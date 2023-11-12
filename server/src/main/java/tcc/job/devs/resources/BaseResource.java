package tcc.job.devs.resources;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface BaseResource<TModel, TCreatePayload, TUpdatePayload> extends IResponseResource {

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody(required = true) TCreatePayload payload);

    @PutMapping
    ResponseEntity<?> update(@Valid @RequestBody(required = true) TUpdatePayload payload);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id", required = true) int id);

    default String getDuplicatedFieldFromException(DataIntegrityViolationException ex) {
        String message = ex.getCause().getMessage();
        Pattern pattern = Pattern.compile("Duplicate entry '(.+?)' for key '(.+?)'");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return "Campo duplicado: " + matcher.group(2) + ", Valor: " + matcher.group(1);
        }
        return "Campo duplicado não identificado";
    }

}

