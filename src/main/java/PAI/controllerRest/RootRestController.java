package PAI.controllerRest;

import PAI.config.ApiCapabilitiesCatalog;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RootRestController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Map<String, Object>> options(@RequestParam(name = "role", required = false) String role) {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.ALLOW, "GET, OPTIONS")
                .body(ApiCapabilitiesCatalog.getCapabilitiesForRole(resolveRole(role)));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> index(@RequestParam(name = "role", required = false) String role) {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.ALLOW, "GET, OPTIONS")
                .body(ApiCapabilitiesCatalog.getCapabilitiesForRole(resolveRole(role)));
    }

    private String resolveRole(String role) {
        return (role != null) ? role.toLowerCase() : "anonymous";
    }

    @GetMapping("/catalog")
    public ResponseEntity<Map<String, Object>> fullCatalog() {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.ALLOW, "GET")
                .body(ApiCapabilitiesCatalog.getCapabilities());
    }
}
