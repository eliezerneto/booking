package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.CityDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateCityDTO;
import br.com.booking.corebookingapi.entity.City;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface CityMapper {

    CityDTO toDto(City city);

    @Mapping(source = "createUpdateCityDTO.idCountry", target = "country.idCountry")
    City toEntity(CreateUpdateCityDTO createUpdateCityDTO);

    List<CityDTO> toDtoList(List<City> cities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCityFromDto(CreateUpdateCityDTO createUpdateCityDTO, @MappingTarget City city);
}
