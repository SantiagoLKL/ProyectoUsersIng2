package co.edu.ucentral.creditsProject.repostory;

import co.edu.ucentral.creditsProject.repostory.entity.ClientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp(){

    }

    @DisplayName("Test de guardado, repositorio de cliente")
    @Test
    void testSaveClient(){
        ClientEntity clientEntity =new ClientEntity();
        clientEntity.setId("12345");
        clientEntity.setFirstName("Nombre");
        clientEntity.setLastName("Apellido");

        //2 WHEN  - ACCION

        ClientEntity client = clientRepository.save(clientEntity);

        //3 THEN - VERIFICAR
        assertThat(client).isNotNull();



    }


    @DisplayName("Test de edicion, repositorio de cliente")
    @Test
    void testEditClient(){
        ClientEntity clientEntity =new ClientEntity();
        clientEntity.setId("12345");
        clientEntity.setFirstName("Nombre");
        clientEntity.setLastName("Apellido");

        ClientEntity clientEntity2 =new ClientEntity();
        clientEntity2.setId("12345");
        clientEntity2.setFirstName("NombreDiferente");
        clientEntity2.setLastName("ApellidoDiferente");

        //2 WHEN  - ACCION

        ClientEntity client = clientRepository.save(clientEntity);

        ClientEntity client2 = clientRepository.save(clientEntity2);

        //3 THEN - VERIFICAR
        assertThat(!client.getFirstName().equals(client2.getFirstName()));



    }


    @DisplayName("Test de eliminacion, repositorio de cliente")
    @Test
    void testDeleteClient(){
        ClientEntity clientEntity =new ClientEntity();
        clientEntity.setId("12345");
        clientEntity.setFirstName("Nombre");
        clientEntity.setLastName("Apellido");




        //2 WHEN  - ACCION

        ClientEntity client = clientRepository.save(clientEntity);

        //verificamoos si guarda primero
        assertThat(client).isNotNull();

        clientRepository.delete(clientEntity);

        //3 THEN - VERIFICAR

        ClientEntity client1 = clientRepository.findById(client.getId()).orElse(null);

        assertThat(client1).isNull();



    }

    @DisplayName("Test de buscar un elemento, repositorio de cliente")
    @Test
    void testFindClient(){
        ClientEntity clientEntity =new ClientEntity();
        clientEntity.setId("12345");
        clientEntity.setFirstName("Nombre");
        clientEntity.setLastName("Apellido");

        ClientEntity clientEntity2 =new ClientEntity();
        clientEntity2.setId("123456");
        clientEntity2.setFirstName("NombreDiferente");
        clientEntity2.setLastName("ApellidoDiferente");


        //2 WHEN  - ACCION

        ClientEntity client = clientRepository.save(clientEntity);
        //añadimos un segundo para comprobar que encuentra el correcto
        ClientEntity clientExtra = clientRepository.save(clientEntity2);


        //3 THEN - VERIFICAR

        ClientEntity client1 = clientRepository.findById(client.getId()).orElse(null);

        assertThat(client1).isNotNull();
        assertEquals(client1, client);



    }


    @DisplayName("Test de buscar todos los elementos, repositorio de cliente")
    @Test
    void testFindAllClient(){
        ClientEntity clientEntity =new ClientEntity();
        clientEntity.setId("12345");
        clientEntity.setFirstName("Nombre");
        clientEntity.setLastName("Apellido");

        ClientEntity clientEntity2 =new ClientEntity();
        clientEntity2.setId("123456");
        clientEntity2.setFirstName("NombreDiferente");
        clientEntity2.setLastName("ApellidoDiferente");


        //2 WHEN  - ACCION

        ClientEntity client = clientRepository.save(clientEntity);
        ClientEntity clientExtra = clientRepository.save(clientEntity2);


        //3 THEN - VERIFICAR

        List<ClientEntity> client1 = clientRepository.findAll();

        assertThat(client1.size()>0);



    }




}