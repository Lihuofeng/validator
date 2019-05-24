package cn.donespeak.demos.validator.config.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ControllerResponseHandler implements ResponseBodyAdvice<Object> {
	
	private Logger logger = LogManager.getLogger(getClass());

	/*
	 * It will supports all return types.
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		logger.debug("converterType " + converterType);
		logger.debug("returnType: " + returnType.getClass());
		logger.debug("MappingJackson2HttpMessageConverter is assignable from converterType: "
				+ MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType));
		return true;
	}

	/*
	 * package the return data into the data field of <code>ResponseJson</code>
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if(body instanceof HttpStatusResponse) {
			HttpStatusResponse httpStatusReponse = (HttpStatusResponse) body;
			response.setStatusCode(httpStatusReponse.getHttpStatus());
		} else {
			if(request.getMethod().equals(HttpMethod.DELETE)) {
				response.setStatusCode(HttpStatus.NO_CONTENT);
			} else if (request.getMethod().equals(HttpMethod.POST)) {
				response.setStatusCode(HttpStatus.CREATED);				
			}
		}
		return body;
	}
}