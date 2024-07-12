package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.dto.BookingDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateBookingDTO;
import br.com.booking.corebookingapi.entity.Booking;
import br.com.booking.corebookingapi.entity.Guest;
import br.com.booking.corebookingapi.entity.Room;
import br.com.booking.corebookingapi.entity.RoomPrice;
import br.com.booking.corebookingapi.enums.BookingStatus;
import br.com.booking.corebookingapi.exception.custom.BusinessException;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.mapper.BookingMapper;
import br.com.booking.corebookingapi.repository.BookingRepository;
import br.com.booking.corebookingapi.repository.GuestRepository;
import br.com.booking.corebookingapi.repository.RoomPriceRepository;
import br.com.booking.corebookingapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomPriceRepository roomPriceRepository;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccommodationService accommodationService;

    public Page<Booking> findAll(Pageable pageable) {
        Page<Booking> pageBooking = bookingRepository.findAll(pageable);

        if (pageBooking == null || pageBooking.isEmpty())
            throw new RecordNotFoundException();

        return pageBooking;
    }

    public Page<Booking> findAll(Specification<Booking> bookingSpec, Pageable pageable) {
        Page<Booking> pageBooking = bookingRepository.findAll(bookingSpec, pageable);

        if (pageBooking == null || pageBooking.isEmpty())
            throw new RecordNotFoundException();

        return pageBooking;
    }

    public BookingDTO findBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);

        if (!booking.isPresent())
            throw new RecordNotFoundException();

        return bookingMapper.toDto(booking.get());
    }

    public BookingDTO createBooking(CreateUpdateBookingDTO createUpdateBookingDTO) {

        Optional<Room> optionalRoom = roomRepository.findById(createUpdateBookingDTO.getIdRoom());
        Optional<Guest> optionalGuest = guestRepository.findById(createUpdateBookingDTO.getGuestId());

        if(!optionalRoom.isPresent() && !optionalGuest.isPresent())
            throw new BusinessException("000", "Requisição inválida. Verifique Hóspede e Quarto");

        if(!accommodationService.verifyAccommodationByDateAvailability(
                optionalRoom.get().getAccommodation().getIdAccommodation(),
                createUpdateBookingDTO.getCheckInDate(),
                createUpdateBookingDTO.getCheckOutDate(),
                createUpdateBookingDTO.getGuestQuantity()))
            throw new BusinessException("000", "Não há disponibilidade de quartos para esta acomodação.");

        Booking booking = bookingMapper.toEntity(createUpdateBookingDTO);

        booking.setStatus(BookingStatus.CREATED);
        booking.setRoom(optionalRoom.get());
        booking.setGuest(optionalGuest.get());

        Optional<RoomPrice> optionalRoomPrice =
                roomPriceRepository.findByDate(createUpdateBookingDTO.getCheckInDate(),
                        createUpdateBookingDTO.getCheckOutDate());

        Double multiplier = 1.0;

        if(optionalRoomPrice.isPresent())
            multiplier = optionalRoomPrice.get().getPercentualChange();

        booking.setTotalPrice(optionalRoom.get().getRegularBasePrice() * multiplier);

        return bookingMapper.toDto(
                bookingRepository.save(booking));
    }

    public BookingDTO updateBooking(Long id, CreateUpdateBookingDTO createUpdateBookingDTO) {
        Optional<Booking> oldBooking = bookingRepository.findById(id);

        if (!oldBooking.isPresent())
            throw new RecordNotFoundException();

        Booking booking = oldBooking.get();

        bookingMapper.updateBookingFromDto(createUpdateBookingDTO, booking);

        return bookingMapper.toDto(bookingRepository.save(booking));
    }


}
