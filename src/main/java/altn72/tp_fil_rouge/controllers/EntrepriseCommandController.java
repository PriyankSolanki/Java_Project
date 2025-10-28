package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.dto.EntrepriseCreateRequest;
import altn72.tp_fil_rouge.dto.EntrepriseDto;
import altn72.tp_fil_rouge.dto.EntrepriseMapper;
import altn72.tp_fil_rouge.entities.Entreprise;
import altn72.tp_fil_rouge.services.EntrepriseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseCommandController {

    private final EntrepriseService service;

    public EntrepriseCommandController(EntrepriseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EntrepriseDto> create(@RequestBody @Valid EntrepriseCreateRequest req) {
        Entreprise created = service.create(req);
        EntrepriseDto dto = EntrepriseMapper.toDto(created);
        return ResponseEntity
                .created(URI.create("/api/entreprises/" + dto.id()))
                .body(dto);
    }

    @GetMapping
    public List<EntrepriseDto> list() {
        return service.findAll().stream().map(EntrepriseMapper::toDto).toList();
    }
}
