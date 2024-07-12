package br.com.booking.corebookingapi.dto;

import br.com.booking.corebookingapi.enums.RoomPriceType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateUpdateRoomPriceDTO {
    private RoomPriceType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double percentualChange;
    private Long roomId;
}
