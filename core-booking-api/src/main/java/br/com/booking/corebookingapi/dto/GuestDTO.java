package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.entity.Booking;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GuestDTO {
    private Long idGuest;
    private String name;
    private String email;
    private String phone;
    private List<Booking> bookings;
}
