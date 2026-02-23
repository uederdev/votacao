package br.com.ueder.votacao_api.controllers.v1;

import br.com.ueder.votacao_api.converters.PessoaConverter;
import br.com.ueder.votacao_api.domains.Pessoa;
import br.com.ueder.votacao_api.dto.*;
import br.com.ueder.votacao_api.exceptions.MensagemDTO;
import br.com.ueder.votacao_api.services.PessoaService;
import br.com.ueder.votacao_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listAll(){
        List<PessoaDTO> pessoas = pessoaService.findAll().stream().map(PessoaDTO::new).toList();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> listById(@PathVariable Integer id){
        Pessoa pessoa = pessoaService.findById(String.valueOf(id));
        return ResponseEntity.ok(new PessoaDTO(pessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid DadosPessoaDTO entity) {
        PessoaDTO pessoaDTO = entity.toPessoaDTO();
        Pessoa pessoaNew = pessoaService.save(pessoaDTO);
        URI uri = Util.getURI("/apis/v1/pessoas/{id}",  pessoaNew.getId());
        return ResponseEntity.created(uri).body(new PessoaDTO(pessoaNew));
    }

    @PutMapping
    public ResponseEntity<PessoaDTO> update(@RequestBody @Valid AtualizaDadosPessoaDTO entity) {
        Pessoa pessoaNew = pessoaService.update(entity);
        return ResponseEntity.ok(new PessoaDTO(pessoaNew));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
        pessoaService.delete(id);
        MensagemDTO mensagem = new MensagemDTO("Deletado com sucesso!",
                                                LocalDateTime.now(), request.getServletPath());
        return ResponseEntity.ok(mensagem);
    }
}
