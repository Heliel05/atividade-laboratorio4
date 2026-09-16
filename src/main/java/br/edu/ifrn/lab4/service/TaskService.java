package br.edu.ifrn.lab4.service;

import br.edu.ifrn.lab4.dto.TaskRequestDTO;
import br.edu.ifrn.lab4.dto.TaskResponseDTO;
import br.edu.ifrn.lab4.model.Task;
import br.edu.ifrn.lab4.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskResponseDTO criar(TaskRequestDTO dto) {

        System.out.println(
                "[SERVICE] Validando regra de negócio para: "
                        + dto.titulo()
        );

        if (dto.titulo() == null || dto.titulo().isBlank()) {
            throw new IllegalArgumentException(
                    "O título da tarefa não pode ser vazio."
            );
        }

        Task tarefa = new Task(
                dto.titulo().trim(),
                dto.descricao(),
                dto.prazo()
        );

        Task salva = repository.salvar(tarefa);

        return toResponseDTO(salva);
    }

    public List<TaskResponseDTO> listarTodas() {

        return repository.listarTodas()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private TaskResponseDTO toResponseDTO(Task tarefa) {

        return new TaskResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.isConcluida(),
                tarefa.getPrioridade()
        );
    }
}