package altn72.projet.controllers;

import altn72.projet.dto.ApprentiDto;
import altn72.projet.dto.ApprentiMapper;
import altn72.projet.entities.Apprenti;
import altn72.projet.services.AffectationApprentiMaitre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AffectationApprentiMaitreController {

    private final AffectationApprentiMaitre affectationService;

    public AffectationApprentiMaitreController(AffectationApprentiMaitre affectationService) {
        this.affectationService = affectationService;
    }

    @PutMapping("/maitres/{maitreId}/apprentis/{apprentiId}")
    public ResponseEntity<ApprentiDto> affecter(
            @PathVariable Long maitreId,
            @PathVariable Long apprentiId) {
        Apprenti updated = affectationService.affecterApprentiAuMaitre(apprentiId, maitreId);
        return ResponseEntity.ok(ApprentiMapper.toDto(updated));
    }
}
