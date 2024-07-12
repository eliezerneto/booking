package br.com.booking.corebookingapi.entity;

import br.com.booking.corebookingapi.enums.AccommodationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "accommodation")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_accommodation")
    @SequenceGenerator(name = "sequence_id_accommodation", sequenceName = "sequence_id_accommodation", allocationSize = 1)
    @Column(name = "id_accommodation")
    private Long idAccommodation;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccommodationType type;

    @OneToOne
    @JoinColumn(name = "id_accommodation_rating")
    private AccommodationRating accommodationRating;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @ManyToMany
    @JoinTable(
            name = "accommodation_amenity",
            joinColumns = @JoinColumn(name = "idAccommodation"),
            inverseJoinColumns = @JoinColumn(name = "idAmenity")
    )
    private List<Amenity> amenities;

}
