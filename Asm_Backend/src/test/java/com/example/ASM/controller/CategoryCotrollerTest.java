package com.example.ASM.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Category.CategoryRequest;
import com.example.ASM.dto.request.Category.CategoryUpdateRequest;
import com.example.ASM.dto.response.product.CategoryResponse;
import com.example.ASM.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class CategoryCotrollerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    CategoryRequest request;
    CategoryRequest existsRequest;
    CategoryRequest missing_input;
    CategoryResponse categoryResponse;
    CategoryUpdateRequest updateRequest;

    @BeforeEach
    void initData() throws Exception {
        request =
                CategoryRequest.builder().categoryName("test").parentCategory(0).build();

        existsRequest = CategoryRequest.builder()
                .categoryName("existed-test")
                .parentCategory(0)
                .build();

        missing_input =
                CategoryRequest.builder().categoryName("").parentCategory(0).build();

        categoryResponse = CategoryResponse.builder()
                .id(1)
                .categoryName("test")
                .parentCategory(0)
                .build();

        updateRequest = CategoryUpdateRequest.builder()
                .categoryName("test")
                .parentCategory(0)
                .build();
    }

    @Test
    void create_valid() throws Exception {
        Mockito.when(categoryService.CreateCategory(any())).thenReturn(true);

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result").value(true))
                .andReturn();
    }

    @Test
    void detail_valid() throws Exception {
        int id = 1;

        Mockito.when(categoryService.Detail(id)).thenReturn(categoryResponse);

        mockMvc.perform(get("/category/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value(1))
                .andReturn();
    }

    @Test
    void list_valid() throws Exception {
        Mockito.when(categoryService.List()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/category/List").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").isArray())
                .andReturn();
    }

    @Test
    void get_valid() throws Exception {
        int page = 1;
        int size = 10;

        List<CategoryResponse> categoryList = new ArrayList<>();
        categoryList.add(new CategoryResponse(1, "Electronic", 0, new ArrayList<>(), new ArrayList<>()));
        categoryList.add(new CategoryResponse(2, "SmartPhone", 1, new ArrayList<>(), List.of("Iphone 13")));
        categoryList.add(new CategoryResponse(3, "test", 0, new ArrayList<>(), new ArrayList<>()));
        categoryList.add(new CategoryResponse(4, "SmartPhone1", 1, new ArrayList<>(), new ArrayList<>()));
        categoryList.add(new CategoryResponse(5, "haha", 0, new ArrayList<>(), new ArrayList<>()));
        categoryList.add(new CategoryResponse(6, "ha", 1, new ArrayList<>(), new ArrayList<>()));

        PageResponse<CategoryResponse> cates = new PageResponse<>(1, 1, 10, 6, categoryList);

        Mockito.when(categoryService.Get(page, size)).thenReturn(cates);

        mockMvc.perform(get("/category/Get")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1000))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.result.totalElements").value(6))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.result.data.length()").value(6))
                .andReturn();
    }

    @Test
    void delete_valid() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/category/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Delete success!"))
                .andReturn();
    }

    @Test
    void update_valid() throws Exception {
        int id = 1;

        Mockito.when(categoryService.Update(id, updateRequest)).thenReturn(categoryResponse);

        mockMvc.perform(delete("/category/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(new ObjectMapper().writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Delete success!"))
                .andReturn();
    }
}
