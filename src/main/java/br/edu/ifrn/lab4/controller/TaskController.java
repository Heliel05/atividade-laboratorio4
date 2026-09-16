package br.edu.ifrn.lab4.controller;

import br.edu.ifrn.lab4.dto.TaskRequestDTO;
import br.edu.ifrn.lab4.dto.TaskResponseDTO;
import br.edu.ifrn.lab4.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> criar(
            @RequestBody TaskRequestDTO dto
    ) {

        System.out.println(
                "[CONTROLLER] Requisição recebida: POST /tarefas"
        );

        TaskResponseDTO criada = service.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(criada);
    }

    @GetMapping
    public List<TaskResponseDTO> listar() {

        System.out.println(
                "[CONTROLLER] Requisição recebida: GET /tarefas"
        );

        return service.listarTodas();
    }
}