package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.AccommodationRatingDTO;
import br.com.booking.corebookingapi.entity.AccommodationRating;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.AccommodationRatingMapper;
import br.com.booking.corebookingapi.repository.AccommodationRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccommodationRatingService {

    @Autowired
    private AccommodationRatingRepository accommodationRatingRepository;
    @Autowired
    private AccommodationRatingMapper accommodationRatingMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<AccommodationRating> findAll(Pageable pageable) {
        Page<AccommodationRating> pageAccommodationRating = accommodationRatingRepository.findAll(pageable);

        if (pageAccommodationRating == null || pageAccommodationRating.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageAccommodationRating;
    }

    public AccommodationRatingDTO findAccommodationRatingById(Long id) {
        Optional<AccommodationRating> accommodationRating = accommodationRatingRepository.findById(id);

        if (!accommodationRating.isPresent()) {
            throw new RecordNotFoundException();
        }

        return accommodationRatingMapper.toDto(accommodationRating.get());
    }


}
