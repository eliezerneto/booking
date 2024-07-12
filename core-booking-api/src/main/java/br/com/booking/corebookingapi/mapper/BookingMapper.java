package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.BookingDTO;
import br.com.booking.corebookingapi.dto.CreateUpdateBookingDTO;
import br.com.booking.corebookingapi.entity.Booking;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface BookingMapper {

    BookingDTO toDto(Booking booking);

    Booking toEntity(CreateUpdateBookingDTO createUpdateBookingDTO);

    List<BookingDTO> toDtoList(List<Booking> bookings);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookingFromDto(CreateUpdateBookingDTO createUpdateBookingDTO, @MappingTarget Booking booking);
}
