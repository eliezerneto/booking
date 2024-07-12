package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.AccommodationDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateAccommodationDTO;
import br.com.booking.corebookingapi.entity.Accommodation;
import br.com.booking.corebookingapi.enums.AccommodationType;
import br.com.booking.corebookingapi.enums.BookingStatus;
import br.com.booking.corebookingapi.exception.custom.BusinessException;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.AccommodationMapper;
import br.com.booking.corebookingapi.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationMapper accommodationMapper;

    @Autowired
    private MessageSource messageSource;

    public Page<Accommodation> findAll(Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationRepository.findAll(pageable);
        if (pageAccommodation.isEmpty()) {
            throw new RecordNotFoundException();
        }
        return pageAccommodation;
    }

    public Page<Accommodation> findAll(Specification<Accommodation> accommodationSpec, Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationRepository.findAll(accommodationSpec, pageable);
        if (pageAccommodation.isEmpty()) {
            throw new RecordNotFoundException();
        }
        return pageAccommodation;
    }

    public AccommodationDTO findAccommodationById(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (!accommodation.isPresent()) {
            throw new RecordNotFoundException();
        }
        return accommodationMapper.toDto(accommodation.get());
    }

    public Boolean verifyAccommodationByDateAvailability(Long idAccommodation, LocalDate checkIn, LocalDate checkOut, Integer guestsQuantity) {
        Optional<Accommodation> accommodation = accommodationRepository.verifyByDateAvailability(idAccommodation, checkIn, checkOut, guestsQuantity);
        if (!accommodation.isPresent()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Page<Accommodation> findAccommodationByDateAvailability(LocalDate checkIn, LocalDate checkOut, Integer guestsQuantity, String city, Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationRepository.findByDateAvailability(checkIn, checkOut, guestsQuantity, city, pageable);
        if (pageAccommodation.isEmpty()) {
            throw new RecordNotFoundException();
        }
        return pageAccommodation;
    }

    public Page<Accommodation> compareAccommodation(Long firstIdAccommodation, Long secondIdAccommodation, Pageable pageable) {
        Page<Accommodation> pageAccommodation = accommodationRepository.compareAccommodation(firstIdAccommodation, secondIdAccommodation, pageable);
        if (pageAccommodation.isEmpty()) {
            throw new RecordNotFoundException();
        }
        return pageAccommodation;
    }

    public AccommodationDTO createAccommodation(CreateUpdateAccommodationDTO createUpdateAccommodationDTO) {
        Accommodation accommodation = accommodationMapper.toEntity(createUpdateAccommodationDTO);
        return accommodationMapper.toDto(accommodationRepository.save(accommodation));
    }

    public AccommodationDTO updateAccommodation(Long id, CreateUpdateAccommodationDTO createUpdateAccommodationDTO) {
        Optional<Accommodation> oldAccommodation = accommodationRepository.findById(id);
        if (!oldAccommodation.isPresent()) {
            throw new RecordNotFoundException();
        }
        Accommodation accommodation = oldAccommodation.get();
        if (accommodation.getType() != null && accommodation.getType() == AccommodationType.HOTEL) {
            throw new BusinessException(
                    messageSource.getMessage("accommodation.updatehotel.exception.code", null, null),
                    messageSource.getMessage("accommodation.updatehotel.exception.message", null, null));
        }
        accommodationMapper.updateAccommodationFromDto(createUpdateAccommodationDTO, accommodation);
        return accommodationMapper.toDto(accommodationRepository.save(accommodation));
    }

}

