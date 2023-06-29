package com.crud.tasks.mapper;

import java.util.ArrayList;
import java.util.List;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;
    
    @Test
    public void mapToTaskTest(){
        //GIVEN
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //WHEN
        Task task = taskMapper.mapToTask(taskDto);
        //THEN
        assertEquals(1L, task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    public void mapToTaskDto(){
        //GIVEN
        Task task = new Task(1L, "title", "content");
        //WHEN
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //THEN
        assertEquals(1L, taskDto.getId());
        assertEquals("title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList(){
        //GIVEN
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskList.add(new Task(1L, "title1", "content1"));
        taskList.add(new Task(2L, "title2", "content2"));
        //WHEN
        taskDtoList = taskMapper.mapToTaskDtoList(taskList);        
        //THEN
        assertEquals(2, taskDtoList.size());
        assertEquals("title2", taskDtoList.get(1).getTitle());
    }
}
