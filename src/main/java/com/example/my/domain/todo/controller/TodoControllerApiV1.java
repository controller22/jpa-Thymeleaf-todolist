package com.example.my.domain.todo.controller;

import com.example.my.common.dto.LoginUserDTO;
import com.example.my.common.dto.ResponseDTO;
import com.example.my.domain.todo.dto.ReqTodoTableInsertDTO;
import com.example.my.domain.todo.dto.ReqTodoTableUpdateDoneYnDTO;
import com.example.my.domain.todo.service.TodoServiceApiV1;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoControllerApiV1 {

    private final TodoServiceApiV1 todoServiceApiV1;

    @GetMapping
    public ResponseEntity<?> getTodoTableData(HttpSession session) {
        // TODO : 서비스에서 할 일 목록 가져오기
        LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("dto");

        return todoServiceApiV1.getTodoTableData(loginUserDTO);
    }

    @PostMapping
    public ResponseEntity<?> insertTodoTableData(
            @RequestBody ReqTodoTableInsertDTO dto,
            HttpSession session) {
        // TODO : 서비스에서 할 일 추가하기
        LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("dto");

        todoServiceApiV1.insertTodoTableData(dto, loginUserDTO);

        return new ResponseEntity<>(ResponseDTO.builder()
                .message("할 일 추가 성공")
                .code(0)
                .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{todoIdx}")
    public ResponseEntity<?> updateTodoTableData(
            @PathVariable Long todoIdx,
            @RequestBody ReqTodoTableUpdateDoneYnDTO dto,
            HttpSession session) {
        // TODO : 서비스에서 할 일 완료 수정하기
        LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("dto");

        todoServiceApiV1.updateTodoTableData(todoIdx, dto, loginUserDTO);

        return new ResponseEntity<>(ResponseDTO.builder()
                .message("할 일 업데이트 성공")
                .code(0)
                .build(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{todoIdx}")
    public ResponseEntity<?> deleteTodoTableData(
            @PathVariable Long todoIdx,
            HttpSession session) {
        // TODO : 서비스에서 할 일 삭제하기
        LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("dto");
        todoServiceApiV1.deleteTodoTableData(todoIdx, loginUserDTO);

        return new ResponseEntity<>(ResponseDTO.builder()
                .message("할 일 삭제 성공")
                .code(0)
                .build(),
                HttpStatus.OK);
    }

}
