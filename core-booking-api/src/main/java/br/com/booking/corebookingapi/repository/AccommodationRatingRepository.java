package br.com.booking.corebookingapi.repository;

import br.com.booking.corebookingapi.entity.AccommodationRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccommodationRatingRepository extends JpaRepository<AccommodationRating, Long>, JpaSpecificationExecutor<AccommodationRating> {

}