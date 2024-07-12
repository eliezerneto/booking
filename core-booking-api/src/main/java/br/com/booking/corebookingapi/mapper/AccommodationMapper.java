package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.AccommodationDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateAccommodationDTO;
import br.com.booking.corebookingapi.entity.Accommodation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface AccommodationMapper {

    AccommodationDTO toDto(Accommodation accommodation);

    Accommodation toEntity(CreateUpdateAccommodationDTO createUpdateAccommodationDTO);

    List<AccommodationDTO> toDtoList(List<Accommodation> accommodations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccommodationFromDto(CreateUpdateAccommodationDTO createUpdateAccommodationDTO, @MappingTarget Accommodation accommodation);
}
