package br.com.booking.corebookingapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateUpdateGuestDTO {
    @NotEmpty(message = "{guest.name.notempty}")
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 10, max = 20, message = "{guest.phone.size}")
    private String phone;
}
