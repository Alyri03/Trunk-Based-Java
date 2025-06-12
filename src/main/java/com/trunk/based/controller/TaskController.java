package com.trunk.based.controller;

import com.trunk.based.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    private final List<Task> tareas = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public ResponseEntity<List<Task>> listarTareas() {
        return ResponseEntity.ok(tareas);
    }

    @PostMapping
    public ResponseEntity<Task> crearTarea(@RequestBody Task tarea) {
        tarea.setId(idCounter++);
        tareas.add(tarea);
        return ResponseEntity.ok(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> actualizarTarea(@PathVariable Long id, @RequestBody Task nuevaTarea) {
        for (Task tarea : tareas) {
            if (tarea.getId().equals(id)) {
                tarea.setTitulo(nuevaTarea.getTitulo());
                tarea.setCompletado(nuevaTarea.isCompletado());
                return ResponseEntity.ok(tarea);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        boolean removed = tareas.removeIf(t -> t.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
