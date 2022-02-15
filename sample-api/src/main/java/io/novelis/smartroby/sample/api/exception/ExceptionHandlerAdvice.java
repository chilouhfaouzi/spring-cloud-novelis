package io.novelis.smartroby.sample.api.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.novelis.smartroby.sample.helper.i18n.I18nHelper;
import io.novelis.smartroby.sample.service.validation.exception.common.BusinessException;
import io.novelis.smartroby.sample.service.validation.exception.common.DataNotFoundException;
import io.novelis.smartroby.sample.service.validation.exception.common.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice implements ProblemHandling {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    private static final String PATH = "path";

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Problem> handleDataNotFound(DataNotFoundException e, NativeWebRequest request) {
        if(log.isDebugEnabled()) {
            log.error(e.getMessage(), e);
        }
        Problem problem = Problem.builder()
                .with("code", e.getCode())
                .withTitle(I18nHelper.getMessage(e.getTitle()))
                .withStatus(Status.NOT_FOUND)
                .withDetail(I18nHelper.getMessage(e.getMessage(), e.getArgs()))
                .with(PATH, request.getNativeRequest(HttpServletRequest.class).getRequestURI())
                .build();
        return new ResponseEntity<>(problem, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Problem> handleBusinessException(BusinessException e, NativeWebRequest request) {
        if(log.isDebugEnabled()) {
            log.error(e.getMessage(), e);
        }
        Problem problem = Problem.builder()
                .with("code", e.getCode())
                .withTitle(I18nHelper.getMessage(e.getTitle()))
                .withStatus(Status.BAD_REQUEST)
                .withDetail(I18nHelper.getMessage(e.getMessage(), e.getArgs()))
                .with(PATH, request.getNativeRequest(HttpServletRequest.class).getRequestURI())
                .build();
        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Problem> handleTechnicalException(BusinessException e, NativeWebRequest request) {
        log.error(e.getMessage(), e);

        Problem problem = Problem.builder()
                .with("code", e.getCode())
                .withTitle(e.getTitle())
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .withDetail("unexpected error")
                .with(PATH, request.getNativeRequest(HttpServletRequest.class).getRequestURI())
                .build();
        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleUnexpectedException(Exception e, NativeWebRequest request) {
        log.error(e.getMessage(), e);

        Problem problem = Problem.builder()
                .withTitle("unexpected error")
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .withDetail("unexpected error")
                .with(PATH, request.getNativeRequest(HttpServletRequest.class).getRequestURI())
                .build();
        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModules(
                new ProblemModule(),
                new ConstraintViolationProblemModule());
    }
}
