package altn72.projet.controllers;

import altn72.projet.dto.EntrepriseCreateRequest;
import altn72.projet.dto.EntrepriseDto;
import altn72.projet.dto.EntrepriseMapper;
import altn72.projet.entities.Entreprise;
import altn72.projet.services.EntrepriseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseCommandController {

    @Autowired
    private EntrepriseService service;
    
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
