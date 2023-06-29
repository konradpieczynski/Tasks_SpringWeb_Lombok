package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;
import com.crud.tasks.domain.*;

import static org.junit.jupiter.api.Assertions.*;
public class DomainTest {  
    @Test
    public void createdTrelloCardDtoTest(){
        //GIVEN
        Trello trello = new Trello();
        trello.setBoard(1);
        trello.setCard(1);
        AttachmentsByType attachmentsByType = new AttachmentsByType();
        attachmentsByType.setTrello(trello);
        Badges badges = new Badges();
        badges.setVotes(1);
        badges.setAttachments(attachmentsByType);
        //WHEN
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        createdTrelloCardDto.setId("1");
        createdTrelloCardDto.setName("name");
        createdTrelloCardDto.setShortUrl("url");
        createdTrelloCardDto.setBadges(badges);
        //THEN
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("url", createdTrelloCardDto.getShortUrl());
        assertEquals(badges, createdTrelloCardDto.getBadges());
        assertEquals(1, createdTrelloCardDto.getBadges().getVotes());
        assertEquals(attachmentsByType, createdTrelloCardDto.getBadges().getAttachments());
        assertEquals(trello, createdTrelloCardDto.getBadges().getAttachments().getTrello());
        assertEquals(1, createdTrelloCardDto.getBadges().getAttachments().getTrello().getBoard());
        assertEquals(1, createdTrelloCardDto.getBadges().getAttachments().getTrello().getCard());
    }
}
