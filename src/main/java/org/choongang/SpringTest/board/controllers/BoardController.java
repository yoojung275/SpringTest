package org.choongang.SpringTest.board.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.SpringTest.board.entities.BoardData;
import org.choongang.SpringTest.board.repositories.BoardDataRepository;
import org.choongang.SpringTest.board.services.BoardReadService;
import org.choongang.SpringTest.board.services.BoardSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardSaveService boardSaveService;
    private final BoardReadService readService;
    private final BoardDataRepository boardDataRepository;


    @GetMapping("/write")
    public String write(@ModelAttribute RequestBoard form, Model model) {
        commonProcess("write", model);

        return "front/board/write";
    }

    /*
    @PostMapping("/write")
    public String writePs(@ModelAttribute RequestBoard form, Model model) {
        // = boardDataRepository.saveAndFlush(form);

        return "redirect:/board/read";
    }
    */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        BoardData boardData = readService.get(id);
        model.addAttribute("boardData", boardData);

        return "front/board/view";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardData> items = boardDataRepository.findAll();
        model.addAttribute("items", items);
        return "front/board/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        commonProcess("update", model);
        RequestBoard form = readService.getForm(id);
        model.addAttribute("requestBoard", form);
        return "front/board/update";
    }

    @PostMapping("/save")
    public String save(@Valid RequestBoard form, Errors errors, Model model) {
        String mode = form.getId() == null ? "update" : "write";
        commonProcess(mode, model);

        if (errors.hasErrors()) {
            return "front/board/" + mode;
        }

        boardSaveService.save(form);

        return "redirect:/board/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardDataRepository.deleteById(id);

        return "redirect:/board/list";
    }

    private void commonProcess(String mode, Model model) {
        List<String> addCss = new ArrayList<>();
        List<String> addScript = new ArrayList<>();
        addCss.add("board/style"); // 공통

        if (mode == "write" || mode == "update") { // 글쓰기 또는 수정
            addCss.add("board/form");
        }

        model.addAttribute("addCss", addCss);
        model.addAttribute("addScript", addScript);
    }

}
