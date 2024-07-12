package br.com.booking.corebookingapi.repository;

import br.com.booking.corebookingapi.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

}