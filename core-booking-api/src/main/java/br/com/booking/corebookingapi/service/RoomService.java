package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.CreateUpdateRoomDTO;
import br.com.booking.corebookingapi.dto.RoomDTO;
import br.com.booking.corebookingapi.entity.Room;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.RoomMapper;
import br.com.booking.corebookingapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<RoomDTO> findAll(Pageable pageable) {
        Page<Room> pageRooms = roomRepository.findAll(pageable);

        if (pageRooms.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageRooms.map(roomMapper::toDto);
    }

    public RoomDTO findRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);

        if (!room.isPresent()) {
            throw new RecordNotFoundException();
        }

        return roomMapper.toDto(room.get());
    }

    public RoomDTO createRoom(CreateUpdateRoomDTO createUpdateRoomDTO) {
        Room room = roomMapper.toEntity(createUpdateRoomDTO);
        return roomMapper.toDto(roomRepository.save(room));
    }

    public RoomDTO updateRoom(Long id, CreateUpdateRoomDTO createUpdateRoomDTO) {
        Optional<Room> oldRoom = roomRepository.findById(id);

        if (!oldRoom.isPresent()) {
            throw new RecordNotFoundException();
        }

        Room room = oldRoom.get();
        roomMapper.updateRoomFromDto(createUpdateRoomDTO, room);

        return roomMapper.toDto(roomRepository.save(room));
    }

}
