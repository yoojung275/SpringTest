package org.choongang.SpringTest.global.board.controllers;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class RequestBoard {
    @Id
    private Long id;
    @NotBlank
    private String author;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
