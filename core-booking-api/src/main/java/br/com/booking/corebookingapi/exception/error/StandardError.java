package br.com.booking.corebookingapi.exception.error;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StandardError {

	private String code;
	private String description;
}