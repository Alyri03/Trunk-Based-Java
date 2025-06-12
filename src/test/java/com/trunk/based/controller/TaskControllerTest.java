package com.trunk.based.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trunk.based.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearYListarTarea() throws Exception {
        Task tarea = new Task(null, "Tarea de prueba", false);

        // Crear tarea
       mockMvc.perform(post("/api/tareas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(tarea)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.titulo", is("Tarea de prueba")))
        .andExpect(jsonPath("$.completado", is(false)));


        // Listar tareas
        mockMvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testEliminarTarea() throws Exception {
        Task tarea = new Task(null, "Eliminar esto", false);

        // Crear tarea
        String response = mockMvc.perform(post("/api/tareas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarea)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Task tareaCreada = objectMapper.readValue(response, Task.class);

        // Eliminar la tarea
        mockMvc.perform(delete("/api/tareas/" + tareaCreada.getId()))
                .andExpect(status().isNoContent());

        // Verificar que la tarea fue eliminada
        mockMvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
