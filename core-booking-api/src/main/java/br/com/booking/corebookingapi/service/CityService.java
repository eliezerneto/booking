package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.CreateUpdateCityDTO;
import br.com.booking.corebookingapi.dto.CityDTO;
import br.com.booking.corebookingapi.entity.City;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.CityMapper;
import br.com.booking.corebookingapi.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<City> findAll(Pageable pageable) {
        Page<City> pageCity = cityRepository.findAll(pageable);

        if (pageCity.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageCity;
    }

    public CityDTO findCityById(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if (!city.isPresent()) {
            throw new RecordNotFoundException();
        }

        return cityMapper.toDto(city.get());
    }

    public CityDTO createCity(CreateUpdateCityDTO createUpdateCityDTO) {
        City city = cityMapper.toEntity(createUpdateCityDTO);
        return cityMapper.toDto(cityRepository.save(city));
    }

    public CityDTO updateCity(Long id, CreateUpdateCityDTO createUpdateCityDTO) {
        Optional<City> oldCity = cityRepository.findById(id);

        if (!oldCity.isPresent()) {
            throw new RecordNotFoundException();
        }

        City city = oldCity.get();
        cityMapper.updateCityFromDto(createUpdateCityDTO, city);

        return cityMapper.toDto(cityRepository.save(city));
    }

}
