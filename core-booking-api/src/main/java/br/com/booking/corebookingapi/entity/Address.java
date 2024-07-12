package br.com.booking.corebookingapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "address")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_address")
    @SequenceGenerator(name = "sequence_id_address", sequenceName = "sequence_id_address", allocationSize = 1)
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "zipcode", nullable = false)
    private String zipCode;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;
}
