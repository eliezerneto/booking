package br.com.booking.corebookingapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateUpdateAmenityDTO {

    @NotEmpty
    @NotNull
    private String name;
}
