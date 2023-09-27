package tcc.job.devs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Slf4jLoggerService implements LoggerService {

    private final Logger logger = LoggerFactory.getLogger(Slf4jLoggerService.class);

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(Exception e) {
        logger.error("Exception caught", e);
    }

    @Override
    public void error(String message, Exception e) {
        logger.error(message, e);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

}

