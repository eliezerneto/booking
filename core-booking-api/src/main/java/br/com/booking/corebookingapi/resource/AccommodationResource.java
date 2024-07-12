package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.AccommodationDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateAccommodationDTO;
import br.com.booking.corebookingapi.entity.Accommodation;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.AccommodationService;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/accommodations", consumes = "application/json", produces = "application/json")
public class AccommodationResource {

    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> findAllAccommodation(Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageAccommodation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> findAccommodationById(@PathVariable(value = "id") Long id) {
        AccommodationDTO accommodationDTO = accommodationService.findAccommodationById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                accommodationDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> createAccommodation(@Valid @RequestBody CreateUpdateAccommodationDTO createUpdateAccommodationDTO) {
        AccommodationDTO accommodationDTO = accommodationService.createAccommodation(createUpdateAccommodationDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("accommodation.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                accommodationDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDTO> updateAccommodation(@PathVariable(value = "id") Long id,
                                                           @Valid @RequestBody CreateUpdateAccommodationDTO createUpdateAccommodationDTO) {
        AccommodationDTO accommodationDTO = accommodationService.updateAccommodation(id, createUpdateAccommodationDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("accommodation.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                accommodationDTO);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> searchAccommodation(
            @Join(path = "address", alias = "a")
            @Join(path = "a.city", alias = "c") @And({
                    @Spec(path = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "type", paramSeparator = ',', spec = In.class),
                    @Spec(path = "c.name", params = "city", spec = LikeIgnoreCase.class)
            }) Specification<Accommodation> accommodationSpec, Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationService.findAll(accommodationSpec, pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageAccommodation);
    }

    @RequestMapping(value = "/search/availability/date", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> searchAccommodationByDate(
            @RequestParam(value = "checkIn", required = true) LocalDate checkIn,
            @RequestParam(value = "checkOut", required = true) LocalDate checkOut,
            @RequestParam(value = "guestsQuantity", required = true) Integer guestsQuantity,
            @RequestParam(value = "city", required = true) String city,
            Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationService.findAccommodationByDateAvailability(checkIn, checkOut, guestsQuantity, city, pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageAccommodation);
    }

    @RequestMapping(value = "/verify/{idAccommodation}/availability/date", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> verifyAccommodation(
            @PathVariable(value = "idAccommodation", required = true) Long idAccommodation,
            @RequestParam(value = "checkIn", required = true) LocalDate checkIn,
            @RequestParam(value = "checkOut", required = true) LocalDate checkOut,
            @RequestParam(value = "guestsQuantity", required = true) Integer guestsQuantity) {

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                accommodationService.verifyAccommodationByDateAvailability(idAccommodation, checkIn, checkOut, guestsQuantity));
    }

    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> searchAccommodationByDate(
            @RequestParam("firstIdAccommodation") Long firstIdAccommodation,
            @RequestParam("secondIdAccommodation") Long secondIdAccommodation,
            Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationService.compareAccommodation(firstIdAccommodation, secondIdAccommodation, pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageAccommodation);
    }

}
