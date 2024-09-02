package br.edu.iftm.rastreamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import br.edu.iftm.rastreamento.service.util.Converters;

@RestControllerAdvice
public class ExceptionsController {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private Converters converters;

    public PacoteDTO getPacoteById(Long id) {
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new NaoAcheiException("Pacote não encontrado"));
        return converters.convertToDTO(pacote);
    }

    public PacoteDTO updatePacote(Long id, PacoteDTO pacoteDTO) {
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new NaoAcheiException("Pacote não encontrado"));
        pacote.setId(id);
        Pacote updatedPacote = pacoteRepository.save(pacote);
        return converters.convertToDTO(updatedPacote);
    }

	@ExceptionHandler(NaoAcheiException.class)
    public ResponseEntity<String> handleNaoAcheiException(NaoAcheiException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}