package by.it.academy.controllers.type;

import by.it.academy.dtos.requests.type.TypeDTO;
import by.it.academy.entities.Type;
import by.it.academy.services.type.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for performing operations with a type of product.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@RequestMapping("/types")
@RestController
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    /**
     * Find type by its id.
     *
     * @return the type with the given id. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if type not found.
     */
    @GetMapping("{id}")
    public Type findType(@PathVariable Long id) {
        return typeService.findType(id);
    }

    /**
     * Save a type by given TypeDTO.
     *
     * @return the id of saved type. Return HttpStatus.CREATED when request passed without exceptions.
     * Throw SQLIntegrityConstraintViolationException when type with such name exists.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createType(@RequestBody @Valid TypeDTO dto) {
        return typeService.createType(dto);
    }

    /**
     * Update a type by given id and TypeDTO.
     *
     * @return the id of updated type. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if type none found. Throw SQLIntegrityConstraintViolationException
     * when type with such name exists.
     */
    @PutMapping("{id}")
    public Long updateType(@PathVariable Long id, @RequestBody @Valid TypeDTO dto) {
        return typeService.updateType(id, dto);
    }

    /**
     * Delete a type by given id.
     *
     * Return HttpStatus.OK when request passed without exceptions. Throw NoSUchElementException if type not found.
     */
    @DeleteMapping("{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }

    /**
     * Find type by its name.
     *
     * @return the type with the given name. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if type not found.
     */
    @GetMapping("name/{name}")
    public Type findType(@PathVariable String name) {
        return typeService.findType(name);
    }

    /**
     * Find all types.
     *
     * @return all types. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping
    public List<Type> findTypes() {
        return typeService.findTypes();
    }

}
