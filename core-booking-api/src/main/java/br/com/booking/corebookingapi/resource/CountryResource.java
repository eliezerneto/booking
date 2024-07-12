package br.com.booking.corebookingapi.resource;

import br.com.booking.corebookingapi.dto.CreateUpdateCountryDTO;
import br.com.booking.corebookingapi.dto.CountryDTO;
import br.com.booking.corebookingapi.entity.Country;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import br.com.booking.corebookingapi.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/countries", consumes = "application/json", produces = "application/json")
public class CountryResource {

    @Autowired
    private CountryService countryService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<ResponseDTO> findAllCountries(Pageable pageable) {
        Page<Country> pageCountries = countryService.findAll(pageable);

        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                pageCountries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findCountryById(@PathVariable(value = "id") Long id) {
        CountryDTO countryDTO = countryService.findCountryById(id);
        return StandardResponse.generateResponse(
                null,
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                countryDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createCountry(@Valid @RequestBody CreateUpdateCountryDTO createUpdateCountryDTO) {
        CountryDTO countryDTO = countryService.createCountry(createUpdateCountryDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("country.create.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.CREATED,
                countryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateCountry(@PathVariable(value = "id") Long id,
                                                     @Valid @RequestBody CreateUpdateCountryDTO createUpdateCountryDTO) {
        CountryDTO countryDTO = countryService.updateCountry(id, createUpdateCountryDTO);
        return StandardResponse.generateResponse(
                messageSource.getMessage("country.update.success.message", null, null),
                null,
                ResponseType.SUCCESS,
                HttpStatus.OK,
                countryDTO);
    }


}
