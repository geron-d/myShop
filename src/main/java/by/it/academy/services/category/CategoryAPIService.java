package by.it.academy.services.category;

import by.it.academy.dtos.requests.category.CategoryDTO;
import by.it.academy.entities.Category;
import by.it.academy.repositories.category.CategoryRepository;
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
public class CategoryAPIService implements CategoryService<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Long createCategory(CategoryDTO dto) {
        Category category = buildCategory(dto);
        return categoryRepository.save(category).getId();
    }

    @Override
    @Transactional
    public Long updateCategory(Long id, CategoryDTO dto) {
        if (!checkCategory(id)) {
            throw new NoSuchElementException();
        }
        Category category = buildCategory(dto);
        category.setId(id);
        return categoryRepository.save(category).getId();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findCategory(String name) {
        return categoryRepository.findCategoryByName(name).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String search) {
        return categoryRepository.searchAllByNameContains(search);
    }

    @Override
    @Transactional
    public List<Category> findCategories(List<String> categoryNames) {
        return categoryNames.stream()
                .map(this::findCategory)
                .collect(Collectors.toList());
    }

    private boolean checkCategory(Long id) {
        return categoryRepository.existsById(id);
    }

    private Category buildCategory(CategoryDTO dto) {
        return Category.builder()
                .name(dto.getName())
                .build();
    }

}
