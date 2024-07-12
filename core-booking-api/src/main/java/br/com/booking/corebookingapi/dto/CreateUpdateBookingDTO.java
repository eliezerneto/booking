package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateUpdateBookingDTO {

    @Schema(description = "Check-in date", required = true)
    @NotNull(message = "Check-in date is required")
    private LocalDate checkInDate;

    @Schema(description = "Check-out date", required = true)
    @NotNull(message = "Check-out date is required")
    private LocalDate checkOutDate;

    @Schema(description = "Guest ID", required = true)
    @NotNull(message = "Guest ID is required")
    private Long guestId;

    @Schema(description = "Guest Quantity", required = true)
    @NotNull(message = "Guest Quantity")
    private Integer guestQuantity;

    @Schema(description = "Room", required = true)
    @NotNull(message = "Room")
    private Long idRoom;


}
