package com.crud.tasks.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class TrelloBoard {
   private String id;
   private String name;
   private List<TrelloList> lists;
}