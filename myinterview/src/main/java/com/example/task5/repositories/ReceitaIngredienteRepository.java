package com.example.task5.repositories;

import com.example.task5.models.ReceitaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, ReceitaIngrediente.ReceitaIngredienteId> {
}
