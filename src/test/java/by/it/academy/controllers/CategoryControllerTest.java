package by.it.academy.controllers;

import by.it.academy.controllers.category.CategoryController;
import by.it.academy.dtos.requests.category.CategoryDTO;
import by.it.academy.entities.Category;
import by.it.academy.services.category.CategoryService;
import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private Gson gson;

    private Category category;

    @BeforeEach
    public void setCategory() {
        category = Category.builder()
                .id(1L)
                .name("headphones")
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
    @DisplayName("Test for findCategory method with arg id when id valid and category exists")
    void checkFindCategoryWithArgIdWhenIdValidAndCategoryExists() throws Exception {
        Mockito.when(categoryService.findCategory(category.getId())).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{id}", category.getId())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(category.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(category.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for findCategory method with arg id when id not valid")
    void checkFindCategoryWithArgIdWhenIdNotValid() throws Exception {
        Mockito.when(categoryService.findCategory(category.getId())).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{id}", "null")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentTypeMismatchException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for createCategory method with arg CategoryDTO when CategoryDTO valid")
    void checkCreateCategoryWithArgCategoryDTOWhenDTOValid() throws Exception {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryService.createCategory(dto)).thenReturn(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .content(gson.toJson(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(category.getId().toString()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for createCategory method with arg CategoryDTO when CategoryDTO does not valid")
    void checkCreateCategoryWithArgCategoryDTOWhenDTODoesNotValid() throws Exception {
        CategoryDTO dto = getCategoryDTO();
        dto.setName("");
        Mockito.when(categoryService.createCategory(dto)).thenReturn(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                        .content(gson.toJson(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for updateCategory method with args id and CategoryDTO when id and CategoryDTO valid")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenIdAndDTOValid() throws Exception {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryService.updateCategory(category.getId(), dto)).thenReturn(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/{id}", category.getId())
                        .content(gson.toJson(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(category.getId().toString()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for updateCategory method with args id and CategoryDTO when CategoryDTO does not valid")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenDTODoesNotValid() throws Exception {
        CategoryDTO dto = getCategoryDTO();
        dto.setName("");
        Mockito.when(categoryService.updateCategory(category.getId(), dto)).thenReturn(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/{id}", category.getId())
                        .content(gson.toJson(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for updateCategory method with args id and CategoryDTO when id does not valid")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenIdDoesNotValid() throws Exception {
        CategoryDTO dto = getCategoryDTO();
        Mockito.when(categoryService.updateCategory(category.getId(), dto)).thenReturn(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/{id}", "null")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentTypeMismatchException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for deleteCategory method with arg id when id valid")
    void checkDeleteCategoryWithArgIdWhenIdValid() throws Exception {
        Mockito.doNothing().when(categoryService).deleteCategory(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/{id}", category.getId())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for deleteCategory method with arg id when id not valid")
    void checkDeleteCategoryWithArgIdWhenIdDoesNotValid() throws Exception {
        Mockito.doNothing().when(categoryService).deleteCategory(category.getId());
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{id}", "null")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException()
                        instanceof MethodArgumentTypeMismatchException))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for findCategory method with arg name when name valid and category exists")
    void checkFindCategoryWithArgNameWhenNameValidAndCategoryExists() throws Exception {
        Mockito.when(categoryService.findCategory(category.getName())).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/name/{name}", category.getName())
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(category.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(category.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for findCategories method without args")
    void checkFindCategoriesWithoutArgs() throws Exception {
        Category anotherCategory = getAnotherCategory();
        List<Category> categories = List.of(category, anotherCategory);
        Mockito.when(categoryService.findCategories()).thenReturn(categories);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(category.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(category.getName())))
                .andDo(MockMvcResultHandlers.print());

    }

}
