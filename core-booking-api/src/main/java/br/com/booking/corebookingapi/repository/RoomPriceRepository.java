package br.com.booking.corebookingapi.repository;

import br.com.booking.corebookingapi.entity.Accommodation;
import br.com.booking.corebookingapi.entity.RoomPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface RoomPriceRepository extends JpaRepository<RoomPrice, Long>, JpaSpecificationExecutor<RoomPrice> {

    @Query("SELECT r " +
            "FROM RoomPrice r " +
            "WHERE r.startDate <= :checkIn OR r.endDate >= :checkOut ")
    Optional<RoomPrice> findByDate(
            LocalDate checkIn,
            LocalDate checkOut
    );
}