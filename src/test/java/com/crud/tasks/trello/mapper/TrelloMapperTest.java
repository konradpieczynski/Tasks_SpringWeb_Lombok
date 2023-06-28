package com.crud.tasks.trello.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest(){
        //GIVEN
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloListDto> trelloListDto2 = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "test1", false));
        trelloListDto2.add(new TrelloListDto("1", "test2", true));
        trelloBoardDto.add(new TrelloBoardDto("1", "board1", trelloListDto));
        trelloBoardDto.add(new TrelloBoardDto("2", "board2", trelloListDto2));
        //WHEN
        trelloBoard = trelloMapper.mapToBoards(trelloBoardDto);
        //THEN
        assertEquals(2, trelloBoard.size());
        assertEquals("board2", trelloBoard.get(1).getName());                
    }

    @Test
    public void mapToBoardsDtoTest(){
        
    }

    @Test
    public void mapToListTest(){

    }

    @Test
    public void mapToListDtoTest(){

    }

    @Test
    public void mapToCardDtoTest(){

    }

    @Test
    public void mapToCardTest(){

    }
}
