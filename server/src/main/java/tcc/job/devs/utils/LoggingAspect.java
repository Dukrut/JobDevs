package tcc.job.devs.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private Slf4jLoggerService loggerService;

    @Around("execution(* tcc.job.devs.*.BaseResource.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String path = request.getRequestURI();

        loggerService.info("Path = [" + path + "] " +
                " Method = [" + joinPoint.getSignature().getName().toUpperCase() + "]");

        try {
            Object result = joinPoint.proceed();

            if (result instanceof ResponseEntity<?> response) {
                if (response.getStatusCode() != HttpStatus.OK) {
                    loggerService.warn("Method " + joinPoint.getSignature().getName() +
                            " returned with status: " + response.getStatusCode());
                } else {
                    loggerService.info("Method " + joinPoint.getSignature().getName() + " executed successfully");
                }
            } else {
                loggerService.info("Method " + joinPoint.getSignature().getName() + " executed successfully");
            }

            return result;
        } catch (Exception ex) {
            loggerService.error("Error = " + ex.getMessage(), ex);
            throw ex;
        }
    }
}