package com.trunk.based.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trunk.based.model.Task;
import com.trunk.based.service.TareaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService tareaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearTarea() throws Exception {
        Task tarea = new Task(1L, "Tarea de prueba", false);

        when(tareaService.crear(any(Task.class))).thenReturn(tarea);

        mockMvc.perform(post("/api/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Tarea de prueba")))
                .andExpect(jsonPath("$.completado", is(false)));
    }

    @Test
    void testListarTareas() throws Exception {
        Task tarea = new Task(1L, "Ver tarea", false);

        when(tareaService.listar()).thenReturn(Collections.singletonList(tarea));

        mockMvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titulo", is("Ver tarea")))
                .andExpect(jsonPath("$[0].completado", is(false)));
    }

    @Test
    void testActualizarTarea() throws Exception {
        Task actualizada = new Task(1L, "Nueva", true);

        when(tareaService.actualizar(any(Long.class), any(Task.class)))
                .thenReturn(Optional.of(actualizada));

        mockMvc.perform(put("/api/tareas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Nueva")))
                .andExpect(jsonPath("$.completado", is(true)));
    }
}
