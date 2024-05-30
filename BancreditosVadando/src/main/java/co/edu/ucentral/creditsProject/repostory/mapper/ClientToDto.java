package co.edu.ucentral.creditsProject.repostory.mapper;


import co.edu.ucentral.creditsProject.dto.Client;
import co.edu.ucentral.creditsProject.repostory.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientToDto {

    Client toClient(ClientEntity clientEntity);
    ClientEntity toClientEntity(Client client);


}
