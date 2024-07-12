package br.com.booking.corebookingapi.entity;

import br.com.booking.corebookingapi.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "room")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_room")
    @SequenceGenerator(name = "sequence_id_room", sequenceName = "sequence_id_room", allocationSize = 1)
    @Column(name = "id_room")
    private Long idRoom;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoomType type;

    @Column(name = "regular_base_price", nullable = false)
    private Double regularBasePrice;

    @Column(name = "max_guest_quantity", nullable = false)
    private Integer maxGuestQuantity;

    @ManyToOne
    @JoinColumn(name = "id_accommodation")
    @JsonIgnore
    private Accommodation accommodation;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomPrice> dynamicPricing;
}
