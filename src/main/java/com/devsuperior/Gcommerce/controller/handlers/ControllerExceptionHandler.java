// src/main/java/com/devsuperior/Gcommerce/controller/handlers/ControllerExceptionHandler.java
package com.devsuperior.Gcommerce.controller.handlers;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.Gcommerce.dto.CustomErrorDTO;
import com.devsuperior.Gcommerce.dto.ValidationErrorDTO;
import com.devsuperior.Gcommerce.services.exceptions.DatabaseException;
import com.devsuperior.Gcommerce.services.exceptions.ForbiddenException;
import com.devsuperior.Gcommerce.services.exceptions.ResourceNotFoundException;
import com.devsuperior.Gcommerce.services.exceptions.ValidationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

        private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

        /**
         * 404 Not Found
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<CustomErrorDTO> resourceNotFound(
                        ResourceNotFoundException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.NOT_FOUND;
                CustomErrorDTO err = new CustomErrorDTO(
                                Instant.now(),
                                status.value(),
                                e.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        /**
         * 400 Bad Request
         */
        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<CustomErrorDTO> validationException(
                        ValidationException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                CustomErrorDTO err = new CustomErrorDTO(
                                Instant.now(),
                                status.value(),
                                e.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        /**
         * 401 Unauthorized
         */
        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<CustomErrorDTO> authenticationException(
                        AuthenticationException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.UNAUTHORIZED;
                CustomErrorDTO err = new CustomErrorDTO(
                                Instant.now(),
                                status.value(),
                                "Autenticação necessária para acessar este recurso",
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        /**
         * 403 Forbidden
         */
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<CustomErrorDTO> accessDeniedException(
                        AccessDeniedException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.FORBIDDEN;
                CustomErrorDTO err = new CustomErrorDTO(
                                Instant.now(),
                                status.value(),
                                "Você não tem permissão para acessar este recurso",
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(ForbiddenException.class)
        public ResponseEntity<CustomErrorDTO> forbidden(ForbiddenException e, HttpServletRequest request) {
                HttpStatus status = HttpStatus.FORBIDDEN;
                CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        /**
         * 405 Method Not Allowed
         */
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<CustomErrorDTO> methodNotSupported(
                        HttpRequestMethodNotSupportedException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
                CustomErrorDTO err = new CustomErrorDTO(
                                Instant.now(),
                                status.value(),
                                "Método '" + e.getMethod() + "' não é suportado para este recurso",
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<CustomErrorDTO> globalException(
                        Exception e,
                        HttpServletRequest request) {
                logger.error("Erro inesperado: ", e);
                HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
                CustomErrorDTO err = new CustomErrorDTO(
                                Instant.now(),
                                status.value(),
                                "Um erro inesperado ocorreu no servidor",
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<CustomErrorDTO> methodArgumentNotValidation(MethodArgumentNotValidException e,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
                ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Dados inválidos",
                                request.getRequestURI());
                for (FieldError f : e.getBindingResult().getFieldErrors()) {
                        err.addError(f.getField(), f.getDefaultMessage());
                }
                return ResponseEntity.status(status).body(err);
        }

        @ExceptionHandler(DatabaseException.class)
        public ResponseEntity<CustomErrorDTO> database(DatabaseException e, HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(status).body(err);
        }

}