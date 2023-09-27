package tcc.job.devs.resources;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface BaseResource<TModel, TCreatePayload, TUpdatePayload> {

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable(name = "id", required = true) int id);

    @PostMapping
    ResponseEntity<?> create(@RequestBody(required = true) TCreatePayload payload);

    @PutMapping
    ResponseEntity<?> update(@RequestBody(required = true) TUpdatePayload payload);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id", required = true) int id);

    default ResponseEntity<?> response(Object data, HttpStatus status) {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("info", data);
        return ResponseEntity.status(status).body(infoMap);
    }

    default ResponseEntity<Void> response(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }

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

