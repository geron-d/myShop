package by.it.academy.controllers.category;

import by.it.academy.dtos.requests.category.CategoryDTO;
import by.it.academy.entities.Category;
import by.it.academy.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for performing operations with a category of product.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Find category by its id.
     *
     * @return the category with the given id. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if category none found.
     */
    @GetMapping("{id}")
    public Category findCategory(@PathVariable Long id) {
        return categoryService.findCategory(id);
    }

    /**
     * Save a category by given CategoryDTO.
     *
     * @return the id of saved category. Return HttpStatus.CREATED when request passed without exceptions.
     * Throw SQLIntegrityConstraintViolationException when category with such name exists.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCategory(@RequestBody @Valid CategoryDTO dto) {
        return categoryService.createCategory(dto);
    }

    /**
     * Update a category by given id and CategoryDTO.
     *
     * @return the id of updated category. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if category none found. Throw SQLIntegrityConstraintViolationException
     * when category with such name exists.
     */
    @PutMapping( "{id}")
    public Long updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO dto) {
        return categoryService.updateCategory(id, dto);
    }

    /**
     * Delete a category by given id.
     *
     * Return HttpStatus.OK when request passed without exceptions. Throw NoSUchElementException if category none found.
     */
    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    /**
     * Find category by its name.
     *
     * @return the category with the given name. Return HttpStatus.OK when request passed without exceptions.
     * Throw NoSUchElementException if category none found.
     */
    @GetMapping("name/{name}")
    public Category findCategory(@PathVariable String name) {
        return categoryService.findCategory(name);
    }

    /**
     * Find all categories.
     *
     * @return all categories. Return HttpStatus.OK when request passed without exceptions.
     */
    @GetMapping
    public List<Category> findCategories() {
        return categoryService.findCategories();
    }

}
