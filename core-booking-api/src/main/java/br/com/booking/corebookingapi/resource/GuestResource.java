package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.CreateUpdateGuestDTO;
import br.com.booking.corebookingapi.dto.GuestDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/guests", consumes = "application/json", produces = "application/json")
public class GuestResource {

    @Autowired
    private GuestService guestService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllGuests(Pageable pageable) {
        Page<GuestDTO> pageGuests = guestService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageGuests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findGuestById(@PathVariable(value = "id") Long id) {
        GuestDTO guestDTO = guestService.findGuestById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                guestDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createGuest(@Valid @RequestBody CreateUpdateGuestDTO createUpdateGuestDTO) {
        GuestDTO guestDTO = guestService.createGuest(createUpdateGuestDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("guest.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.CREATED,
                guestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateGuest(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody CreateUpdateGuestDTO createUpdateGuestDTO) {
        GuestDTO guestDTO = guestService.updateGuest(id, createUpdateGuestDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("guest.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                guestDTO);
    }


}
