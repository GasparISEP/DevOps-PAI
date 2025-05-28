package PAI.controllerRest;

import PAI.config.ApiCapabilitiesCatalog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RootRestController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Map<String, Object>> options() {
        return ResponseEntity
                .ok()
                .header("Allow", "GET, OPTIONS")
                .body(ApiCapabilitiesCatalog.getCapabilities());
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        return options();
    }
}
