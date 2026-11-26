package com.editora.api.controller;

import com.editora.api.entity.Artigo;
import com.editora.api.entity.Autor;
import com.editora.api.service.ArtigoService;
import com.editora.api.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;
    private final ArtigoService artigoService;

    public AutorController(AutorService autorService, ArtigoService artigoService) {
        this.autorService = autorService;
        this.artigoService = artigoService;
    }

    @PostMapping
    public ResponseEntity<Autor> cadastrar(@Valid @RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvar(autor));
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.ok(autorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/artigos")
    public ResponseEntity<List<Artigo>> artigosDoAutor(@PathVariable Long id) {
        if (autorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artigoService.listarPorAutor(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return autorService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}