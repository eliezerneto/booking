package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.CreateUpdateGuestDTO;
import br.com.booking.corebookingapi.dto.GuestDTO;
import br.com.booking.corebookingapi.entity.Guest;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.GuestMapper;
import br.com.booking.corebookingapi.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private MessageSource messageSource;

    public Page<GuestDTO> findAll(Pageable pageable) {
        Page<Guest> pageGuests = guestRepository.findAll(pageable);

        if (pageGuests.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return pageGuests.map(guestMapper::toDto);
    }

    public GuestDTO findGuestById(Long id) {
        Optional<Guest> guest = guestRepository.findById(id);

        if (!guest.isPresent()) {
            throw new RecordNotFoundException();
        }

        return guestMapper.toDto(guest.get());
    }

    public GuestDTO createGuest(CreateUpdateGuestDTO createUpdateGuestDTO) {
        Guest guest = guestMapper.toEntity(createUpdateGuestDTO);
        return guestMapper.toDto(guestRepository.save(guest));
    }

    public GuestDTO updateGuest(Long id, CreateUpdateGuestDTO createUpdateGuestDTO) {
        Optional<Guest> oldGuest = guestRepository.findById(id);

        if (!oldGuest.isPresent()) {
            throw new RecordNotFoundException();
        }

        Guest guest = oldGuest.get();
        guestMapper.updateGuestFromDto(createUpdateGuestDTO, guest);

        return guestMapper.toDto(guestRepository.save(guest));
    }


}
