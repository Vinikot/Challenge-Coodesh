package com.example.task5.controllers;

import com.example.task5.models.Receita;
import com.example.task5.repositories.ReceitaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Receita")
public class ReceitaController {

    @Autowired
    ReceitaRepository receitaRepository;
    
    @GetMapping("")
    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }
    
    @GetMapping("/findById")
    public Optional<Receita> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("receitaId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        Optional<Receita> byId = receitaRepository.findById((Long) idObject);

        return byId;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Receita> create(@Valid @RequestBody Receita requestBody) {

        Receita savedReceita = receitaRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/receita/" + savedReceita.getNome())).body(savedReceita);
    }
    
    @PutMapping("/update")
    public ResponseEntity<Receita> update(@Valid @RequestBody Receita requestBody) {

        List<Receita> receitaList = receitaRepository.findAll();

        for (Receita receitaTEMP : receitaList) {
            if (receitaTEMP.getReceitaId() == requestBody.getReceitaId()) {
                receitaRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Receita> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        List<Receita> receitaList = receitaRepository.findAll();

        Object idObject = requestBody.get("receitaId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        for (Receita receitaTEMP : receitaList) {
            if (receitaTEMP.getReceitaId().equals(idObject)) {
                receitaRepository.delete(receitaTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
