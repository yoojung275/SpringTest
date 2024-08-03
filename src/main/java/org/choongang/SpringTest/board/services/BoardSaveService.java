package org.choongang.SpringTest.board.services;

import lombok.RequiredArgsConstructor;
import org.choongang.SpringTest.board.controllers.RequestBoard;
import org.choongang.SpringTest.board.entities.BoardData;
import org.choongang.SpringTest.board.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardSaveService {

    private final BoardDataRepository boardDataRepository;

    public void save(RequestBoard form) {
        Long id = Objects.requireNonNullElse(form.getId(), 0L);
        BoardData boardData = boardDataRepository.findById(id).orElseGet(BoardData::new);

        boardData.setAuthor(form.getAuthor());
        boardData.setTitle(form.getTitle());
        boardData.setContent(form.getContent());

        boardDataRepository.saveAndFlush(boardData);
    }
}
