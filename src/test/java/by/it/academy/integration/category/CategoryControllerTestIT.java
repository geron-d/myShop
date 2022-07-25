package by.it.academy.integration.category;

import by.it.academy.dtos.requests.category.CategoryDTO;
import by.it.academy.entities.Category;
import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(value = "/scripts/INITIAL_OFFER_TABLE_SCRIPT.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CategoryControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private Category category;

    @BeforeEach
    public void setCategory() {
        category = Category.builder()
                .id(4L)
                .name("very new category")
                .build();
    }

    public CategoryDTO getCategoryDTO() {
        return CategoryDTO.builder()
                .name(category.getName())
                .build();
    }

    @Test
    @DisplayName("Test for findCategory method with arg id when id valid and category exists")
    void checkFindCategoryWithArgIdWhenIdValidAndCategoryExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/{id}", 1)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("headphones")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for findCategory method with arg id when id not valid")
    void checkFindCategoryWithArgIdWhenIdNotValid() throws Exception {
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
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/{id}", 1)
                        .content(gson.toJson(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("1"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for updateCategory method with args id and CategoryDTO when CategoryDTO does not valid")
    void checkUpdateCategoryWithArgsIdAndCategoryDTOWhenDTODoesNotValid() throws Exception {
        CategoryDTO dto = getCategoryDTO();
        dto.setName("");
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
        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/{id}", 3)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for deleteCategory method with arg id when id not valid")
    void checkDeleteCategoryWithArgIdWhenIdDoesNotValid() throws Exception {
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
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/name/{name}", "headphones")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("headphones")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test for findCategories method without args")
    void checkFindCategoriesWithoutArgs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("headphones")))
                .andDo(MockMvcResultHandlers.print());

    }
}
