package com.unit17.seliganatrilha.service;

import com.unit17.seliganatrilha.dtos.TrilhaDto;
import com.unit17.seliganatrilha.exceptions.UsuarioNaoEncontradoException;
import com.unit17.seliganatrilha.models.Trilha;
import com.unit17.seliganatrilha.repositories.TrilhaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrilhaServiceImpl implements TrilhaService {

    final TrilhaRepository trilhaRepository;

    public TrilhaServiceImpl(TrilhaRepository trilhaRepository) {
        this.trilhaRepository = trilhaRepository;
    }

    public List<Trilha> findAll() {
        return trilhaRepository.findAll();
    }

    @Transactional
    public void save(TrilhaDto novaTrilhaDtoDto) {
        Trilha novaTrilha = new Trilha(
                novaTrilhaDtoDto.getNome(),
                novaTrilhaDtoDto.getComentario(),
                novaTrilhaDtoDto.getStatus()
        );
        trilhaRepository.save(novaTrilha);
    }

    @Transactional
    public void update(UUID id, TrilhaDto novaTrilha) {
        Optional<Trilha> trilha = trilhaRepository.findById(id);
        if(trilha.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }
        Trilha antigaTrilha = trilha.get();
        antigaTrilha.setNome(novaTrilha.getNome());
        antigaTrilha.setComentario(novaTrilha.getComentario());
        antigaTrilha.setStatus(novaTrilha.getStatus());
    }

    @Transactional
    public void delete(UUID id) {
        if(trilhaRepository.findById(id).isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }
        trilhaRepository.deleteById(id);
    }
}