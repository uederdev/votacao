package br.com.ueder.votacao_api.controllers.v1;

import br.com.ueder.votacao_api.domains.Cargo;
import br.com.ueder.votacao_api.dto.CargoDTO;
import br.com.ueder.votacao_api.exceptions.MensagemDTO;
import br.com.ueder.votacao_api.services.CargoService;
import br.com.ueder.votacao_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        List<CargoDTO> cargos = cargoService.findAll()
                    .stream().map(CargoDTO::new).toList();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listById(@PathVariable String id){
        Cargo cargoEncontrado = cargoService.findById(id);
        return ResponseEntity.ok(new CargoDTO(cargoEncontrado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id, HttpServletRequest request){
        cargoService.delete(new CargoDTO(Long.parseLong(id), ""));
        return ResponseEntity.ok().body(new MensagemDTO("Deletado com sucesso", LocalDateTime.now(), request.getServletPath() ));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CargoDTO entity){
        Cargo cargo = cargoService.save(entity);
        URI uri = Util.getURI("/apis/v1/cargos/{id}", cargo.getId());
        return ResponseEntity.created(uri).body(new CargoDTO(cargo));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid CargoDTO entity, HttpServletRequest request){
        Cargo cargoUpdated = cargoService.update(entity);
        return ResponseEntity.ok(new CargoDTO(cargoUpdated));
    }
}
