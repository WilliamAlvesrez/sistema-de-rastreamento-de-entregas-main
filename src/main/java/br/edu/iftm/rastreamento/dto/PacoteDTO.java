package br.edu.iftm.rastreamento.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PacoteDTO {
	private Long id;
	private String idUnico;
	private String destinatario;
	private EnderecoDTO endereco;
	private String status;
	private List<RastreamentoDTO> rastreamentos = new ArrayList<>();
}
