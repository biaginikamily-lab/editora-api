package com.editora.api.repository;

import com.editora.api.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmail(String email);
}