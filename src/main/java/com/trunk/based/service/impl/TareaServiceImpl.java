package com.trunk.based.service.impl;

import com.trunk.based.service.TareaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {

    @Override
    public List<String> listarTareas() {
        return Arrays.asList("Tarea 1", "Tarea 2", "Tarea 3");
    }
}
