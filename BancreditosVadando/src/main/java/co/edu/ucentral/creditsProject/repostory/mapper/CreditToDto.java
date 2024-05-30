package co.edu.ucentral.creditsProject.repostory.mapper;

import co.edu.ucentral.creditsProject.dto.Credit;
import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditToDto {
    Credit toCredit(CreditEntity creditEntity);
    CreditEntity toCreditEntity(Credit credit);
}
