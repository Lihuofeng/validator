package cn.donespeak.demos.validator.config.communication;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import cn.donespeak.demos.validator.base.ServiceException;
import cn.donespeak.demos.validator.base.ServiceMessageException;
import cn.donespeak.demos.validator.base.validators.ValidatorErrorType;
import cn.donespeak.demos.validator.config.communication.ResponseNotValidError.FieldNotValidError;

/**
 * refs: [Custom Error Message Handling for REST
 * API](https://www.baeldung.com/global-error-handler-in-a-spring-rest-api)
 * 
 * @author guanrongYang
 * @date 2019/03/12 18:39:30
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

	private Logger logger = LogManager.getLogger(getClass());

	private static final String logExceptionFormat = "[EXIGENCE] Some thing wrong with the system: %s";
	private static final HttpStatus DEFAULT_SERVICE_ERROR_STATUS = HttpStatus.BAD_REQUEST;

	@ExceptionHandler({ ServiceMessageException.class })
	public ResponseCodeError handleServiceMessageException(HttpServletRequest request, ServiceMessageException ex) {
		logger.info(ex);
		return new ResponseCodeError(DEFAULT_SERVICE_ERROR_STATUS, ex.getMsgCode() + "", ex.getMessage());
	}

	@ExceptionHandler({ ServiceException.class })
	public ResponseCodeError handleServiceException(HttpServletRequest request, ServiceException ex) {
		logger.info(ex);
		String message = codeToMessage(ex.getMsgCode());
		return new ResponseCodeError(DEFAULT_SERVICE_ERROR_STATUS, ex.getMsgCode() + "", message);
	}

	/**
	 * used for handling the exception occurs while validating the object type
	 * method argument. It happen in post, put, patch type request.
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseNotValidError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		logger.info(ex);
		return validatorErrors(ex.getBindingResult());
	}

	private ResponseNotValidError validatorErrors(BindingResult result) {
		List<FieldNotValidError> errors = new ArrayList<FieldNotValidError>();
		for (FieldError error : result.getFieldErrors()) {
			errors.add(toFieldNotValidError(error));
		}
		return new ResponseNotValidError(errors);
	}

	/**
	 * This exception reports the result of constraint violations. It occurs
	 * when the validator is used in controller method while is used for
	 * handling the get type request.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseNotValidError handleConstraintViolationException(HttpServletRequest request,
			ConstraintViolationException ex) {
		logger.info(ex);
		List<FieldNotValidError> errors = new ArrayList<FieldNotValidError>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(toFieldNotValidError(violation));
		}
		return new ResponseNotValidError(errors);
	}

	private FieldNotValidError toFieldNotValidError(ConstraintViolation<?> violation) {
		Path.Node lastNode = null;
		for (Path.Node node : violation.getPropertyPath()) {
			lastNode = node;
		}

		FieldNotValidError fieldNotValidError = new FieldNotValidError();
		// fieldNotValidError.setType(ValidatorTypeMapping.toType(violation.getConstraintDescriptor().getAnnotation().annotationType()));
		fieldNotValidError.setType(ValidatorErrorType.INVALID.value());
		fieldNotValidError.setField(lastNode.getName());
		fieldNotValidError.setMessage(violation.getMessage());
		return fieldNotValidError;
	}

	private FieldNotValidError toFieldNotValidError(FieldError error) {
		FieldNotValidError fieldNotValidError = new FieldNotValidError();
		fieldNotValidError.setType(ValidatorErrorType.INVALID.value());
		fieldNotValidError.setField(error.getField());
		fieldNotValidError.setMessage(error.getDefaultMessage());
		return fieldNotValidError;
	}

	@ExceptionHandler(BindException.class)
	public ResponseNotValidError handleBindException(HttpServletRequest request, BindException ex) {
		logger.info(ex);
		return validatorErrors(ex.getBindingResult());
	}

	@ExceptionHandler(ConversionNotSupportedException.class)
	public ResponseMessageError handleConversionNotSupportedException(HttpServletRequest request,
			ConversionNotSupportedException ex) {
		return internalServiceError(ex);
	}

	/**
	 * it will not return a response body to request
	 */
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseMessageError handleHttpMediaTypeNotAcceptableException(HttpServletRequest request,
			HttpMediaTypeNotAcceptableException ex) {
		logger.info(ex);
		StringBuilder messageBuilder = new StringBuilder().append("The media type is not acceptable.")
				.append(" Acceptable media types are ");
		ex.getSupportedMediaTypes().forEach(t -> messageBuilder.append(t + ", "));
		String message = messageBuilder.substring(0, messageBuilder.length() - 2);
		System.out.println(message);
		return new ResponseMessageError(HttpStatus.NOT_ACCEPTABLE, message);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseMessageError handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
			HttpMediaTypeNotSupportedException ex) {
		 logger.info(ex);
		StringBuilder messageBuilder = new StringBuilder().append(ex.getContentType())
				.append(" media type is not supported.").append(" Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> messageBuilder.append(t + ", "));
		String message = messageBuilder.substring(0, messageBuilder.length() - 2);
		System.out.println(message);
		return new ResponseMessageError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseMessageError handlerHttpMessageNotReadableException(HttpServletRequest request,
			HttpMessageNotReadableException ex) {
		logger.info(ex);
		String message = "Problems parsing JSON";
		return new ResponseMessageError(HttpStatus.BAD_REQUEST, message);
	}

	/**
	 * It occurs when convert message to response the request.
	 */
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseMessageError handlerHttpMessageNotWritableException(HttpServletRequest request,
			HttpMessageNotWritableException ex) {
		return internalServiceError(ex);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseMessageError springException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
		logger.info(ex);
		StringBuilder messageBuilder = new StringBuilder().append(ex.getMethod())
				.append(" method is not supported for this request.").append(" Supported methods are ");

		ex.getSupportedHttpMethods().forEach(m -> messageBuilder.append(m + ","));
		String message = messageBuilder.substring(0, messageBuilder.length() - 2);
		return new ResponseMessageError(HttpStatus.METHOD_NOT_ALLOWED, message);
	}

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseJson springException(HttpServletRequest request,
	 * MethodArgumentNotValidException ex) { return
	 * generateResponseJson(HttpStatus.BAD_REQUEST); }
	 */

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseNotValidError methodArgumentTypeMismatchExceptionHandler(HttpServletRequest request,
			MethodArgumentTypeMismatchException ex) {
		logger.info(ex);
		String message = "The parameter '" + ex.getName() + "' should of type '"
				+ ex.getRequiredType().getSimpleName().toLowerCase() + "'";

		FieldNotValidError fieldNotValidError = new FieldNotValidError();
		fieldNotValidError.setType(ValidatorErrorType.TYPE_MISMATCH.value());
		fieldNotValidError.setField(ex.getName());
		fieldNotValidError.setMessage(message);

		return new ResponseNotValidError(fieldNotValidError);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseNotValidError springException(HttpServletRequest request,
			MissingServletRequestParameterException ex) {
		logger.info(ex);
		String message = "Required parameter '" + ex.getParameterName() + "' is not present";

		FieldNotValidError fieldNotValidError = new FieldNotValidError();
		fieldNotValidError.setType(ValidatorErrorType.MISSING_FIELD.value());
		fieldNotValidError.setField(ex.getParameterName());
		fieldNotValidError.setMessage(message);

		return new ResponseNotValidError(fieldNotValidError);
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseMessageError springException(HttpServletRequest request, MissingServletRequestPartException ex) {
		logger.info(ex);
		return new ResponseMessageError(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseMessageError springException(HttpServletRequest request, NoHandlerFoundException ex) {
		logger.info(ex);
		String message = "No resource found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		return new ResponseMessageError(HttpStatus.NOT_FOUND, message);
	}

	@ExceptionHandler(OperationNotSupportedException.class)
	public ResponseMessageError springException(HttpServletRequest request, OperationNotSupportedException ex) {
		return internalServiceError(ex);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseMessageError springException(HttpServletRequest request, TypeMismatchException ex) {
		return internalServiceError(ex);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseMessageError springException(HttpServletRequest request, MissingPathVariableException ex) {
		return internalServiceError(ex);
	}

	@ExceptionHandler()
	public ResponseMessageError handleAll(HttpServletRequest request, Exception ex) {
		// infoExigence(e);
		return internalServiceError(ex);
	}

	private String codeToMessage(int code) {
		return "The code is " + code;
	}

	private ResponseMessageError internalServiceError(Exception ex) {
		logException(ex);
		return new ResponseMessageError(HttpStatus.INTERNAL_SERVER_ERROR, "error occured");
	}

	private <T extends Throwable> void logException(T e) {
		logger.error(String.format(logExceptionFormat, e.getMessage()), e);
	}
}