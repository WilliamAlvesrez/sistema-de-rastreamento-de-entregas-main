package br.edu.iftm.rastreamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.repository.EnderecoRepository;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.service.util.Converters;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private Converters converters;

    public List<PacoteDTO> getAllPacotes() {
        Iterable<Pacote> pacotesIterable = pacoteRepository.findAll();
        List<Pacote> pacotesList = new ArrayList<>();
        pacotesIterable.forEach(pacotesList::add);
        return pacotesList.stream().map((pacote) -> converters.convertToDTO(pacote)).collect(Collectors.toList());
    }

    public PacoteDTO getPacoteById(Long id) {
        Pacote pacote = pacoteRepository.findById(id).get();
        return converters.convertToDTO(pacote);
    }

    public PacoteDTO createPacote(PacoteDTO pacoteDTO) {
        Endereco endereco = enderecoRepository.findById(pacoteDTO.getEndereco().getId()).get();
        Pacote pacote = converters.convertToEntity(pacoteDTO);
        pacote.setEndereco(endereco);
        Pacote savedPacote = pacoteRepository.save(pacote);
        return converters.convertToDTO(savedPacote);
    }

    public PacoteDTO updatePacote(Long id, PacoteDTO pacoteDTO) {
        Pacote pacote = converters.convertToEntity(pacoteDTO);
        pacote.setId(id);
        Pacote updatedPacote = pacoteRepository.save(pacote);
        return converters.convertToDTO(updatedPacote);
    }

    public List<PacoteDTO> buscarPacotesPorStatus(String status) {
        List<Pacote> pacotes = pacoteRepository.findByStatus(status);
        return pacotes.stream().map(converters::convertToDTO).collect(Collectors.toList());
    }

    public List<PacoteDTO> buscarPacotesPorDestinatario(String destinatario) {
        List<Pacote> pacotes = pacoteRepository.findByDestinatario(destinatario);
        return pacotes.stream().map(converters::convertToDTO).collect(Collectors.toList());
    }

}