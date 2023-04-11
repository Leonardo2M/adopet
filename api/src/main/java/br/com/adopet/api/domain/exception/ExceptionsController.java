package br.com.adopet.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(AdopetException.class)
    public ResponseEntity<?> catchError500(AdopetException error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErros>> catchError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DadosErros::new).toList());
    }

    private record DadosErros(String campo, String mensagem) {
        public DadosErros(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
