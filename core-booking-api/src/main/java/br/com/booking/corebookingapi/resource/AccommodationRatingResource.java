package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.AccommodationRatingDTO;
import br.com.booking.corebookingapi.entity.AccommodationRating;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.AccommodationRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/accommodation-ratings", consumes = "application/json", produces = "application/json")
public class AccommodationRatingResource {

    @Autowired
    private AccommodationRatingService accommodationRatingService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllAccommodationRatings(Pageable pageable) {
        Page<AccommodationRating> pageAccommodationRatings = accommodationRatingService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageAccommodationRatings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findAccommodationRatingById(@PathVariable(value = "id") Long id) {
        AccommodationRatingDTO accommodationRatingDTO = accommodationRatingService.findAccommodationRatingById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                accommodationRatingDTO);
    }

}
