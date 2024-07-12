package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.AmenityDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateAmenityDTO;
import br.com.booking.corebookingapi.entity.Amenity;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.AmenityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/amenities", consumes = "application/json", produces = "application/json")
public class AmenityResource {

    @Autowired
    private AmenityService amenityService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllAmenities(Pageable pageable) {
        Page<Amenity> pageAmenities = amenityService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageAmenities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findAmenityById(@PathVariable(value = "id") Long id) {
        AmenityDTO amenityDTO = amenityService.findAmenityById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                amenityDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createAmenity(@Valid @RequestBody CreateUpdateAmenityDTO createUpdateAmenityDTO) {
        AmenityDTO amenityDTO = amenityService.createAmenity(createUpdateAmenityDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("amenity.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.CREATED,
                amenityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateAmenity(@PathVariable(value = "id") Long id,
                                                     @Valid @RequestBody CreateUpdateAmenityDTO createUpdateAmenityDTO) {
        AmenityDTO amenityDTO = amenityService.updateAmenity(id, createUpdateAmenityDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("amenity.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                amenityDTO);
    }


}
