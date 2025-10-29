package altn72.projet.controllers;

import altn72.projet.dto.ApprentiCreateRequest;
import altn72.projet.dto.ApprentiDto;
import altn72.projet.dto.ApprentiMapper;
import altn72.projet.entities.Apprenti;
import altn72.projet.services.ApprentiCommandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/apprentis")
public class ApprentiCommandController {

    @Autowired
    private  ApprentiCommandService service;

    @PostMapping
    public ResponseEntity<ApprentiDto> create(@RequestBody @Valid ApprentiCreateRequest req) {
        Apprenti created = service.create(req);
        ApprentiDto dto = ApprentiMapper.toDto(created);
        return ResponseEntity
                .created(URI.create("/api/apprentis/" + dto.id()))
                .body(dto);
    }
}
