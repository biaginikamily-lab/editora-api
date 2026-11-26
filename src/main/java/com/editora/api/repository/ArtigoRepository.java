package com.editora.api.repository;

import com.editora.api.entity.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    List<Artigo> findByAutorId(Long autorId);

    boolean existsByAutorId(Long autorId);
}