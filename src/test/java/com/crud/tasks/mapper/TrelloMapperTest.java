package com.crud.tasks.mapper;

import java.util.ArrayList;
import java.util.List;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crud.tasks.mapper.TrelloMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest(){
        //GIVEN
        List<TrelloBoard> trelloBoard;
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
        assertEquals("2", trelloBoard.get(1).getId());
        assertEquals("board2", trelloBoard.get(1).getName());
        assertEquals(1, trelloBoard.get(1).getLists().size());
    }

    @Test
    public void mapToBoardsDtoTest(){
        //GIVEN
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto;
        List<TrelloList> list = new ArrayList<>();
        List<TrelloList> list2 = new ArrayList<>();
        list.add(new TrelloList("1","list1",false));
        list2.add(new TrelloList("1","list1",false));
        trelloBoard.add(new TrelloBoard("1","board1", list));
        trelloBoard.add(new TrelloBoard("2","board2", list2));
        //WHEN
        trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);
        //THEN
        assertEquals(2, trelloBoardDto.size());
        assertEquals("2", trelloBoardDto.get(1).getId());
        assertEquals("board2", trelloBoardDto.get(1).getName());
        assertEquals(1, trelloBoardDto.get(1).getLists().size());
    }

    @Test
    public void mapToListTest(){
        //GIVEN
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloList> trelloList;
        trelloListDto.add(new TrelloListDto("1","list1",false));
        trelloListDto.add(new TrelloListDto("2","list2",false));
        //WHEN
        trelloList = trelloMapper.mapToList(trelloListDto);
        //THEN
        assertEquals(2, trelloList.size());
        assertEquals("list2", trelloList.get(1).getName());
        assertFalse(trelloList.get(1).isClosed());
    }

    @Test
    public void mapToListDtoTest(){
        //GIVEN
        List<TrelloListDto> trelloListDto;
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1","list1",false));
        trelloList.add(new TrelloList("2","list2",false));
        //WHEN
        trelloListDto = trelloMapper.mapToListDto(trelloList);
        //THEN
        assertEquals(2, trelloListDto.size());
        assertEquals("list2", trelloListDto.get(1).getName());
        assertFalse(trelloListDto.get(1).isClosed());
    }

    @Test
    public void mapToCardDtoTest(){
        //GIVEN
        TrelloCard trelloCard = new TrelloCard("Name 1","Description 1","Position 1","1");
        //WHEN
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //THEN
        assertEquals("Name 1", trelloCardDto.getName());
        assertEquals("Description 1", trelloCardDto.getDescription());
        assertEquals("Position 1", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void mapToCardTest(){
        //GIVEN
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name 1","Description 1","Position 1","1");
        //WHEN
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //THEN
        assertEquals("Name 1", trelloCard.getName());
        assertEquals("Description 1", trelloCard.getDescription());
        assertEquals("Position 1", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}
