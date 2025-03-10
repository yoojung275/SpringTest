package org.choongang.SpringTest.board.services;

import lombok.RequiredArgsConstructor;
import org.choongang.SpringTest.board.controllers.RequestBoard;
import org.choongang.SpringTest.board.entities.BoardData;
import org.choongang.SpringTest.board.exceptions.BoardNotFoundException;
import org.choongang.SpringTest.board.repositories.BoardDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardReadService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long id) {
        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        return boardData;
    }

    public RequestBoard getForm(Long id) {
        BoardData boardData = get(id);
        RequestBoard form = new ModelMapper().map(boardData, RequestBoard.class);

        return form;
    }

}
