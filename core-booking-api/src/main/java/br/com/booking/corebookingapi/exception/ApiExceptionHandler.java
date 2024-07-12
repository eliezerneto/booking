package br.com.booking.corebookingapi.exception;

import br.com.booking.corebookingapi.exception.custom.BusinessException;
import br.com.booking.corebookingapi.exception.custom.GeneralException;
import br.com.booking.corebookingapi.exception.custom.RecordNotFoundException;
import br.com.booking.corebookingapi.exception.error.StandardError;
import br.com.booking.corebookingapi.resource.standard.ResponseType;
import br.com.booking.corebookingapi.resource.standard.ResponseDTO;
import br.com.booking.corebookingapi.resource.standard.StandardResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.EOFException;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	/* Custom exceptions */

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseDTO handleBusinessException(BusinessException ex, WebRequest request) {

		return StandardResponse.generateObjectResponse(
				ex.getMessage(),
				null,
				ResponseType.ERROR,
				HttpStatus.UNPROCESSABLE_ENTITY,
				StandardError.builder()
						.code(ex.getCode())
						.description(ex.getMessage())
						.build()
				);

	}

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseDTO handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {

		return StandardResponse.generateObjectResponse(
				ex.getMessage(),
				null,
				ResponseType.ERROR,
				HttpStatus.NOT_FOUND,
				StandardError.builder()
						.code(ex.getCode())
						.description(ex.getMessage())
						.build()
		);
	}

	@ExceptionHandler({GeneralException.class, Exception.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseDTO handleGeneralException(GeneralException ex, WebRequest request) {

		return StandardResponse.generateObjectResponse(
				ex.getMessage(),
				null,
				ResponseType.ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR,
				StandardError.builder()
						.code(ex.getCode())
						.description(ex.getMessage())
						.build()
		);
	}

	/* End Custom exceptions */

	@ExceptionHandler({EOFException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseDTO handlePathGeneralException(GeneralException ex, WebRequest request) {

		return StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.BAD_REQUEST,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.methodnotallowed.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.METHOD_NOT_ALLOWED,
				StandardError.builder()
						.code(messageSource.getMessage("general.methodnotallowed.exception.code",null,null))
						.description(messageSource.getMessage("general.methodnotallowed.exception.message",null,null))
						.build()
		);
		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
	 *
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.BAD_REQUEST,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}


	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
	 *
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.BAD_REQUEST,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
	 *
	 * @param ex the ConstraintViolationException
	 * @return the ApiError object
	 */
	@ExceptionHandler({jakarta.validation.ConstraintViolationException.class, ConstraintViolationException.class})
	@ResponseStatus(value = HttpStatus.CONFLICT)
	protected ResponseDTO handleConstraintViolation(
			jakarta.validation.ConstraintViolationException ex) {
		return StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.constraintviolation.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.CONFLICT,
				StandardError.builder()
						.code(messageSource.getMessage("general.constraintviolation.exception.code",null,null))
						.description(messageSource.getMessage("general.constraintviolation.exception.message",null,null))
						.build()
		);
	}


	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.BAD_REQUEST,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}

	/**
	 * Handle HttpMessageNotWritableException.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);

		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}

	/**
	 * Handle NoHandlerFoundException.
	 *
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ResponseDTO responseDTO = StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.BAD_REQUEST,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
		return super.handleExceptionInternal(ex, responseDTO, headers, status, request);
	}

	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	protected ResponseDTO handleDataIntegrityViolation(DataIntegrityViolationException ex,
																  WebRequest request) {
		return StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.constraintviolation.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.CONFLICT,
				StandardError.builder()
						.code(messageSource.getMessage("general.constraintviolation.exception.code",null,null))
						.description(messageSource.getMessage("general.constraintviolation.exception.message",null,null))
						.build()
		);
	}

	/**
	 * Handle Exception, handle generic Exception.class
	 *
	 * @param ex the Exception
	 * @return the ApiError object
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ResponseDTO handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
																	  WebRequest request) {
		return StandardResponse.generateObjectResponse(
				messageSource.getMessage("general.request.exception.message",null,null),
				null,
				ResponseType.ERROR,
				HttpStatus.BAD_REQUEST,
				StandardError.builder()
						.code(messageSource.getMessage("general.request.exception.code",null,null))
						.description(messageSource.getMessage("general.request.exception.message",null,null))
						.build()
		);
	}


}
