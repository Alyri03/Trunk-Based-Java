package com.trunk.based.service.impl;

import com.trunk.based.model.Task;
import com.trunk.based.service.TareaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TareaServiceImpl implements TareaService {

    private final Map<Long, Task> tareas = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<Task> listar() {
        return new ArrayList<>(tareas.values());
    }

    @Override
    public Task crear(Task tarea) {
        tarea.setId(idCounter++);
        tareas.put(tarea.getId(), tarea);
        return tarea;
    }

    @Override
    public Optional<Task> actualizar(Long id, Task nueva) {
        if (!tareas.containsKey(id)) return Optional.empty();
        Task actual = tareas.get(id);
        actual.setTitulo(nueva.getTitulo());
        actual.setCompletado(nueva.isCompletado());
        return Optional.of(actual);
    }
}
