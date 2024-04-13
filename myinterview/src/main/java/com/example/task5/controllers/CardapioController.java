package com.example.task5.controllers;

import com.example.task5.models.Cardapio;
import com.example.task5.repositories.CardapioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Cardapio")
public class CardapioController {

    @Autowired
    CardapioRepository cardapioRepository;

    @GetMapping("")
    public List<Cardapio> findAll() {
        return cardapioRepository.findAll();
    }

    @GetMapping("/findById")
    public Optional<Cardapio> findById(@Valid @RequestBody Map<String, Object> requestBody) {

        Object idObject = requestBody.get("cardapioId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        Optional<Cardapio> byId = cardapioRepository.findById((Long) idObject);

        return byId;
    }

    @PostMapping("/create")
    public ResponseEntity<Cardapio> create(@Valid @RequestBody Cardapio requestBody) {

        Cardapio savedCardapio = cardapioRepository.save(requestBody);

        return ResponseEntity.created(URI.create("/cardapio/")).body(savedCardapio);
    }

    @PutMapping("/update")
    public ResponseEntity<Cardapio> update(@Valid @RequestBody Cardapio requestBody) {

        List<Cardapio> cardapioList = cardapioRepository.findAll();

        for (Cardapio cardapioTEMP : cardapioList) {
            if (cardapioTEMP.getCardapioId() == requestBody.getCardapioId()) {
                cardapioRepository.save(requestBody);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Cardapio> delete(@Valid @RequestBody Map<String, Object> requestBody) {

        List<Cardapio> cardapioList = cardapioRepository.findAll();

        Object idObject = requestBody.get("cardapioId");
        if (idObject instanceof Integer) {
            idObject = ((Integer) idObject).longValue();
        }

        for (Cardapio cardapioTEMP : cardapioList) {
            if (cardapioTEMP.getCardapioId().equals(idObject)) {
                cardapioRepository.delete(cardapioTEMP);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
