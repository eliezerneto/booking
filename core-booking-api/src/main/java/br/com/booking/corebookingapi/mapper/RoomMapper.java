package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.CreateUpdateRoomDTO;
import br.com.booking.corebookingapi.dto.RoomDTO;
import br.com.booking.corebookingapi.entity.Room;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomDTO toDto(Room room);

    Room toEntity(CreateUpdateRoomDTO createUpdateRoomDTO);

    List<RoomDTO> toDtoList(List<Room> rooms);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoomFromDto(CreateUpdateRoomDTO createUpdateRoomDTO, @MappingTarget Room room);
}
