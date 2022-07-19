package by.it.academy.controllers.type;

import by.it.academy.dtos.requests.type.TypeDTO;
import by.it.academy.entities.Type;
import by.it.academy.services.type.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/types")
@RestController
@RequiredArgsConstructor
public class TypeController {

    private final TypeService<Type> typeService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Type findType(@PathVariable Long id) {
        return typeService.findType(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createType(@RequestBody @Valid TypeDTO dto) {
        return typeService.createType(dto);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateType(@PathVariable Long id, @RequestBody @Valid TypeDTO dto) {
        return typeService.updateType(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }

    @GetMapping(path = "name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Type findType(@PathVariable String name) {
        return typeService.findType(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Type> findTypes() {
        return typeService.findTypes();
    }

}
