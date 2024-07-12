package br.com.booking.corebookingapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccommodationRatingDTO {
    private Long idAccommodationRating;
    private Long idAccommodation;
    private Integer sumRating;
    private Integer numberOfRatings;
    private Double averageRating;
}
