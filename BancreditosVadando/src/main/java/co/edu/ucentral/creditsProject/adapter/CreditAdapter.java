package co.edu.ucentral.creditsProject.adapter;


import co.edu.ucentral.creditsProject.config.CreditType;
import co.edu.ucentral.creditsProject.config.Status;
import co.edu.ucentral.creditsProject.dto.Client;
import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.dto.Officer;
import co.edu.ucentral.creditsProject.repostory.CreditRepository;
import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import co.edu.ucentral.creditsProject.repostory.mapper.CreditToDto;
import co.edu.ucentral.creditsProject.service.ClientService;
import co.edu.ucentral.creditsProject.service.CreditService;
import co.edu.ucentral.creditsProject.service.OfficerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.ucentral.creditsProject.service.CreditService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import static co.edu.ucentral.creditsProject.config.Utilities.ID_LOG_IN;

@Service
@RequiredArgsConstructor
public class CreditAdapter implements CreditService {

    @Autowired
    CreditToDto creditMapper;
    @Autowired
    CreditRepository creditRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    OfficerService officerService;

    @Autowired
    ModelMapper mp;

    @Override
    public Credit registerCredit(Credit credit) {
        credit.setCreditType(getCreditType(credit.getType()));

        credit.setInterest(getInterest(credit.getCreditType()));
        credit.setCurrentAmount(credit.getTotalAmount());

        Client client = new Client();
        client.setId(credit.getIdClient());
        client.setFirstName(credit.getFirstName());
        client.setLastName(credit.getLastName());
        client.setEmail(credit.getEmail());
        client.setIncome(credit.getIncome());
        client.setAddress(credit.getAddress());
        client.setPhone(credit.getPhone());


        credit.setClient(client);
        credit.setStatus(Status.REQUESTED);


        CreditEntity creditEntity = creditMapper.toCreditEntity(credit);
        creditEntity.setClientId(credit.getClient().getId());
        clientService.saveClient(client);



        creditRepository.save(creditEntity);
        return credit;
    }

    @Override
    public List<Credit> getAllCredits() {
        TypeToken<List<Credit>> token = new TypeToken<>(){};
        List<Credit> credits = mp.map(creditRepository.findAll(),token.getType());

        credits.forEach(credit ->{
                    if(credit.getOfficerId() != null){
                        credit.setOfficer(officerService.getOfficer(credit.getOfficerId()));
                    }
                }
        );



        return setExtraData(credits);
    }

    @Override
    public List<Credit> getAllCreditsClient(String id) {
        TypeToken<List<Credit>> token = new TypeToken<>(){};
        List<Credit> credits = mp.map(creditRepository.findByClientId(id),token.getType());

        credits.forEach(credit ->{
                    if(credit.getOfficerId() != null){
                        credit.setOfficer(officerService.getOfficer(credit.getOfficerId()));
                    }
        }
        );



        return setExtraData(credits);
    }

    @Override
    public List<Credit> getAllCreditsOfficer(String id) {

        TypeToken<List<Credit>> token = new TypeToken<>(){};
        List<Credit> credits = mp.map(creditRepository.findByOfficerId(id),token.getType());

        credits.forEach(credit ->{
                    if(credit.getOfficerId() != null){
                        credit.setOfficer(officerService.getOfficer(credit.getOfficerId()));
                    }
                }
        );

        return setExtraData(credits);

    }

    @Override
    public List<Credit> getApprovingPendingCreditsOfficer() {
        TypeToken<List<Credit>> token = new TypeToken<>(){};
        List<Credit> credits = mp.map(creditRepository.getAllApprovingPending(),token.getType());

        return setExtraData(credits);
    }

    @Override
    public double quotesimulation(double totalAmount, double interest, double monthsTime) {
        double total = totalAmount  / monthsTime;
        return total + (total * interest);
    }

    @Override
    public double getInterest(CreditType creditType){

        return switch (creditType) {
            case HOME -> 0.011;
            case VEHICLE -> 0.017;
            case STUDIES -> 0.009;
            case WALLET -> 0.008;
            case FREE -> 0.015;
            default -> 0;
        };


    }

    @Override
    public CreditType getCreditType(String creditType){
        return switch (creditType) {
            case "HOME" -> CreditType.HOME;
            case "VEHICLE" -> CreditType.VEHICLE;
            case "STUDIES" -> CreditType.STUDIES;
            case "WALLET" -> CreditType.WALLET;
            case "FREE" -> CreditType.FREE;
            default -> null;
        };
    }

    @Override
    public Credit getCredit(int id) {
        System.out.println("entra getCredit");
        Credit credit = mp.map(creditRepository.findById(id).orElse(null),Credit.class);
        credit.setClient(clientService.findClient(credit.getIdClient()));
        credit.setType(getStatusLabel(credit.getStatus()));
        if(credit.getOfficerId() != null){
            credit.setOfficer(officerService.getOfficer(credit.getOfficerId()));
        }

        return credit;
    }



    @Override
    public Credit approveCredit(boolean approve, int id, int dateCut) {
        Credit credit = getCredit(id);

        if (approve) {
            credit.setStatus(Status.ACTIVE);


            String dtStr = String.valueOf(dateCut)+"-"+String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR));


            LocalDate localDate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+2,dateCut);
            SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
            Date date = Date.valueOf(localDate);



            credit.setDatePayment(date);


        }else{
            credit.setStatus(Status.CANCELLED);
        }
        credit.setOfficerId(ID_LOG_IN);
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CreditEntity ce = mp.map(credit,CreditEntity.class);
        ce.setClientId(credit.getIdClient());
        creditRepository.save(ce);

        return credit;
    }

    public String getStatusLabel(Status status) {
        switch (status) {
            case REQUESTED:
                return "Pendiente de aprobacion";
            case ACTIVE:
                return "Activo/en curso";
            case CANCELLED:
                return "No aprobado";
            case TERMINATED:
                return "Finalizado";
            default:
                return "Estado desconocido";
        }
    }


    public List<Credit> setExtraData(List<Credit> credits){
        credits.forEach(credit -> credit.setClient(clientService.findClient(credit.getIdClient())));
        credits.forEach(credit -> credit.setType(getStatusLabel(credit.getStatus())));
        return credits;
    }

}
