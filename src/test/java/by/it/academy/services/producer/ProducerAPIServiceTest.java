package by.it.academy.services.producer;

import by.it.academy.dtos.requests.producer.ProducerDTO;
import by.it.academy.entities.Producer;
import by.it.academy.repositories.producer.ProducerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing producer service")
public class ProducerAPIServiceTest {

    @Mock
    private ProducerRepository producerRepository;

    @InjectMocks
    private ProducerAPIService producerAPIService;

    Producer producer;

    @BeforeEach
    public void setProducer() {
        producer = Producer.builder()
                .id(1L)
                .name("F9")
                .build();
    }

    public Producer getProducer() {
        return Producer.builder()
                .name(producer.getName())
                .build();
    }

    public ProducerDTO getProducerDTO() {
        return ProducerDTO.builder()
                .name(producer.getName())
                .build();
    }

    public Producer getAnotherProducer() {
        return Producer.builder()
                .id(2L)
                .name("overhead")
                .build();
    }

    @Test
    @DisplayName("JUnit test for findProducer method with arg id when producer exists " +
            "for returning producer is not null")
    void checkFindProducerWithArgIdWhenProducerExistsReturnsNotNull() {
        Mockito.when(producerRepository.findById(producer.getId())).thenReturn(Optional.ofNullable(producer));
        Assertions.assertNotNull(producerAPIService.findProducer(producer.getId()));
    }

    @Test
    @DisplayName("JUnit test for findProducer method with arg id when producer exists")
    void checkFindProducerWithArgIdWhenProducerExists() {
        Mockito.when(producerRepository.findById(producer.getId())).thenReturn(Optional.ofNullable(producer));
        Assertions.assertEquals(producer, producerAPIService.findProducer(producer.getId()));
    }


    @Test
    @DisplayName("JUnit test for findProducer method with arg id when producer does not exist")
    void checkFindProducerWithArgIdWhenProducerDoesNotExist() {
        Mockito.when(producerRepository.findById(producer.getId())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> producerAPIService.findProducer(producer.getId()));
    }

