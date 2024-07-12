package br.com.booking.corebookingapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "amenity")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_amenity")
    @SequenceGenerator(name = "sequence_id_amenity", sequenceName = "sequence_id_amenity", allocationSize = 1)
    @Column(name = "id_amenity")
    private Long idAmenity;

    @Column(name = "name", nullable = false)
    private String name;
}
