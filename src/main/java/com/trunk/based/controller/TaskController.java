package com.trunk.based.controller;

import com.trunk.based.model.Task;
import com.trunk.based.service.TareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    private final TareaService tareaService;

    public TaskController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> listarTareas() {
        return ResponseEntity.ok(tareaService.listar());
    }

    @PostMapping
    public ResponseEntity<Task> crearTarea(@RequestBody Task tarea) {
        return ResponseEntity.ok(tareaService.crear(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> actualizarTarea(@PathVariable Long id, @RequestBody Task nuevaTarea) {
        return tareaService.actualizar(id, nuevaTarea)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
