package sample.customer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sample.customer.biz.domain.ApiError;
import sample.customer.biz.service.DataNotFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    private final Map<Class<? extends Exception>, String> messageMappings =
            Collections.unmodifiableMap(new LinkedHashMap<Class<? extends Exception>, String>() {
                {
                    put(Exception.class, "Request body is invalid");
                }
            });

    private String resolveMessage(Exception ex, String defaultMessage){
        return messageMappings.entrySet().stream()
                .filter(entry ->entry.getKey().isAssignableFrom(ex.getClass())).findFirst()
                .map(Map.Entry::getValue)
                .orElse(defaultMessage);
    }

    private ApiError createApiError(Exception ex, String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(resolveMessage(ex, message));
        apiError.setDocumentationUrl("http://localhost:8080/Gradle___Sample___web20200616_2_1_0_SNAPSHOT_war/api/errors");
        return apiError;
    }

    // @ValidでNGの時、呼ばれる。
    // @Size(max=3)
    // private String name;
    @Override
    //@ExceptionHandler
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        ex.getBindingResult().getGlobalErrors().forEach(e ->apiError.addDetail(e.getObjectName(), getMessage(e, request)));
        ex.getBindingResult().getFieldErrors().forEach(e -> apiError.addDetail(e.getField(), e.getDefaultMessage()/*getMessage(e, request)*/));
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        ex.getBindingResult().getGlobalErrors().forEach(e ->apiError.addDetail(e.getObjectName(), getMessage(e, request)));
        ex.getBindingResult().getFieldErrors().forEach(e -> apiError.addDetail(e.getField(), e.getDefaultMessage()/*getMessage(e, request)*/));
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private String getMessage(MessageSourceResolvable resolvable, WebRequest request){
        return messageSource.getMessage(resolvable, request.getLocale());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAllException(DataNotFoundException ex, WebRequest request) {
        //ApiError apiError = createApiError(ex);
        //return super.handleExceptionInternal(ex, apiError, request);
        return super.handleExceptionInternal(ex, "handleAllException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }



}
