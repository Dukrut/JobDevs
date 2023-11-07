package tcc.job.devs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.services.JobService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/jobs")
public class JobResourceImpl implements IResponseResource{

    @Autowired
    private JobService jobService;

    @GetMapping("/search")
    public ResponseEntity<?> searchJobs() {
        try {
            List<Map<String, Object>> jobs = jobService.searchJobForUser();
            return response(jobs, HttpStatus.OK);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return response(HttpStatus.BAD_REQUEST);
        }
    }

}
