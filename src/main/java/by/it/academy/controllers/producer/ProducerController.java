package by.it.academy.controllers.producer;

import by.it.academy.dtos.requests.producer.ProducerDTO;
import by.it.academy.entities.Producer;
import by.it.academy.services.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for performing operations with a producer of product.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@RequestMapping("/producers")
@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    /**
     * Find producer by its id.
     *
     * @return the producer with the given id. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if producer none found.
     */
    @GetMapping("{id}")
    public Producer findProducer(@PathVariable Long id) {
        return producerService.findProducer(id);
    }

    /**
     * Save a producer by given ProducerDTO.
     *
     * @return the id of saved producer. Return HttpStatus.CREATED when request passed without exceptions.
     * Throw SQLIntegrityConstraintViolationException when producer with such name exists.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProducer(@RequestBody @Valid ProducerDTO dto) {
        return producerService.createProducer(dto);
    }

    /**
     * Update a producer by given id and ProducerDTO.
     *
     * @return the id of updated producer. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if producer none found. Throw SQLIntegrityConstraintViolationException
     * when producer with such name exists.
     */
    @PutMapping("{id}")
    public Long updateProducer(@PathVariable Long id, @RequestBody @Valid ProducerDTO dto) {
        return producerService.updateProducer(id, dto);
    }

    /**
     * Delete a producer by given id.
     *
     * Return HttpStatus.OK when request passed without exceptions. Throw NoSUchElementException if producer none found.
     */
    @DeleteMapping("{id}")
    public void deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
    }

    /**
     * Find producer by its name.
     *
     * @return the producer with the given name. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if producer none found.
     */
    @GetMapping("name/{name}")
    public Producer findProducer(@PathVariable String name) {
        return producerService.findProducer(name);
    }

    /**
     * Find all producers.
     *
     * @return all producers. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping
    public List<Producer> findProducers() {
        return producerService.findProducers();
    }

}
