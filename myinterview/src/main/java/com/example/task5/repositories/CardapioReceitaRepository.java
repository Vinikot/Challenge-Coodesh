package com.example.task5.repositories;

import com.example.task5.models.CardapioReceita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioReceitaRepository extends JpaRepository<CardapioReceita, CardapioReceita.CardapioReceitaId> {
}
