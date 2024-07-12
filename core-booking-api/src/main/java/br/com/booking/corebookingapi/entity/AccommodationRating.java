package br.com.booking.corebookingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "accommodation_rating")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccommodationRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_accommodation_rating")
    @SequenceGenerator(name = "sequence_id_accommodation_rating", sequenceName = "sequence_id_accommodation_rating", allocationSize = 1)
    @Column(name = "id_accommodation_rating")
    private Long idAccommodationRating;

    @OneToOne
    @JoinColumn(name = "id_accommodation")
    @JsonIgnore
    private Accommodation accommodation;

    @Column(name = "sum_rating", nullable = false)
    private Integer sumRating;

    @Column(name = "number_of_ratings", nullable = false)
    private Integer numberOfRatings;

    @Column(name = "average_rating", nullable = false)
    private Double averageRating;
}
