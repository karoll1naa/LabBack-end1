package main.labbackend1.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@RestController
public class HealthController {
    @GetMapping("/")    public RedirectView redirectToHealthcheck() {
        return new RedirectView("/healthcheck");
    }

    @GetMapping("/healthcheck")
    public Map<String, Object> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("time", LocalDateTime.now());
        return response;
    }
}