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

/**
 * Implementation of the by.it.academy.services.CategoryService interface.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryAPIService implements CategoryService {

    private final CategoryRepository categoryRepository;

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#findCategory(Long id)
     */
    @Override
    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#createCategory(CategoryDTO dto)
     */
    @Override
    public Long createCategory(CategoryDTO dto) {
        Category category = buildCategory(dto);
        return categoryRepository.save(category).getId();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#updateCategory(Long id, CategoryDTO dto)
     */
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

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#deleteCategory(Long id)
     */
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#findCategory(String name)
     */
    @Override
    public Category findCategory(String name) {
        return categoryRepository.findCategoryByName(name).orElseThrow(NoSuchElementException::new);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#findCategories()
     */
    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#searchCategories(String search)
     */
    @Override
    public List<Category> searchCategories(String search) {
        return categoryRepository.searchAllByNameContains(search);
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.services.CategoryService#findCategories(List<String> categoryNames)
     */
    @Override
    @Transactional
    public List<Category> findCategories(List<String> categoryNames) {
        return categoryNames.stream()
                .map(this::findCategory)
                .collect(Collectors.toList());
    }

    /**
     * Check category by its id.
     *
     * @return true if such category exists and false when does not.
     */
    private boolean checkCategory(Long id) {
        return categoryRepository.existsById(id);
    }

    /**
     * Build category by CategoryDTO.
     *
     * @return category by CategoryDTO.
     */
    private Category buildCategory(CategoryDTO dto) {
        return Category.builder()
                .name(dto.getName())
                .build();
    }

}
