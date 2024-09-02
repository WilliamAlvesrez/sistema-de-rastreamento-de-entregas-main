package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public List<EnderecoDTO> getAllEnderecos() {
		return enderecoService.getAllEnderecos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id) {
		EnderecoDTO enderecoDTO = enderecoService.getEnderecoById(id);
		return ResponseEntity.ok(enderecoDTO);
	}

	@PostMapping
	public EnderecoDTO createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		return enderecoService.createEndereco(enderecoDTO);
	}

	@PutMapping("/{id}")
	public EnderecoDTO updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
		return enderecoService.updateEndereco(id, enderecoDTO);
	}
}