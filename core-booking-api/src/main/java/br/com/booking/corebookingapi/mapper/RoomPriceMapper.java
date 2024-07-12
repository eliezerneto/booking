package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.CreateUpdateRoomPriceDTO;
import br.com.booking.corebookingapi.dto.RoomPriceDTO;
import br.com.booking.corebookingapi.entity.RoomPrice;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface RoomPriceMapper {

    RoomPriceDTO toDto(RoomPrice roomPrice);

    RoomPrice toEntity(CreateUpdateRoomPriceDTO createUpdateRoomPriceDTO);

    List<RoomPriceDTO> toDtoList(List<RoomPrice> roomPrices);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoomPriceFromDto(CreateUpdateRoomPriceDTO createUpdateRoomPriceDTO, @MappingTarget RoomPrice roomPrice);
}
