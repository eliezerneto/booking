package br.com.booking.corebookingapi.entity;

import br.com.booking.corebookingapi.enums.AccommodationType;
import br.com.booking.corebookingapi.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_booking")
    @SequenceGenerator(name = "sequence_id_accommodation", sequenceName = "sequence_id_booking", allocationSize = 1)
    @Column(name = "id_booking")
    private Long idBooking;

    @Column(name = "checkin_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "checkout_date", nullable = false)
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "idGuest", nullable = false)
    private Guest guest;

    @Column(name = "number_of_guests", nullable = false)
    private Integer guestQuantity;

    @ManyToOne
    @JoinColumn(name = "idRoom", nullable = false)
    private Room room;
}
