package co.edu.ucentral.creditsProject.service;

import co.edu.ucentral.creditsProject.dto.Officer;
import org.springframework.stereotype.Component;

@Component
public interface OfficerService {


    public boolean login(String id, String password);

    public Officer modifyOfficer(Officer officer);

    public Officer getOfficer(String id);


}
