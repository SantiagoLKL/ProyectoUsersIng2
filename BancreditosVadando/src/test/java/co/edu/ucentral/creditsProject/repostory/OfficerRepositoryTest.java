package co.edu.ucentral.creditsProject.repostory;

import co.edu.ucentral.creditsProject.repostory.entity.OfficerEntity;
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
class OfficerRepositoryTest {

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp(){

    }

    @DisplayName("Test de guardado, repositorio de officere")
    @Test
    void testSaveOfficer(){
        OfficerEntity officerEntity =new OfficerEntity();
        officerEntity.setId("12345");
        officerEntity.setFirstName("Nombre");
        officerEntity.setLastName("Apellido");
        officerEntity.setStation("Estacion/Puesto");

        //2 WHEN  - ACCION

        OfficerEntity officer = officerRepository.save(officerEntity);

        //3 THEN - VERIFICAR
        assertThat(officer).isNotNull();



    }


    @DisplayName("Test de edicion, repositorio de officere")
    @Test
    void testEditOfficer(){
        OfficerEntity officerEntity =new OfficerEntity();
        officerEntity.setId("12345");
        officerEntity.setFirstName("Nombre");
        officerEntity.setLastName("Apellido");
        officerEntity.setStation("Estacion/Puesto Diferente");

        OfficerEntity officerEntity2 =new OfficerEntity();
        officerEntity2.setId("12345");
        officerEntity2.setFirstName("NombreDiferente");
        officerEntity2.setLastName("ApellidoDiferente");
        officerEntity2.setStation("Estacion/Puesto Diferente");

        //2 WHEN  - ACCION

        OfficerEntity officer = officerRepository.save(officerEntity);

        OfficerEntity officer2 = officerRepository.save(officerEntity2);

        //3 THEN - VERIFICAR
        assertThat(!officer.getFirstName().equals(officer2.getFirstName()));



    }


    @DisplayName("Test de eliminacion, repositorio de officere")
    @Test
    void testDeleteOfficer(){
        OfficerEntity officerEntity =new OfficerEntity();
        officerEntity.setId("12345");
        officerEntity.setFirstName("Nombre");
        officerEntity.setLastName("Apellido");
        officerEntity.setStation("Estacion/Puesto");




        //2 WHEN  - ACCION

        OfficerEntity officer = officerRepository.save(officerEntity);

        //verificamoos si guarda primero
        assertThat(officer).isNotNull();

        officerRepository.delete(officerEntity);

        //3 THEN - VERIFICAR

        OfficerEntity officer1 = officerRepository.findById(officer.getId()).orElse(null);

        assertThat(officer1).isNull();



    }

    @DisplayName("Test de buscar un elemento, repositorio de officere")
    @Test
    void testFindOfficer(){
        OfficerEntity officerEntity =new OfficerEntity();
        officerEntity.setId("12345");
        officerEntity.setFirstName("Nombre");
        officerEntity.setLastName("Apellido");
        officerEntity.setStation("Estacion/Puesto");

        OfficerEntity officerEntity2 =new OfficerEntity();
        officerEntity2.setId("123456");
        officerEntity2.setFirstName("NombreDiferente");
        officerEntity2.setLastName("ApellidoDiferente");
        officerEntity2.setStation("Estacion/Puesto Diferente");


        //2 WHEN  - ACCION

        OfficerEntity officer = officerRepository.save(officerEntity);
        //añadimos un segundo para comprobar que encuentra el correcto
        OfficerEntity officerExtra = officerRepository.save(officerEntity2);


        //3 THEN - VERIFICAR

        OfficerEntity officer1 = officerRepository.findById(officer.getId()).orElse(null);

        assertThat(officer1).isNotNull();
        assertEquals(officer1, officer);



    }


    @DisplayName("Test de buscar todos los elementos, repositorio de officere")
    @Test
    void testFindAllOfficer(){
        OfficerEntity officerEntity =new OfficerEntity();
        officerEntity.setId("12345");
        officerEntity.setFirstName("Nombre");
        officerEntity.setLastName("Apellido");
        officerEntity.setStation("Estacion/Puesto");

        OfficerEntity officerEntity2 =new OfficerEntity();
        officerEntity2.setId("123456");
        officerEntity2.setFirstName("NombreDiferente");
        officerEntity2.setLastName("ApellidoDiferente");
        officerEntity2.setStation("Estacion/Puesto Diferente");


        //2 WHEN  - ACCION

        OfficerEntity officer = officerRepository.save(officerEntity);
        OfficerEntity officerExtra = officerRepository.save(officerEntity2);


        //3 THEN - VERIFICAR

        List<OfficerEntity> officer1 = officerRepository.findAll();

        assertThat(officer1.size()>0);



    }


}