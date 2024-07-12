package br.com.booking.corebookingapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "country")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_country")
    @SequenceGenerator(name = "sequence_id_country", sequenceName = "sequence_id_country", allocationSize = 1)
    @Column(name = "id_country")
    private Long idCountry;

    @Column(name = "name", nullable = false)
    private String name;
}
