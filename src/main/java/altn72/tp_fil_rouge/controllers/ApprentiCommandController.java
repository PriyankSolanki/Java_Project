package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.dto.ApprentiCreateRequest;
import altn72.tp_fil_rouge.dto.ApprentiDto;
import altn72.tp_fil_rouge.dto.ApprentiMapper;
import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.services.ApprentiCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/apprentis")
public class ApprentiCommandController {

    private final ApprentiCommandService service;

    public ApprentiCommandController(ApprentiCommandService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApprentiDto> create(@RequestBody @Valid ApprentiCreateRequest req) {
        Apprenti created = service.create(req);
        ApprentiDto dto = ApprentiMapper.toDto(created);
        return ResponseEntity
                .created(URI.create("/api/apprentis/" + dto.id()))  // Location header
                .body(dto);
    }
}
