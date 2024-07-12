package br.com.booking.corebookingapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddressDTO {
    private Long idAddress;
    private String street;
    private String number;
    private String zipCode;
    private Double latitude;
    private Double longitude;
    private CityDTO city;
}
