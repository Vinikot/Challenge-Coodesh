package com.example.task5.controllers;

import com.example.task5.models.ReceitaIngrediente;
import com.example.task5.repositories.ReceitaIngredienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ReceitaIngrediente")
public class ReceitaIngredienteController {

    @Autowired
    ReceitaIngredienteRepository receitaIngredienteRepository;

    @GetMapping("")
    public List<ReceitaIngrediente> findAll() {
        return receitaIngredienteRepository.findAll();
    }

    @GetMapping("/findById")
    public Optional<ReceitaIngrediente> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("receitaIngredienteId");
        if (idObject instanceof Integer) {
            idObject = (ReceitaIngrediente.ReceitaIngredienteId) idObject;
        }

        Optional<ReceitaIngrediente> byId = receitaIngredienteRepository.findById((ReceitaIngrediente.ReceitaIngredienteId) idObject);

        return byId;
    }

    @PostMapping("/create")
    public ResponseEntity<ReceitaIngrediente> create(@Valid @RequestBody ReceitaIngrediente requestBody) {

        ReceitaIngrediente savedReceitaIngrediente = receitaIngredienteRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/receita-ingrediente/" + savedReceitaIngrediente.getReceitaIngredienteId())).body(savedReceitaIngrediente);
    }

    @PutMapping("/update")
    public ResponseEntity<ReceitaIngrediente> update(@Valid @RequestBody ReceitaIngrediente requestBody) {

        List<ReceitaIngrediente> receitaIngredienteList = receitaIngredienteRepository.findAll();

        for (ReceitaIngrediente receitaIngredienteTEMP : receitaIngredienteList) {
            if (receitaIngredienteTEMP.getReceitaIngredienteId() == requestBody.getReceitaIngredienteId()) {
                receitaIngredienteRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ReceitaIngrediente> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        List<ReceitaIngrediente> receitaIngredienteList = receitaIngredienteRepository.findAll();

        Object idObject = requestBody.get("receitaIngredienteId");
        if (idObject instanceof Integer) {
            idObject = (ReceitaIngrediente.ReceitaIngredienteId) idObject;
        }

        for (ReceitaIngrediente receitaIngredienteTEMP : receitaIngredienteList) {
            if (receitaIngredienteTEMP.getReceitaIngredienteId() == ( requestBody.get("receitaIngredienteId"))) {
                receitaIngredienteRepository.delete(receitaIngredienteTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createList")
    public ResponseEntity<ReceitaIngrediente> createList(@Valid @RequestBody List<ReceitaIngrediente> requestBody) {

        for (ReceitaIngrediente receitaIngredienteTEMP : requestBody) {
            receitaIngredienteRepository.save(receitaIngredienteTEMP);
        }

        return ResponseEntity.created(URI.create("/receita-ingrediente-list/")).build();
    }
}