    @Test
    @DisplayName("JUnit test for createProducer method with arg ProducerDTO for returning producer is not null")
    void checkCreateProducerWithArgDTOReturnsNotNull() {
        Producer savingProducer = getProducer();
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.save(savingProducer)).thenReturn(producer);
        Assertions.assertNotNull(producerAPIService.createProducer(dto));
    }

    @Test
    @DisplayName("JUnit test for createProducer method with arg ProducerDTO")
    void checkCreateProducerWithArgDTO() {
        Producer savingProducer = getProducer();
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.save(savingProducer)).thenReturn(producer);
        Assertions.assertEquals(producer.getId(), producerAPIService.createProducer(dto));
    }

    @Test
    @DisplayName("JUnit test for createProducer method with arg ProducerDTO " +
            "when producer with such name already exists")
    void checkCreateProducerWithArgDTOWhenProducerWithSuchNameAlreadyExists() {
        Producer savingProducer = getProducer();
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.save(savingProducer)).thenAnswer(answer -> {
            throw new SQLIntegrityConstraintViolationException();
        });
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                producerAPIService.createProducer(dto));
    }

    @Test
    @DisplayName("JUnit test for updateProducer method with args id and ProducerDTO when producer with such id exists " +
            "for returning producer is not null")
    void checkUpdateProducerWithArgsIdAndProducerDTOWhenProducerWithSuchIdExistsReturnsNotNull() {
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.existsById(producer.getId())).thenReturn(true);
        Mockito.when(producerRepository.save(producer)).thenReturn(producer);
        Assertions.assertNotNull(producerAPIService.updateProducer(producer.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for updateProducer method with args id and ProducerDTO when producer with such id exists")
    void checkUpdateProducerWithArgsIdAndProducerDTOWhenProducerWithSuchIdExists() {
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.existsById(producer.getId())).thenReturn(true);
        Mockito.when(producerRepository.save(producer)).thenReturn(producer);
        Assertions.assertEquals(producer.getId(), producerAPIService.updateProducer(producer.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for updateProducer method with args id and ProducerDTO " +
            "when producer with such id exists and producer with such name already exists")
    void checkUpdateProducerWithArgsIdAndProducerDTOWhenProducerWithSuchIdExistsAndProducerWithSuchNameAlreadyExists() {
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.existsById(producer.getId())).thenReturn(true);
        Mockito.when(producerRepository.save(producer)).thenAnswer(answer -> {
            throw new SQLIntegrityConstraintViolationException();
        });
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                producerAPIService.updateProducer(producer.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for updateProducer method with args id and ProducerDTO " +
            "when producer with such id does not exists")
    void checkUpdateProducerWithArgsIdAndProducerDTOWhenProducerWithSuchIdDoesNotExists() {
        ProducerDTO dto = getProducerDTO();
        Mockito.when(producerRepository.existsById(producer.getId())).thenReturn(false);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> producerAPIService.updateProducer(producer.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for deleteProducer method with arg id when producer with such id exists")
    void checkDeleteProducerWithArgIdWhenProducerWithSuchIdExists() {
        Mockito.doNothing().when(producerRepository).deleteById(producer.getId());
        producerAPIService.deleteProducer(producer.getId());
        Mockito.verify(producerRepository, Mockito.times(1)).deleteById(producer.getId());
    }

    @Test
    @DisplayName("JUnit test for findProducer method with arg String producerName when producer exists " +
            "for returning producer is not null")
    void checkFindProducerWithArgStringProducerNameWhenProducerExistsReturnsNotNull() {
        Mockito.when(producerRepository.findProducerByName(producer.getName()))
                .thenReturn(Optional.ofNullable(producer));
        Assertions.assertNotNull(producerAPIService.findProducer(producer.getName()));
    }

    @Test
    @DisplayName("JUnit test for findProducer method with arg String producerName when producer exists")
    void checkFindProducerWithArgStringProducerNameWhenProducerExists() {
        Mockito.when(producerRepository.findProducerByName(producer.getName()))
                .thenReturn(Optional.ofNullable(producer));
        Assertions.assertEquals(producer, producerAPIService.findProducer(producer.getName()));
    }


    @Test
    @DisplayName("JUnit test for findProducer method with arg String producerName when producer does not exist")
    void checkFindProducerWithArgStringProducerNameWhenProducerDoesNotExist() {
        Mockito.when(producerRepository.findProducerByName(producer.getName())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> producerAPIService.findProducer(producer.getName()));
    }

    @Test
    @DisplayName("JUnit test for findProducers method without args for returning not null")
    void checkFindProducersWithoutArgsReturnsNotNull() {
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.findAll()).thenReturn(producers);
        Assertions.assertNotNull(producerAPIService.findProducers());
    }

    @Test
    @DisplayName("JUnit test for findProducers method without args for returning empty")
    void checkFindProducersWithoutArgsReturnsEmpty() {
        Mockito.when(producerRepository.findAll()).thenReturn(List.of());
        Assertions.assertEquals(List.of(), producerAPIService.findProducers());
    }

    @Test
    @DisplayName("JUnit test for findProducers method without args when producer exist")
    void checkFindProducersWithoutArgsWhenProducersExist() {
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.findAll()).thenReturn(producers);
        Assertions.assertEquals(producers, producerAPIService.findProducers());
    }

    @Test
    @DisplayName("JUnit test for findProducers method without args when producer exist for size")
    void checkFindProducersWithoutArgsWhenProducersExistForSize() {
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.findAll()).thenReturn(producers);
        Assertions.assertEquals(producers.size(), producerAPIService.findProducers().size());
    }

    @Test
    @DisplayName("JUnit test for findProducers method without args when producer exist for first producer")
    void checkFindProducersWithoutArgsWhenProducersExistForFirstProducer() {
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.findAll()).thenReturn(producers);
        Assertions.assertEquals(producers.get(0), producerAPIService.findProducers().get(0));
    }

    @Test
    @DisplayName("JUnit test for searchProducers method with arg String search when result presents " +
            "for returning not null")
    void checkSearchProducersWithArgSearchWhenResultPresentsReturnsNotNull() {
        String search = "wire";
        List<Producer> producers = List.of(producer);
        Mockito.when(producerRepository.searchAllByNameContains(search)).thenReturn(producers);
        Assertions.assertNotNull(producerAPIService.searchProducers(search));
    }

    @Test
    @DisplayName("JUnit test for searchProducers method with arg String search when result does not present")
    void checkSearchProducersWithArgSearchWhenResultDoesNotPresent() {
        String search = "asdasdsdasd";
        Mockito.when(producerRepository.searchAllByNameContains(search)).thenReturn(List.of());
        Assertions.assertEquals(List.of(), producerAPIService.searchProducers(search));
    }

    @Test
    @DisplayName("JUnit test for searchProducers method with arg String search when result presents")
    void checkSearchProducersWithArgSearchWhenResultPresents() {
        String search = "e";
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.searchAllByNameContains(search)).thenReturn(producers);
        Assertions.assertEquals(producers, producerAPIService.searchProducers(search));
    }

    @Test
    @DisplayName("JUnit test for searchProducers method with arg String search when result presents for size")
    void checkSearchProducersWithArgSearchWhenResultPresentsForSize() {
        String search = "e";
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.searchAllByNameContains(search)).thenReturn(producers);
        Assertions.assertEquals(producers.size(), producerAPIService.searchProducers(search).size());
    }

    @Test
    @DisplayName("JUnit test for searchProducers method with arg String search when result presents " +
            "for first producer")
    void checkSearchProducersWithArgSearchWhenResultPresentsForFirstProducer() {
        String search = "e";
        Producer anotherProducer = getAnotherProducer();
        List<Producer> producers = List.of(producer, anotherProducer);
        Mockito.when(producerRepository.searchAllByNameContains(search)).thenReturn(producers);
        Assertions.assertEquals(producers.get(0), producerAPIService.searchProducers(search).get(0));
    }

    @Test
    @DisplayName("JUnit test for findProducers method with arg List String producerNames for returning not null")
    void checkFindProducersWithArgStringListProducerNamesReturnsNotNull() {
        List<String> producerNames = List.of(producer.getName());
        Mockito.when(producerRepository.findProducerByName(producerNames.get(0)))
                .thenReturn(Optional.ofNullable(producer));
        Assertions.assertNotNull(producerAPIService.findProducers(producerNames));
    }

    @Test
    @DisplayName("JUnit test for findProducers method with arg List String producerNames " +
            "when producers does not exist")
    void checkFindProducersWithArgStringListProducerNamesWhenProducersDoesNotExist() {
        List<String> producerNames = List.of("headpasdfdshones");
        Mockito.when(producerRepository.findProducerByName(producerNames.get(0)))
                .thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> producerAPIService.findProducers(producerNames));
    }

    @Test
    @DisplayName("JUnit test for findProducers method with arg List String producerNames when producers exist")
    void checkFindProducersWithoutArgStringListProducerNamesWhenProducersExist() {
        List<String> producerNames = List.of(producer.getName());
        List<Producer> producers = List.of(producer);
        Mockito.when(producerRepository.findProducerByName(producerNames.get(0)))
                .thenReturn(Optional.ofNullable(producer));
        Assertions.assertEquals(producers, producerAPIService.findProducers(producerNames));
    }

    @Test
    @DisplayName("JUnit test for findProducers method with argument List String producerNames " +
            "when producers exist for size")
    void checkFindProducersWithoutArgStringListProducerNamesWhenProducersExistForSize() {
        List<String> producerNames = List.of(producer.getName());
        List<Producer> producers = List.of(producer);
        Mockito.when(producerRepository.findProducerByName(producerNames.get(0)))
                .thenReturn(Optional.ofNullable(producer));
        Assertions.assertEquals(producers.size(), producerAPIService.findProducers(producerNames).size());
    }

    @Test
    @DisplayName("JUnit test for findProducers method with argument List String producerNames " +
            "when producers exist for first producer")
    void checkFindProducersWithoutArgStringListProducerNamesWhenProducersExistForFirstProducer() {
        List<String> producerNames = List.of(producer.getName());
        List<Producer> producers = List.of(producer);
        Mockito.when(producerRepository.findProducerByName(producerNames.get(0)))
                .thenReturn(Optional.ofNullable(producer));
        Assertions.assertEquals(producers.get(0), producerAPIService.findProducers(producerNames).get(0));
    }

}
