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

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerAPIService implements ProducerService<Producer> {

    private final ProducerRepository producerRepository;

    @Override
    public Producer findProducer(Long id) {
        return producerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Long createProducer(ProducerDTO dto) {
        Producer producer = buildProducer(dto);
        return producerRepository.save(producer).getId();
    }

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

    @Override
    public void deleteProducer(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public Producer findProducer(String name) {
        return producerRepository.findProducerByName(name);
    }

    @Override
    public List<Producer> findProducers() {
        return producerRepository.findAll();
    }

    @Override
    public List<Producer> searchProducers(String search) {
        return producerRepository.searchAllByNameContains(search);
    }

    @Override
    @Transactional
    public List<Producer> findProducers(List<String> producerNames) {
        return producerNames.stream()
                .map(this::findProducer)
                .collect(Collectors.toList());
    }

    private boolean checkProducer(Long id) {
        return producerRepository.existsById(id);
    }

    private Producer buildProducer(ProducerDTO dto) {
        return Producer.builder()
                .name(dto.getName())
                .build();
    }

}
