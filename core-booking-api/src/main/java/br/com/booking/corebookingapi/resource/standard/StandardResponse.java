package br.com.booking.corebookingapi.resource.standard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardResponse {
    public static ResponseEntity<ResponseDTO> generateResponse(String message,
                                                          String detail,
                                                          ResponseType responseType,
                                                               HttpStatus status,
                                                          Object responseObj) {


        return new ResponseEntity<ResponseDTO>(generateObjectResponse(message,detail,responseType,status,responseObj),status);
    }

    public static ResponseDTO generateObjectResponse(String message,
                                                             String detail,
                                                             ResponseType responseType,
                                                             HttpStatus status,
                                                             Object responseObj) {

        ResponseDTO responseDTO = ResponseDTO.builder()
                .date(LocalDateTime.now())
                .message(message)
                .detail(detail)
                .responseType(responseType)
                .codeStatus(status.value())
                .reasonPhraseStatus(status.getReasonPhrase())
                .data(responseObj)
                .build();

        return responseDTO;
    }

}