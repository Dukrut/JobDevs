package tcc.job.devs.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public interface BaseResource<TModel, TCreatePayload, TUpdatePayload> {

    @GetMapping("/{id}")
    ResponseEntity<TModel> get(@PathVariable(name = "id", required = true) int id);

    @GetMapping
    ResponseEntity<TModel> getAll();

    @PostMapping
    ResponseEntity<TModel> create(@RequestBody(required = true) TCreatePayload payload);

    @PutMapping
    ResponseEntity<TModel> update(@RequestBody(required = true) TUpdatePayload payload);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(name = "id", required = true) int id);

    default ResponseEntity<Map<String, Object>> response(Object data, HttpStatus status) {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("info", data);
        return ResponseEntity.status(status).body(infoMap);
    }

    default ResponseEntity<Void> response(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }

}

