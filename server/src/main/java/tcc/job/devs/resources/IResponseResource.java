package tcc.job.devs.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface IResponseResource {

    default ResponseEntity<?> response(Object data, HttpStatus status) {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("info", data);
        return ResponseEntity.status(status).body(infoMap);
    }

    default ResponseEntity<Void> response(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }

}
