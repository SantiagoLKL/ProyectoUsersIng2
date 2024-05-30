package co.edu.ucentral.creditsProject.repostory;

import co.edu.ucentral.creditsProject.repostory.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {
}
