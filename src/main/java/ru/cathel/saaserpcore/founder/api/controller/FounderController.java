package ru.cathel.saaserpcore.founder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cathel.saaserpcore.founder.dto.FounderResponseDto;
import ru.cathel.saaserpcore.founder.service.FounderService;

@RestController
@RequestMapping("/api/v1/founders")
public class FounderController {
    private final FounderService founderService;

    @Autowired
    public FounderController(FounderService founderService) {
        this.founderService = founderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FounderResponseDto> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(founderService.getById(id));
    }
}
