package com.editora.api.service;

import com.editora.api.entity.Artigo;
import com.editora.api.entity.Autor;
import com.editora.api.repository.ArtigoRepository;
import com.editora.api.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService {

    private final ArtigoRepository artigoRepository;
    private final AutorRepository autorRepository;

    public ArtigoService(ArtigoRepository artigoRepository, AutorRepository autorRepository) {
        this.artigoRepository = artigoRepository;
        this.autorRepository = autorRepository;
    }

    public Artigo salvar(Artigo artigo) {
        if (artigo.getAutor() == null || artigo.getAutor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o id do autor");
        }
        Autor autor = autorRepository.findById(artigo.getAutor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
        artigo.setId(null);
        artigo.setAutor(autor);
        return artigoRepository.save(artigo);
    }

    public List<Artigo> listar() {
        return artigoRepository.findAll();
    }

    public Optional<Artigo> buscarPorId(Long id) {
        return artigoRepository.findById(id);
    }

    public List<Artigo> listarPorAutor(Long autorId) {
        return artigoRepository.findByAutorId(autorId);
    }
}