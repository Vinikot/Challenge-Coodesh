package com.example.task5.controllers;

import com.example.task5.models.CardapioReceita;
import com.example.task5.repositories.CardapioReceitaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/CardapioReceita")
public class CardapioReceitaController {

    @Autowired
    CardapioReceitaRepository cardapioReceitaRepository;
    
    @GetMapping("")
    public List<CardapioReceita> findAll() {
        return cardapioReceitaRepository.findAll();
    }
    
    @GetMapping("/findById")
    public Optional<CardapioReceita> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("cardapioReceitaId");
        if (idObject instanceof Integer) {
            idObject = ((CardapioReceita.CardapioReceitaId) idObject);
        }

        Optional<CardapioReceita> byId = cardapioReceitaRepository.findById((CardapioReceita.CardapioReceitaId) idObject);

        return byId;
    }
    
    @PostMapping("/create")
    public ResponseEntity<CardapioReceita> create(@Valid @RequestBody CardapioReceita requestBody) {

        CardapioReceita savedCardapioReceita = cardapioReceitaRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/cardapio-receita/" + savedCardapioReceita.getCardapioReceitaId())).body(savedCardapioReceita);
    }

    @PutMapping("/update")
    public ResponseEntity<CardapioReceita> update(@Valid @RequestBody CardapioReceita requestBody) {

        List<CardapioReceita> cardapioReceitaList = cardapioReceitaRepository.findAll();

        for (CardapioReceita cardapioReceitaTEMP : cardapioReceitaList) {
            if (cardapioReceitaTEMP.getCardapioReceitaId() == requestBody.getCardapioReceitaId()) {
                cardapioReceitaRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<CardapioReceita> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        List<CardapioReceita> cardapioReceitaList = cardapioReceitaRepository.findAll();

        Object idObject = requestBody.get("cardapioReceitaId");
        if (idObject instanceof Integer) {
            idObject = ((CardapioReceita.CardapioReceitaId) idObject);
        }

        for (CardapioReceita cardapioReceitaTEMP : cardapioReceitaList) {
            if (cardapioReceitaTEMP.getCardapioReceitaId().equals(idObject)) {
                cardapioReceitaRepository.delete(cardapioReceitaTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createList")
    public ResponseEntity<CardapioReceita> createList(@Valid @RequestBody List<CardapioReceita> requestBody) {

        for (CardapioReceita cardapioReceitaTEMP : requestBody) {
            cardapioReceitaRepository.save(cardapioReceitaTEMP);
        }

        return ResponseEntity.created(URI.create("/cardapio-receita-list/")).build();
    }
}
