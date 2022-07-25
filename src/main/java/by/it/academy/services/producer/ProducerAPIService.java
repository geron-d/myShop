package by.it.academy.services.producer;

import by.it.academy.dtos.requests.producer.ProducerDTO;
import by.it.academy.entities.Producer;
import by.it.academy.repositories.producer.ProducerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementation of the by.it.academy.services.ProducerService interface.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerAPIService implements ProducerService {

    private final ProducerRepository producerRepository;

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#findProducer(Long id)
     */
    @Override
    public Producer findProducer(Long id) {
        return producerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#createProducer(ProducerDTO dto)
     */
    @Override
    @Transactional
    public Long createProducer(ProducerDTO dto) {
        Producer producer = buildProducer(dto);
        return producerRepository.save(producer).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#updateProducer(Long id, ProducerDTO dto)
     */
    @Override
    @Transactional
    public Long updateProducer(Long id, ProducerDTO dto) {
        if (!checkProducer(id)) {
            throw new NoSuchElementException();
        }
        Producer producer = buildProducer(dto);
        producer.setId(id);
        return producerRepository.save(producer).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#deleteProducer(Long id)
     */
    @Override
    public void deleteProducer(Long id) {
        producerRepository.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#findProducer(String name)
     */
    @Override
    public Producer findProducer(String name) {
        return producerRepository.findProducerByName(name).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#findProducers()
     */
    @Override
    public List<Producer> findProducers() {
        return producerRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#searchProducers(String search)
     */
    @Override
    public List<Producer> searchProducers(String search) {
        return producerRepository.searchAllByNameContains(search);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.ProducerService#findProducers(List<String> producerNames)
     */
    @Override
    @Transactional
    public List<Producer> findProducers(List<String> producerNames) {
        return producerNames.stream()
                .map(this::findProducer)
                .collect(Collectors.toList());
    }

    /**
     * Check producer by its id.
     *
     * @return true if such producer exists and false when does not.
     */
    private boolean checkProducer(Long id) {
        return producerRepository.existsById(id);
    }

    /**
     * Build producer by ProducerDTO.
     *
     * @return producer by ProducerDTO.
     */
    private Producer buildProducer(ProducerDTO dto) {
        return Producer.builder()
                .name(dto.getName())
                .build();
    }

}
