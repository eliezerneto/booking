package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.enums.RoomType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateUpdateRoomDTO {
    private RoomType type;
    private Double regularBasePrice;
    private Integer maxGuestQuantity;
    private Long idAccommodation;
}
