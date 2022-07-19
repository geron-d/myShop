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

@Slf4j
@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService<Category> categoryService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category findCategory(@PathVariable Long id) {
        return categoryService.findCategory(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createCategory(@RequestBody @Valid CategoryDTO dto) {
        return categoryService.createCategory(dto);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO dto) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping(path = "name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Category findCategory(@PathVariable String name) {
        return categoryService.findCategory(name);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findCategories() {
        return categoryService.findCategories();
    }

}
