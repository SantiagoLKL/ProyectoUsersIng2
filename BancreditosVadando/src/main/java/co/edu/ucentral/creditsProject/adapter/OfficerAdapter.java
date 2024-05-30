package co.edu.ucentral.creditsProject.adapter;

import co.edu.ucentral.creditsProject.config.Utilities;
import co.edu.ucentral.creditsProject.dto.Officer;
import co.edu.ucentral.creditsProject.repostory.CreditRepository;
import co.edu.ucentral.creditsProject.repostory.OfficerRepository;
import co.edu.ucentral.creditsProject.repostory.entity.OfficerEntity;
import co.edu.ucentral.creditsProject.service.ClientService;
import co.edu.ucentral.creditsProject.service.OfficerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OfficerAdapter implements OfficerService {

    @Autowired
    OfficerRepository repository;

    @Autowired
    ModelMapper mp;

    @Override
    public boolean login(String id, String password) {
        String passEncrypt = Utilities.encrypt(password);

        Optional<OfficerEntity> officerEntity = repository.findById(id);

        if(!officerEntity.isEmpty()){
            if(officerEntity.get().getPassword().equals(passEncrypt)){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }



    }

    @Override
    public Officer modifyOfficer(Officer officer) {
        officer.setPassword(Utilities.encrypt(officer.getPassword()));

        return mp.map(repository.save(mp.map(officer,OfficerEntity.class)),Officer.class);
    }

    @Override
    public Officer getOfficer(String id) {
        Optional<OfficerEntity> officerEntity = repository.findById(id);
        return mp.map(officerEntity.get(),Officer.class);
    }


}
