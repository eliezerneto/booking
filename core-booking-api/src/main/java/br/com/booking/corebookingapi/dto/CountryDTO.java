package br.com.booking.corebookingapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CountryDTO {
    private Long idCountry;
    private String name;
}
