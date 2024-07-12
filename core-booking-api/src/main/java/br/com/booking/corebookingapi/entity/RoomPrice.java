package br.com.booking.corebookingapi.entity;

import br.com.booking.corebookingapi.enums.RoomPriceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "room_price")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_room_price")
    @SequenceGenerator(name = "sequence_id_room_price", sequenceName = "sequence_id_room_price", allocationSize = 1)
    @Column(name = "id_room_price")
    private Long idRoomPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoomPriceType type;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "percentual_change", nullable = false)
    private Double percentualChange;

    @ManyToOne
    @JoinColumn(name = "id_room")
    @JsonIgnore
    private Room room;
}
