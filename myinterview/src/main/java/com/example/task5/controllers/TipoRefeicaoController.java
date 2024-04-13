package com.example.task5.controllers;

import com.example.task5.models.TipoRefeicao;
import com.example.task5.repositories.TipoRefeicaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/TipoRefeicao")
public class TipoRefeicaoController {

    @Autowired
    TipoRefeicaoRepository tipoRefeicaoRepository;
    
    @GetMapping("")
    public List<TipoRefeicao> findAll() {
        return tipoRefeicaoRepository.findAll();
    }
    
    @GetMapping("/findById")
    public Optional<TipoRefeicao> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("tipoRefeicaoId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        Optional<TipoRefeicao> byId = tipoRefeicaoRepository.findById((Long) idObject);

        return byId;
    }
    
    @PostMapping("/create")
    public ResponseEntity<TipoRefeicao> create(@Valid @RequestBody TipoRefeicao requestBody) {

        TipoRefeicao savedTipoRefeicao = tipoRefeicaoRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/tipo-refeicao/" + savedTipoRefeicao.getNome())).body(savedTipoRefeicao);
    }
    
    @PutMapping("/update")
    public ResponseEntity<TipoRefeicao> update(@Valid @RequestBody TipoRefeicao requestBody) {

        List<TipoRefeicao> tipoRefeicaoList = tipoRefeicaoRepository.findAll();

        for (TipoRefeicao tipoRefeicaoTEMP : tipoRefeicaoList) {
            if (tipoRefeicaoTEMP.getTipoRefeicaoId() == requestBody.getTipoRefeicaoId()) {
                tipoRefeicaoRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<TipoRefeicao> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("tipoRefeicaoId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        List<TipoRefeicao> tipoRefeicaoList = tipoRefeicaoRepository.findAll();

        for (TipoRefeicao tipoRefeicaoTEMP : tipoRefeicaoList) {
            if (tipoRefeicaoTEMP.getTipoRefeicaoId().equals(idObject)) {
                tipoRefeicaoRepository.delete(tipoRefeicaoTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
