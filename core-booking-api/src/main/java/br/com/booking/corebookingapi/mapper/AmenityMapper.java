package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.AmenityDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateAmenityDTO;
import br.com.booking.corebookingapi.entity.Amenity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface AmenityMapper {
    AmenityDTO toDto(Amenity amenity);

    Amenity toEntity(CreateUpdateAmenityDTO createUpdateAmenityDTO);

    List<AmenityDTO> toDtoList(List<Amenity> amenities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAmenityFromDto(CreateUpdateAmenityDTO createUpdateAmenityDTO, @MappingTarget Amenity amenity);
}
