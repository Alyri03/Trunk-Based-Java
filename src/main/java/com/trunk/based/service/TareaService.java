package com.trunk.based.service;

import com.trunk.based.model.Task;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    List<Task> listar();
    Task crear(Task tarea);
    Optional<Task> actualizar(Long id, Task tarea);
}
