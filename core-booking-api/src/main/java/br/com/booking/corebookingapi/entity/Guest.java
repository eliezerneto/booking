package br.com.booking.corebookingapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "guest")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_guest")
    @SequenceGenerator(name = "sequence_id_guest", sequenceName = "sequence_id_guest", allocationSize = 1)
    @Column(name = "id_guest")
    private Long idGuest;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings;
}
