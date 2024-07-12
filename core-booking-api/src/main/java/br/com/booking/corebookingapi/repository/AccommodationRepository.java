package br.com.booking.corebookingapi.repository;

import br.com.booking.corebookingapi.entity.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {

    @Query("SELECT a " +
            "FROM Accommodation a " +
            "WHERE a.idAccommodation = :idAccommodation AND :guestsQuantity <= (" +
            "    SELECT SUM(r.maxGuestQuantity) " +
            "    FROM Room r " +
            "    WHERE r.accommodation.idAccommodation = a.idAccommodation AND " +
            "    NOT EXISTS ( SELECT b FROM Booking b " +
            "    WHERE b.checkOutDate >= :checkIn OR b.checkInDate <= :checkOut )" +
            ")")
    Optional<Accommodation> verifyByDateAvailability(
            Long idAccommodation,
            LocalDate checkIn,
            LocalDate checkOut,
            Integer guestsQuantity
    );

    @Query("SELECT a " +
            "FROM Accommodation a " +
            "WHERE a.address.city LIKE :city AND a.idAccommodation = :idAccommodation AND :guestsQuantity <= (" +
            "    SELECT SUM(r.maxGuestQuantity) " +
            "    FROM Room r " +
            "    WHERE r.accommodation.idAccommodation = a.idAccommodation AND " +
            "    NOT EXISTS ( SELECT b FROM Booking b " +
            "    WHERE b.checkOutDate >= :checkIn OR b.checkInDate <= :checkOut )" +
            ")")
    Page<Accommodation> findByDateAvailability(
            LocalDate checkIn,
            LocalDate checkOut,
            Integer guestsQuantity,
            String city,
            Pageable pageable
    );

    @Query("SELECT a " +
            "FROM Accommodation a " +
            "WHERE a.idAccommodation IN (:firstIdAccommodation, :secondIdAccommodation)")
    Page<Accommodation> compareAccommodation(
            Long firstIdAccommodation,
            Long secondIdAccommodation,
            Pageable pageable
    );


}