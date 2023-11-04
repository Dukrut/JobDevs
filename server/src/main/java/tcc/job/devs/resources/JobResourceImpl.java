package tcc.job.devs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.services.JobService;

@RestController
@RequestMapping(value = "/api/job")
public class JobResourceImpl implements IResponseResource{

    @Autowired
    private JobService jobService;

    @GetMapping("/search")
    public ResponseEntity<?> searchJobs() {
        jobService.searchJobForUser();
        return response(HttpStatus.OK);
    }

}
