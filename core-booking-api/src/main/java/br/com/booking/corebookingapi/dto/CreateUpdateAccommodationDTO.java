package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.enums.AccommodationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateAccommodationDTO {


    @NotEmpty
    @NotNull
    @Size(max = 50)
    private String name;
    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String description;
    @NotNull
    private AddressDTO idAddress;
    @NotNull
    private AccommodationType type;
}

