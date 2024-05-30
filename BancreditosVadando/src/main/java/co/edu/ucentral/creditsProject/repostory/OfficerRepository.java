package co.edu.ucentral.creditsProject.repostory;


import co.edu.ucentral.creditsProject.repostory.entity.OfficerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepository extends JpaRepository<OfficerEntity, String> {
}
