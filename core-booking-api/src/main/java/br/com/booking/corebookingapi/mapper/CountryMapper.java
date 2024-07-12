package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.CountryDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateCountryDTO;
import br.com.booking.corebookingapi.entity.Country;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface CountryMapper {

    CountryDTO toDto(Country country);

    Country toEntity(CreateUpdateCountryDTO createUpdateCountryDTO);

    List<CountryDTO> toDtoList(List<Country> countries);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCountryFromDto(CreateUpdateCountryDTO createUpdateCountryDTO, @MappingTarget Country country);
}
