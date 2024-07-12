package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.enums.AccommodationType;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccommodationDTO {

    private Long idAccommodation;
    private String name;
    private String description;
    private AddressDTO address;
    private AccommodationType type;
    private AccommodationRatingDTO accommodationRating;
    private List<RoomDTO> rooms;
    private List<AmenityDTO> amenities;
}

