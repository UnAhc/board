package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private BoardRepository boardRepo;

    @GetMapping("list")
    private String list(Model model) {
        List<Board> boardList = boardRepo.findAll();
        model.addAttribute("boardList", boardList);
        return "main";
    }

    @GetMapping("reg")
    private String moveReg() {
        return "board/reg";
    }

    @PostMapping("reg")
    private String reg(BoardDto boardDto, Model model) {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter()).build();

        boardRepo.save(board);

        model.addAttribute("msg", "게시글이 정상 등록 되었습니다.");


        return "redirect:/board/list";
    }

}
