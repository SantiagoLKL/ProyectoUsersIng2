package co.edu.ucentral.creditsProject.repostory;

import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Integer> {

    @Query(value = "SELECT c FROM CreditEntity c WHERE c.status = 'REQUESTED'")
    List<CreditEntity> getAllApprovingPending();


    List<CreditEntity> findByClientId(String clientId);
    List<CreditEntity> findByOfficerId(String officerId);
}
