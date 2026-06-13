package ru.cathel.saaserpcore.founder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cathel.saaserpcore.auth.dto.FounderSignupRequestDto;
import ru.cathel.saaserpcore.db.entity.Founder;
import ru.cathel.saaserpcore.db.repository.FounderRepository;
import ru.cathel.saaserpcore.founder.dto.FounderResponseDto;
import ru.cathel.saaserpcore.founder.exception.EmailAlreadyExistsException;
import ru.cathel.saaserpcore.founder.exception.FounderNotFoundException;
import ru.cathel.saaserpcore.founder.mapper.FounderMapper;

@Service
public class FounderService {
    private final FounderRepository founderRepository;
    private final FounderMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FounderService(FounderRepository founderRepository,
                          FounderMapper mapper,
                          PasswordEncoder passwordEncoder) {
        this.founderRepository = founderRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public FounderResponseDto register(FounderSignupRequestDto signupDto) {
        if (founderRepository.existsByEmail(signupDto.email()))
            throw new EmailAlreadyExistsException();

        Founder founder = new Founder();
        founder.setEmail(signupDto.email());
        founder.setPasswordHash(passwordEncoder.encode(signupDto.password()));
        return mapper.toDto(founderRepository.save(founder));
    }

    public FounderResponseDto getById(int id) {
        return mapper.toDto(founderRepository.findById(id)
                .orElseThrow(FounderNotFoundException::new)
        );
    }
}
