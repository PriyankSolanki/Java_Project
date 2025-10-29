package altn72.projet.controllers;

import altn72.projet.dto.ImportCsvReport;
import altn72.projet.services.ApprentiImportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/apprentis")
public class ApprentiImportController {

    private final ApprentiImportService importService;

    public ApprentiImportController(ApprentiImportService importService) {
        this.importService = importService;
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImportCsvReport> importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        ImportCsvReport report = importService.importCsv(file);
        // 200 OK avec un rapport détaillé (succès/erreurs par ligne)
        return ResponseEntity.ok(report);
    }
}
