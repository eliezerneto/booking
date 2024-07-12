package br.com.booking.corebookingapi.repository;

import br.com.booking.corebookingapi.entity.Address;
import br.com.booking.corebookingapi.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AmenityRepository extends JpaRepository<Amenity, Long>, JpaSpecificationExecutor<Amenity> {

}