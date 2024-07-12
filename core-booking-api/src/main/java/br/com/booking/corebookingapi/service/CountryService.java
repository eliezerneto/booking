package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.CreateUpdateCountryDTO;
import br.com.booking.corebookingapi.dto.CountryDTO;
import br.com.booking.corebookingapi.entity.Country;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.CountryMapper;
import br.com.booking.corebookingapi.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<Country> findAll(Pageable pageable) {
        Page<Country> pageCountry = countryRepository.findAll(pageable);

        if (pageCountry.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageCountry;
    }

    public CountryDTO findCountryById(Long id) {
        Optional<Country> country = countryRepository.findById(id);

        if (!country.isPresent()) {
            throw new RecordNotFoundException();
        }

        return countryMapper.toDto(country.get());
    }

    public CountryDTO createCountry(CreateUpdateCountryDTO createUpdateCountryDTO) {
        Country country = countryMapper.toEntity(createUpdateCountryDTO);
        return countryMapper.toDto(countryRepository.save(country));
    }

    public CountryDTO updateCountry(Long id, CreateUpdateCountryDTO createUpdateCountryDTO) {
        Optional<Country> oldCountry = countryRepository.findById(id);

        if (!oldCountry.isPresent()) {
            throw new RecordNotFoundException();
        }

        Country country = oldCountry.get();
        countryMapper.updateCountryFromDto(createUpdateCountryDTO, country);

        return countryMapper.toDto(countryRepository.save(country));
    }


}
