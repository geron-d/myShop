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

@Slf4j
@RequestMapping("/producers")
@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService<Producer> producerService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Producer findProducer(@PathVariable Long id) {
        return producerService.findProducer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProducer(@RequestBody @Valid ProducerDTO dto) {
        return producerService.createProducer(dto);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateProducer(@PathVariable Long id, @RequestBody @Valid ProducerDTO dto) {
        return producerService.updateProducer(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
    }

    @GetMapping(path = "name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Producer findProducer(@PathVariable String name) {
        return producerService.findProducer(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Producer> findProducers() {
        return producerService.findProducers();
    }

}
