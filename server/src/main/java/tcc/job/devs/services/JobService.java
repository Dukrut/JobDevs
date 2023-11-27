package tcc.job.devs.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.security.JwtUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    public List<Map<String, Object>> searchJobForUser() throws IOException, InterruptedException {
        int userId = jwtUtils.getUserIdFromJWT();
        UserPayloads.UserModel userModel = userService.findModelById(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        String userModelJson = objectMapper.writeValueAsString(userModel);
        Resource resource = new ClassPathResource("ia/job.py");
        Path tempFile = Files.createTempFile("job-", ".py");
        try (InputStream inputStream = resource.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        ProcessBuilder processBuilder = new ProcessBuilder("python3", tempFile.toString(), "--user_profile", userModelJson);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String output = "";
        while ((line = reader.readLine()) != null) {
            output = output.concat(line);
        }

        output = "[" +  output.substring(output.indexOf("{"));
        List<Map<String, Object>> jobs = objectMapper.readValue(output, new TypeReference<List<Map<String, Object>>>(){});

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("O script Python falhou com o código de saída " + exitCode);
        }

        Files.deleteIfExists(tempFile);

        return jobs;
    }
}
