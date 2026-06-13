package ru.cathel.saaserpcore.auth.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cathel.saaserpcore.auth.dto.FounderLoginRequestDto;
import ru.cathel.saaserpcore.auth.dto.FounderSignupRequestDto;
import ru.cathel.saaserpcore.founder.dto.FounderResponseDto;
import ru.cathel.saaserpcore.founder.service.FounderService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final FounderService founderService;

    @Autowired
    public AuthController(FounderService founderService) {
        this.founderService = founderService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid
                                    FounderSignupRequestDto signupRequest) {
        FounderResponseDto responseDto = founderService.register(signupRequest);
        //TODO: send to email verification message
        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/v1/founders/{id}")
                        .buildAndExpand(responseDto.id())
                        .toUri())
                .body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid
                                   FounderLoginRequestDto loginRequest) {
        //TODO: login founder (founder service), return JWT
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        //TODO: logout founder (clear cookies)
        return ResponseEntity.ok().build();
    }
}
