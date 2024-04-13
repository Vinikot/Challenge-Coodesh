package com.example.task5.controllers;

import com.example.task5.models.Unidade;
import com.example.task5.repositories.UnidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Unidade")
public class UnidadeController {

    @Autowired
    UnidadeRepository unidadeRepository;

    @GetMapping("")
    public List<Unidade> findAll() {
        return unidadeRepository.findAll();
    }

    @GetMapping("/findById")
    public Optional<Unidade> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("unidadeId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        Optional<Unidade> byId = unidadeRepository.findById((Long) idObject);

        return byId;
    }

    @PostMapping("/create")
    public ResponseEntity<Unidade> create(@Valid @RequestBody Unidade requestBody) {

        Unidade savedUnidade = unidadeRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/unidade/" + savedUnidade.getNomeMedidaReceita())).body(savedUnidade);
    }

    @PutMapping("/update")
    public ResponseEntity<Unidade> update(@Valid @RequestBody Unidade requestBody) {

        List<Unidade> unidadeList = unidadeRepository.findAll();

        for (Unidade unidadeTEMP : unidadeList) {
            if (unidadeTEMP.getUnidadeId() == requestBody.getUnidadeId()) {
                unidadeRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Unidade> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("unidadeId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        List<Unidade> unidadeList = unidadeRepository.findAll();

        for (Unidade unidadeTEMP : unidadeList) {
            if (unidadeTEMP.getUnidadeId().equals(idObject)) {
                unidadeRepository.delete(unidadeTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
