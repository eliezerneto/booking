package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.CreateUpdateRoomPriceDTO;
import br.com.booking.corebookingapi.dto.RoomPriceDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.RoomPriceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/room-prices", consumes = "application/json", produces = "application/json")
public class RoomPriceResource {

    @Autowired
    private RoomPriceService roomPriceService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllRoomPrices(Pageable pageable) {
        Page<RoomPriceDTO> pageRoomPrices = roomPriceService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageRoomPrices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findRoomPriceById(@PathVariable(value = "id") Long id) {
        RoomPriceDTO roomPriceDTO = roomPriceService.findRoomPriceById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                roomPriceDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createRoomPrice(@Valid @RequestBody CreateUpdateRoomPriceDTO createUpdateRoomPriceDTO) {
        RoomPriceDTO roomPriceDTO = roomPriceService.createRoomPrice(createUpdateRoomPriceDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("room.price.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.CREATED,
                roomPriceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateRoomPrice(@PathVariable(value = "id") Long id,
                                                  @Valid @RequestBody CreateUpdateRoomPriceDTO createUpdateRoomPriceDTO) {
        RoomPriceDTO roomPriceDTO = roomPriceService.updateRoomPrice(id, createUpdateRoomPriceDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("room.price.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                roomPriceDTO);
    }


}
