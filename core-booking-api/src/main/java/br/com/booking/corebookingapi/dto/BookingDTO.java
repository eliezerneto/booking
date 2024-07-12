package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.entity.Guest;
import br.com.booking.corebookingapi.entity.Room;
import br.com.booking.corebookingapi.enums.BookingStatus;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookingDTO {
    
    private Long idBooking;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private Double totalPrice;
    private Guest guest;
    private Integer guestQuantity;
    private Room room;
}
