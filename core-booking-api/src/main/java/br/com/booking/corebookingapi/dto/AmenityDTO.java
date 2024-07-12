package br.com.booking.corebookingapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AmenityDTO {
    private Long idAmenity;
    private String name;
}
