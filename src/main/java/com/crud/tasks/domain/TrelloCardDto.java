package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;
}