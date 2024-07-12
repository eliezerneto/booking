package br.com.booking.corebookingapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "city")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_city")
    @SequenceGenerator(name = "sequence_id_city", sequenceName = "sequence_id_city", allocationSize = 1)
    @Column(name = "id_city")
    private Long idCity;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;
}
