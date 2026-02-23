package br.com.ueder.votacao_api.controllers.v1;

import br.com.ueder.votacao_api.domains.Setor;
import br.com.ueder.votacao_api.dto.SetorDTO;
import br.com.ueder.votacao_api.exceptions.MensagemDTO;
import br.com.ueder.votacao_api.repositories.CargoRepository;
import br.com.ueder.votacao_api.services.SetorService;
import br.com.ueder.votacao_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @GetMapping
    public ResponseEntity<List<SetorDTO>> listAll(){
        List<SetorDTO> setores = setorService.findAll().stream().map(SetorDTO::new).toList();
        return ResponseEntity.ok(setores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorDTO> listById(@PathVariable Long id){
        Setor setor = setorService.findById(String.valueOf(id));
        return ResponseEntity.ok(new SetorDTO(setor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id, HttpServletRequest request){
        setorService.delete(new SetorDTO(id, ""));
        return ResponseEntity.ok(
                new MensagemDTO("Deletado com sucesso!", LocalDateTime.now(), request.getServletPath())
        );
    }

    @PostMapping
    public ResponseEntity<SetorDTO> create(@RequestBody @Valid SetorDTO setorDTO){
        Setor setorNew = setorService.save(setorDTO);
        URI uri = Util.getURI("/apis/v1/setores/{id}", setorNew.getId());
        return ResponseEntity.created(uri).body(new SetorDTO(setorNew));
    }

    @PutMapping
    public ResponseEntity<SetorDTO> update(@RequestBody @Valid SetorDTO setorDTO){
        Setor setorUpdated = setorService.update(setorDTO);
        return ResponseEntity.ok().body(new SetorDTO(setorUpdated));
    }
}
