package com.example.task5.controllers;

import com.example.task5.models.Ingrediente;
import com.example.task5.repositories.IngredienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/Ingrediente")
public class IngredienteController {

    @Autowired
    IngredienteRepository ingredienteRepository;

    @GetMapping("")
    public List<Ingrediente> findAll() {
        return ingredienteRepository.findAll();
    }

    @GetMapping("/findById")
    public Optional<Ingrediente> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("ingredienteId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        Optional<Ingrediente> byId = ingredienteRepository.findById((Long) idObject);

        return byId;
    }

    @PostMapping("/create")
    public ResponseEntity<Ingrediente> create(@Valid @RequestBody Ingrediente requestBody) {

        Ingrediente savedIngrediente = ingredienteRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/ingrediente/" + savedIngrediente.getNome())).body(savedIngrediente);
    }

    @PutMapping("/update")
    public ResponseEntity<Ingrediente> update(@Valid @RequestBody Ingrediente requestBody) {

        List<Ingrediente> ingredienteList = ingredienteRepository.findAll();

        for (Ingrediente ingredienteTEMP : ingredienteList) {
            if (Objects.equals(ingredienteTEMP.getIngredienteId(), requestBody.getIngredienteId())) {
                ingredienteRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Ingrediente> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        List<Ingrediente> ingredienteList = ingredienteRepository.findAll();

        Object idObject = requestBody.get("ingredienteId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        for (Ingrediente ingredienteTEMP : ingredienteList) {
            if (ingredienteTEMP.getIngredienteId().equals(idObject)) {
                ingredienteRepository.delete(ingredienteTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
