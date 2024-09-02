package br.edu.iftm.rastreamento.controller;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

	@Autowired
	private PacoteService pacoteService;

	@GetMapping
	public List<PacoteDTO> getAllPacotes() {
		return pacoteService.getAllPacotes();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PacoteDTO> getPacoteById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(pacoteService.getPacoteById(id));
		} catch (NaoAcheiException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping
	public PacoteDTO createPacote(@RequestBody PacoteDTO pacoteDTO) {
		return pacoteService.createPacote(pacoteDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PacoteDTO> updatePacote(@PathVariable Long id, @RequestBody PacoteDTO pacoteDTO) {
		try {
			PacoteDTO updatedPacote = pacoteService.updatePacote(id, pacoteDTO);
			return ResponseEntity.ok(updatedPacote);
		} catch (NaoAcheiException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}