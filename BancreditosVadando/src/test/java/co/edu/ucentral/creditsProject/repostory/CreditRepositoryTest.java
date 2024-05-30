package co.edu.ucentral.creditsProject.repostory;
import co.edu.ucentral.creditsProject.config.CreditType;
import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
import co.edu.ucentral.creditsProject.repostory.entity.CreditEntity;
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
class CreditRepositoryTest {

    @Autowired
    CreditRepository creditRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Test de guardado, repositorio de credito")
    @Test
    void testSaveCredit() {
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setTotalAmount(10000000);
        creditEntity.setMonthsTime(12);
        creditEntity.setCreditType(CreditType.HOME);
        creditEntity.setClientId("123456");
        creditEntity.setInterest(0.017);

        //2 WHEN  - ACCION

        CreditEntity credit = creditRepository.save(creditEntity);

        //3 THEN - VERIFICAR
        assertThat(credit).isNotNull();

    }

        @DisplayName("Test de edicion, repositorio de credito")
        @Test
        void testEditCredit(){
            CreditEntity creditEntity =new CreditEntity();
            creditEntity.setTotalAmount(10000000);
            creditEntity.setMonthsTime(12);
            creditEntity.setCreditType(CreditType.HOME);
            creditEntity.setClientId("123456");
            creditEntity.setInterest(0.017);

            CreditEntity creditEntity2 =new CreditEntity();
            creditEntity2.setTotalAmount(20000000);
            creditEntity2.setMonthsTime(12);
            creditEntity2.setCreditType(CreditType.HOME);
            creditEntity2.setClientId("1234567");
            creditEntity2.setInterest(0.017);

            //2 WHEN  - ACCION

            CreditEntity credit = creditRepository.save(creditEntity);

            CreditEntity credit2 = creditRepository.save(creditEntity2);

            //3 THEN - VERIFICAR
            assertThat(!credit.getClientId().equals(credit2.getClientId()));



        }


        @DisplayName("Test de eliminacion, repositorio de credito")
        @Test
        void testDeleteCredit(){
            CreditEntity creditEntity =new CreditEntity();
            creditEntity.setTotalAmount(10000000);
            creditEntity.setMonthsTime(12);;
            creditEntity.setCreditType(CreditType.HOME);
            creditEntity.setClientId("123456");
            creditEntity.setInterest(0.017);




            //2 WHEN  - ACCION

            CreditEntity credit = creditRepository.save(creditEntity);

            //verificamoos si guarda primero
            assertThat(credit).isNotNull();

            creditRepository.delete(creditEntity);

            //3 THEN - VERIFICAR

            CreditEntity credit1 = creditRepository.findById(credit.getId()).orElse(null);

            assertThat(credit1).isNull();



        }

        @DisplayName("Test de buscar un elemento, repositorio de credito")
        @Test
        void testFindCredit(){
            CreditEntity creditEntity =new CreditEntity();
            creditEntity.setTotalAmount(10000000);
            creditEntity.setMonthsTime(12);
            creditEntity.setCreditType(CreditType.HOME);
            creditEntity.setClientId("123456");
            creditEntity.setInterest(0.017);

            CreditEntity creditEntity2 =new CreditEntity();
            creditEntity2.setTotalAmount(10000000);
            creditEntity2.setMonthsTime(12);
            creditEntity2.setCreditType(CreditType.HOME);
            creditEntity.setClientId("123456");
            creditEntity.setInterest(0.017);


            //2 WHEN  - ACCION

            CreditEntity credit = creditRepository.save(creditEntity);
            //añadimos un segundo para comprobar que encuentra el correcto
            CreditEntity creditExtra = creditRepository.save(creditEntity2);


            //3 THEN - VERIFICAR

            CreditEntity credit1 = creditRepository.findById(credit.getId()).orElse(null);

            assertThat(credit1).isNotNull();
            assertEquals(credit1, credit);



        }







    }

