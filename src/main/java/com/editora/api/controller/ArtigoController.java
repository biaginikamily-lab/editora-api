package com.editora.api.controller;

import com.editora.api.entity.Artigo;
import com.editora.api.service.ArtigoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artigos")
public class ArtigoController {

    private final ArtigoService artigoService;

    public ArtigoController(ArtigoService artigoService) {
        this.artigoService = artigoService;
    }

    @PostMapping
    public ResponseEntity<Artigo> cadastrar(@Valid @RequestBody Artigo artigo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artigoService.salvar(artigo));
    }

    @GetMapping
    public ResponseEntity<List<Artigo>> listar() {
        return ResponseEntity.ok(artigoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artigo> buscar(@PathVariable Long id) {
        return artigoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}