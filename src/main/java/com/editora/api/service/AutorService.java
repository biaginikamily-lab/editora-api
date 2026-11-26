package com.editora.api.service;

import com.editora.api.entity.Autor;
import com.editora.api.repository.ArtigoRepository;
import com.editora.api.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final ArtigoRepository artigoRepository;

    public AutorService(AutorRepository autorRepository, ArtigoRepository artigoRepository) {
        this.autorRepository = autorRepository;
        this.artigoRepository = artigoRepository;
    }

    public Autor salvar(Autor autor) {
        if (autorRepository.existsByEmail(autor.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe autor com esse e-mail");
        }
        autor.setId(null);
        return autorRepository.save(autor);
    }

    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public boolean deletar(Long id) {
        if (!autorRepository.existsById(id)) {
            return false;
        }
        if (artigoRepository.existsByAutorId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Autor possui artigos e não pode ser removido");
        }
        autorRepository.deleteById(id);
        return true;
    }
}