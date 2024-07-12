package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.enums.RoomType;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RoomDTO {
    private Long idRoom;
    private RoomType type;
    private Double regularBasePrice;
    private Integer maxGuestQuantity;
    private Long idAccommodation;
    private List<Long> dynamicPricingIds;
}
