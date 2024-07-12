package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.BookingDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateBookingDTO;
import br.com.booking.corebookingapi.entity.Booking;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.BookingService;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/bookings", consumes = "application/json", produces = "application/json")
public class BookingResource {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> findAllBookings(Pageable pageable) {
        Page<Booking> pageBookingDTO = bookingService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageBookingDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> findBookingById(@PathVariable(value = "id") Long id) {
        BookingDTO bookingDTO = bookingService.findBookingById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                bookingDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> createBooking(@Valid @RequestBody CreateUpdateBookingDTO createUpdateBookingDTO) {
        BookingDTO bookingDTO = bookingService.createBooking(createUpdateBookingDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("booking.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                bookingDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDTO> updateBooking(@PathVariable(value = "id") Long id,
                                                     @Valid @RequestBody CreateUpdateBookingDTO createUpdateBookingDTO) {
        BookingDTO bookingDTO = bookingService.updateBooking(id, createUpdateBookingDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("booking.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                bookingDTO);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> searchBooking(@And({
            @Spec(path = "status", spec = Equal.class),
            @Spec(path = "guest.name", spec = Like.class),
            @Spec(path = "room.accommodation.name", spec = Like.class)
    }) Specification<Booking> bookingSpec, Pageable pageable) {
        Page<Booking> pageBookingDTO = bookingService.findAll(bookingSpec, pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageBookingDTO);
    }
}
