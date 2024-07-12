package br.com.booking.corebookingapi.repository;

import br.com.booking.corebookingapi.entity.Booking;
import br.com.booking.corebookingapi.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

    List<Booking> findAllByStatus(BookingStatus bookingStatus);

}