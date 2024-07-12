package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.CreateUpdateRoomDTO;
import br.com.booking.corebookingapi.dto.RoomDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/rooms", consumes = "application/json", produces = "application/json")
public class RoomResource {

    @Autowired
    private RoomService roomService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllRooms(Pageable pageable) {
        Page<RoomDTO> pageRooms = roomService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageRooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findRoomById(@PathVariable(value = "id") Long id) {
        RoomDTO roomDTO = roomService.findRoomById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                roomDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createRoom(@Valid @RequestBody CreateUpdateRoomDTO createUpdateRoomDTO) {
        RoomDTO roomDTO = roomService.createRoom(createUpdateRoomDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("room.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.CREATED,
                roomDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateRoom(@PathVariable(value = "id") Long id,
                                                  @Valid @RequestBody CreateUpdateRoomDTO createUpdateRoomDTO) {
        RoomDTO roomDTO = roomService.updateRoom(id, createUpdateRoomDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("room.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                roomDTO);
    }

}
