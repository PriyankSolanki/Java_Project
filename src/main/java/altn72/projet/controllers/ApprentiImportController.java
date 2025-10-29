package altn72.projet.controllers;

import altn72.projet.dto.ImportCsvReport;
import altn72.projet.services.ApprentiImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/apprentis")
public class ApprentiImportController {

    @Autowired
    private  ApprentiImportService importService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImportCsvReport> importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        ImportCsvReport report = importService.importCsv(file);
        return ResponseEntity.ok(report);
    }
}
