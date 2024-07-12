package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.CreateUpdateGuestDTO;
import br.com.booking.corebookingapi.dto.GuestDTO;
import br.com.booking.corebookingapi.entity.Guest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface GuestMapper {

    GuestDTO toDto(Guest guest);

    Guest toEntity(CreateUpdateGuestDTO createUpdateGuestDTO);

    List<GuestDTO> toDtoList(List<Guest> guests);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGuestFromDto(CreateUpdateGuestDTO createUpdateGuestDTO, @MappingTarget Guest guest);
}
