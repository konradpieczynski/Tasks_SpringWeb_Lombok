package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trello {
    @JsonProperty("board")
    int board;
    @JsonProperty("card")
    int card;
}
