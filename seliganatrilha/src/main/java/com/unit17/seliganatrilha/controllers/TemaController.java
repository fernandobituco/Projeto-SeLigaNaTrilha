package com.unit17.seliganatrilha.controllers;

import com.unit17.seliganatrilha.exceptions.TemaNaoEncontradoException;
import com.unit17.seliganatrilha.models.Tema;
import com.unit17.seliganatrilha.models.Trilha;
import com.unit17.seliganatrilha.service.TemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/tema")
public class TemaController {

    final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping
    public List<Tema> findAll() {
        return temaService.findAll();
    }

    @GetMapping("/{id}/trilhas")
    public Set<Trilha> findById(@PathVariable(value = "id") UUID id) {
        return temaService.findTrilhas(id);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody String tema) {
        try {
            temaService.save(tema);
        } catch (TemaNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<String> update(@PathVariable (value = "id") UUID id, @RequestBody String nome) {
        try {
            temaService.update(id, nome);
        } catch (TemaNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable (value = "id") UUID id) {
        try {
            temaService.delete(id);
        } catch (TemaNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Object> findByNome(@PathVariable(value = "nome") String nome) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(temaService.findByNome(nome));
        } catch (TemaNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
