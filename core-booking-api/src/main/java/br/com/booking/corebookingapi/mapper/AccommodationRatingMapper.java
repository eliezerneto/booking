package br.com.booking.corebookingapi.mapper;

import br.com.booking.corebookingapi.dto.AccommodationRatingDTO;
import br.com.booking.corebookingapi.entity.AccommodationRating;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccommodationRatingMapper {
    AccommodationRatingDTO toDto(AccommodationRating accommodationRating);


    List<AccommodationRatingDTO> toDtoList(List<AccommodationRating> accommodationRatings);
}
