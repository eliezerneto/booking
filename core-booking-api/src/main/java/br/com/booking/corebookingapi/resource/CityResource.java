package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.CityDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateCityDTO;
import br.com.booking.corebookingapi.entity.City;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/cities", consumes = "application/json", produces = "application/json")
public class CityResource {

    @Autowired
    private CityService cityService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllCities(Pageable pageable) {
        Page<City> pageCities = cityService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageCities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findCityById(@PathVariable(value = "id") Long id) {
        CityDTO cityDTO = cityService.findCityById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                cityDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createCity(@Valid @RequestBody CreateUpdateCityDTO createUpdateCityDTO) {
        CityDTO cityDTO = cityService.createCity(createUpdateCityDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("city.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.CREATED,
                cityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateCity(@PathVariable(value = "id") Long id,
                                                  @Valid @RequestBody CreateUpdateCityDTO createUpdateCityDTO) {
        CityDTO cityDTO = cityService.updateCity(id, createUpdateCityDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("city.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                cityDTO);
    }

}
