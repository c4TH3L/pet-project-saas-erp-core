package ru.cathel.saaserpcore.founder.mapper;

import org.mapstruct.Mapper;
import ru.cathel.saaserpcore.db.entity.Founder;
import ru.cathel.saaserpcore.founder.dto.FounderResponseDto;

@Mapper(componentModel = "spring")
public interface FounderMapper {
    FounderResponseDto toDto(Founder founder);
}
