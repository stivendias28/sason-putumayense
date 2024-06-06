package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.UserController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserSecurity extends GlobalMappingConfig {
    private final UserController userController;

    @GetMapping("/users")
    @Secured("ADMIN")
    public ResponseEntity<ApiResponse> getUsers(@RequestParam(value = "filter", required = false) String filter) {
        return userController.getAllUsers(filter);
    }

}
