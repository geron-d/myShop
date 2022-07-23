package by.it.academy.services.productInBucket;

import by.it.academy.dtos.requests.category.CategoryDTO;
import by.it.academy.entities.Category;
import by.it.academy.repositories.category.CategoryRepository;
import by.it.academy.services.category.CategoryAPIService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing category service")
public class ProductInBucketAPIServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryAPIService categoryAPIService;

    Category category;

    @BeforeEach
    public void setCategory() {
        category = Category.builder()
                .id(1L)
                .name("headphones")
                .build();
    }

    public Category getSavingCategory() {
        return Category.builder()
                .name(category.getName())
                .build();
    }

    public CategoryDTO getCategoryDTO() {
        return CategoryDTO.builder()
                .name(category.getName())
                .build();
    }

    public Category getAnotherCategory() {
        return Category.builder()
                .id(2L)
                .name("micros")
                .build();
    }

    @Test
    @DisplayName("JUnit test for findCategory method with arg id when category exists " +
            "for returning category is not null")
    void checkFindCategoryWithArgIdWhenCategoryExistsReturnsNotNull() {
        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.ofNullable(category));
        Assertions.assertNotNull(categoryAPIService.findCategory(category.getId()));
    }

    @Test
    @DisplayName("JUnit test for findCategory method with arg id when category exists")
    void checkFindCategoryWithArgIdWhenCategoryExists() {
        Mockito.when(categoryRepository.findById(category.getId())).thenReturn(Optional.ofNullable(category));
        Assertions.assertEquals(category, categoryAPIService.findCategory(category.getId()));
    }


    @Test
    @DisplayName("JUnit test for findCategory method with arg id when category does not exist")
    void checkFindCategoryWithArgIdWhenCategoryDoesNotExist() {
        Mockito.when(categoryRepository.findById(category.getId())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> categoryAPIService.findCategory(category.getId()));
    }

    @Test
    @DisplayName("JUnit test for createCategory method with arg CategoryDTO for returning category is not null")
    void checkCreateCategoryWithArgDTOReturnsNotNull() {
        Category savingCategory = getSavingCategory();
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.save(savingCategory)).thenReturn(category);
        Assertions.assertNotNull(categoryAPIService.createCategory(dto));
    }

    @Test
    @DisplayName("JUnit test for createCategory method with arg CategoryDTO")
    void checkCreateCategoryWithArgDTO() {
        Category savingCategory = getSavingCategory();
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.save(savingCategory)).thenReturn(category);
        Assertions.assertEquals(category.getId(), categoryAPIService.createCategory(dto));
    }

    @Test
    @DisplayName("JUnit test for createCategory method with arg CategoryDTO " +
            "when category with such name already exists")
    void checkCreateCategoryWithArgDTOWhenCategoryWithSuchNameAlreadyExists() {
        Category savingCategory = getSavingCategory();
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.save(savingCategory)).thenAnswer(answer -> {
            throw new SQLIntegrityConstraintViolationException();
        });
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                categoryAPIService.createCategory(dto));
    }

    @Test
    @DisplayName("JUnit test for updateCategory method with args id and CategoryDTO when category with such id exists " +
            "for returning category is not null")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenCategoryWithSuchIdExistsReturnsNotNull() {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.existsById(category.getId())).thenReturn(true);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Assertions.assertNotNull(categoryAPIService.updateCategory(category.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for updateCategory method with args id and CategoryDTO when category with such id exists")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenCategoryWithSuchIdExists() {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.existsById(category.getId())).thenReturn(true);
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        Assertions.assertEquals(category.getId(), categoryAPIService.updateCategory(category.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for updateCategory method with args id and CategoryDTO " +
            "when category with such id exists and category with such name already exists")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenCategoryWithSuchIdExistsAndCategoryWithSuchNameAlreadyExists() {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.existsById(category.getId())).thenReturn(true);
        Mockito.when(categoryRepository.save(category)).thenAnswer(answer -> {
            throw new SQLIntegrityConstraintViolationException();
        });
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () ->
                categoryAPIService.updateCategory(category.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for updateCategory method with args id and CategoryDTO " +
            "when category with such id does not exists")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenCategoryWithSuchIdDoesNotExists() {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryRepository.existsById(category.getId())).thenReturn(false);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> categoryAPIService.updateCategory(category.getId(), dto));
    }

    @Test
    @DisplayName("JUnit test for deleteCategory method with arg id when category with such id exists")
    void checkDeleteCategoryWithArgIdWhenCategoryWithSuchIdExists() {
        Mockito.doNothing().when(categoryRepository).deleteById(category.getId());
        categoryAPIService.deleteCategory(category.getId());
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(category.getId());
    }

    @Test
    @DisplayName("JUnit test for findCategory method with arg String categoryName when category exists " +
            "for returning category is not null")
    void checkFindCategoryWithArgStringCategoryNameWhenCategoryExistsReturnsNotNull() {
        Mockito.when(categoryRepository.findCategoryByName(category.getName()))
                .thenReturn(Optional.ofNullable(category));
        Assertions.assertNotNull(categoryAPIService.findCategory(category.getName()));
    }

    @Test
    @DisplayName("JUnit test for findCategory method with arg String categoryName when category exists")
    void checkFindCategoryWithArgStringCategoryNameWhenCategoryExists() {
        Mockito.when(categoryRepository.findCategoryByName(category.getName()))
                .thenReturn(Optional.ofNullable(category));
        Assertions.assertEquals(category, categoryAPIService.findCategory(category.getName()));
    }


    @Test
    @DisplayName("JUnit test for findCategory method with arg String categoryName when category does not exist")
    void checkFindCategoryWithArgStringCategoryNameWhenCategoryDoesNotExist() {
        Mockito.when(categoryRepository.findCategoryByName(category.getName())).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> categoryAPIService.findCategory(category.getName()));
    }

    @Test
    @DisplayName("JUnit test for findCategories method without args for returning not null")
    void checkFindCategoriesWithoutArgsReturnsNotNull() {
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Assertions.assertNotNull(categoryAPIService.findCategories());
    }

    @Test
    @DisplayName("JUnit test for findCategories method without args for returning empty")
    void checkFindCategoriesWithoutArgsReturnsEmpty() {
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of());
        Assertions.assertEquals(List.of(), categoryAPIService.findCategories());
    }

    @Test
    @DisplayName("JUnit test for findCategories method without args when categories exist")
    void checkFindCategoriesWithoutArgsWhenCategoriesExist() {
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Assertions.assertEquals(categories, categoryAPIService.findCategories());
    }

    @Test
    @DisplayName("JUnit test for findCategories method without args when categories exist for size")
    void checkFindCategoriesWithoutArgsWhenCategoriesExistForSize() {
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Assertions.assertEquals(categories.size(), categoryAPIService.findCategories().size());
    }

    @Test
    @DisplayName("JUnit test for findCategories method without args when categories exist for first Category")
    void checkFindCategoriesWithoutArgsWhenCategoriesExistForFirstCategory() {
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Assertions.assertEquals(categories.get(0), categoryAPIService.findCategories().get(0));
    }

    @Test
    @DisplayName("JUnit test for searchCategories method with arg String search when result presents " +
            "for returning not null")
    void checkSearchCategoriesWithArgSearchWhenResultPresentsReturnsNotNull() {
        String search = "head";
        List<Category> categories = List.of(category);
        Mockito.when(categoryRepository.searchAllByNameContains(search)).thenReturn(categories);
        Assertions.assertNotNull(categoryAPIService.searchCategories(search));
    }

    @Test
    @DisplayName("JUnit test for searchCategories method with arg String search when result does not present")
    void checkSearchCategoriesWithArgSearchWhenResultDoesNotPresent() {
        String search = "asdasdsdasd";
        Mockito.when(categoryRepository.searchAllByNameContains(search)).thenReturn(List.of());
        Assertions.assertEquals(List.of(), categoryAPIService.searchCategories(search));
    }

    @Test
    @DisplayName("JUnit test for searchCategories method with arg String search when result presents")
    void checkSearchCategoriesWithArgSearchWhenResultPresents() {
        String search = "s";
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.searchAllByNameContains(search)).thenReturn(categories);
        Assertions.assertEquals(categories, categoryAPIService.searchCategories(search));
    }

    @Test
    @DisplayName("JUnit test for searchCategories method with arg String search when result presents for size")
    void checkSearchCategoriesWithArgSearchWhenResultPresentsForSize() {
        String search = "s";
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.searchAllByNameContains(search)).thenReturn(categories);
        Assertions.assertEquals(categories.size(), categoryAPIService.searchCategories(search).size());
    }

    @Test
    @DisplayName("JUnit test for searchCategories method with arg String search when result presents " +
            "for first Category")
    void checkSearchCategoriesWithArgSearchWhenResultPresentsForFirstCategory() {
        String search = "s";
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryRepository.searchAllByNameContains(search)).thenReturn(categories);
        Assertions.assertEquals(categories.get(0), categoryAPIService.searchCategories(search).get(0));
    }

    @Test
    @DisplayName("JUnit test for findCategories method with arg List String categoryNames for returning not null")
    void checkFindCategoriesWithArgStringListCategoryNamesReturnsNotNull() {
        List<String> categoryNames = List.of(category.getName());
        Mockito.when(categoryRepository.findCategoryByName(categoryNames.get(0)))
                .thenReturn(Optional.ofNullable(category));
        Assertions.assertNotNull(categoryAPIService.findCategories(categoryNames));
    }

    @Test
    @DisplayName("JUnit test for findCategories method with arg List String categoryNames " +
            "when categories does not exist")
    void checkFindCategoriesWithArgStringListCategoryNamesWhenCategoriesDoesNotExist() {
        List<String> categoryNames = List.of("headpasdfdshones");
        Mockito.when(categoryRepository.findCategoryByName(categoryNames.get(0)))
                .thenThrow(new NoSuchElementException());
        Assertions.assertThrows(NoSuchElementException.class, () -> categoryAPIService.findCategories(categoryNames));
    }

    @Test
    @DisplayName("JUnit test for findCategories method with arg List String categoryNames when categories exist")
    void checkFindCategoriesWithoutArgStringListCategoryNamesWhenCategoriesExist() {
        List<String> categoryNames = List.of(category.getName());
        List<Category> categories = List.of(category);
        Mockito.when(categoryRepository.findCategoryByName(categoryNames.get(0)))
                .thenReturn(Optional.ofNullable(category));
        Assertions.assertEquals(categories, categoryAPIService.findCategories(categoryNames));
    }

    @Test
    @DisplayName("JUnit test for findCategories method with argument List String categoryNames " +
            "when categories exist for size")
    void checkFindCategoriesWithoutArgStringListCategoryNamesWhenCategoriesExistForSize() {
        List<String> categoryNames = List.of(category.getName());
        List<Category> categories = List.of(category);
        Mockito.when(categoryRepository.findCategoryByName(categoryNames.get(0)))
                .thenReturn(Optional.ofNullable(category));
        Assertions.assertEquals(categories.size(), categoryAPIService.findCategories(categoryNames).size());
    }

    @Test
    @DisplayName("JUnit test for findCategories method with argument List String categoryNames " +
            "when categories exist for first Category")
    void checkFindCategoriesWithoutArgStringListCategoryNamesWhenCategoriesExistForFirstCategory() {
        List<String> categoryNames = List.of(category.getName());
        List<Category> categories = List.of(category);
        Mockito.when(categoryRepository.findCategoryByName(categoryNames.get(0)))
                .thenReturn(Optional.ofNullable(category));
        Assertions.assertEquals(categories.get(0), categoryAPIService.findCategories(categoryNames).get(0));
    }

}
