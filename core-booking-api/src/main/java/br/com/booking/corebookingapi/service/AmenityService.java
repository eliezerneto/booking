package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.AmenityDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateAmenityDTO;
import br.com.booking.corebookingapi.entity.Amenity;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.AmenityMapper;
import br.com.booking.corebookingapi.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;
    @Autowired
    private AmenityMapper amenityMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<Amenity> findAll(Pageable pageable) {
        Page<Amenity> pageAmenity = amenityRepository.findAll(pageable);

        if (pageAmenity.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageAmenity;
    }

    public AmenityDTO findAmenityById(Long id) {
        Optional<Amenity> amenity = amenityRepository.findById(id);

        if (!amenity.isPresent()) {
            throw new RecordNotFoundException();
        }

        return amenityMapper.toDto(amenity.get());
    }

    public AmenityDTO createAmenity(CreateUpdateAmenityDTO createUpdateAmenityDTO) {
        Amenity amenity = amenityMapper.toEntity(createUpdateAmenityDTO);
        return amenityMapper.toDto(amenityRepository.save(amenity));
    }

    public AmenityDTO updateAmenity(Long id, CreateUpdateAmenityDTO createUpdateAmenityDTO) {
        Optional<Amenity> oldAmenity = amenityRepository.findById(id);

        if (!oldAmenity.isPresent()) {
            throw new RecordNotFoundException();
        }

        Amenity amenity = oldAmenity.get();
        amenityMapper.updateAmenityFromDto(createUpdateAmenityDTO, amenity);

        return amenityMapper.toDto(amenityRepository.save(amenity));
    }

}
