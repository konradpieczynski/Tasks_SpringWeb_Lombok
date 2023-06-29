package com.crud.tasks.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @SpyBean
    private TaskMapper taskMapper;
   
    @Test
    void shouldFetchEmptyTaskList() throws Exception {
    // Given
    when(service.getAllTasks()).thenReturn(List.of());

    //When & Then
    mockMvc
        .perform(MockMvcRequestBuilders
            .get("/v1/tasks")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchTaskList() throws Exception {
    // Given
    List<Task> tasks = List.of(new Task(1L, "title1", "content1"));
    when(service.getAllTasks()).thenReturn(tasks);

    //When & Then
    mockMvc
        .perform(MockMvcRequestBuilders
            .get("/v1/tasks")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content1")));
    }

    @Test
    void shouldFetchTask() throws Exception {
    // Given
    Task task = new Task(1L, "title1", "content1");
    when(service.getTask(1L)).thenReturn(task);

    //When & Then
    mockMvc
        .perform(MockMvcRequestBuilders
            .get("/v1/tasks/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }
    
    void shouldDeleteTask() throws Exception {
    //Given
    //When & Then
    mockMvc
        .perform(MockMvcRequestBuilders
            .delete("/v1/tasks/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTask() throws Exception {
    // Given
    Task task = new Task(1L, "title1", "content1");
    TaskDto taskDto = new TaskDto(1L, "title1", "content1");
    when(service.saveTask(any(Task.class))).thenReturn(task);
    Gson gson = new Gson();
    String jsonContent = gson.toJson(taskDto);
    //When & Then
    mockMvc
        .perform(MockMvcRequestBuilders
            .put("/v1/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(jsonContent))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void shouldCreateTask() throws Exception {
    // Given
    TaskDto taskDto = new TaskDto(1L, "title1", "content1");
    Gson gson = new Gson();
    String jsonContent = gson.toJson(taskDto);
    //When & Then
    mockMvc
        .perform(MockMvcRequestBuilders
            .post("/v1/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(jsonContent))
        .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
