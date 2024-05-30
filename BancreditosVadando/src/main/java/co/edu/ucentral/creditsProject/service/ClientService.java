package co.edu.ucentral.creditsProject.service;

import co.edu.ucentral.creditsProject.dto.Client;
import org.springframework.stereotype.Component;

@Component
public interface ClientService {
    public void saveClient(Client client);

    public Client findClient(String id);

}
