package br.com.booking.corebookingapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CityDTO {
    private Long idCity;
    private String name;
    private CountryDTO country;
}
