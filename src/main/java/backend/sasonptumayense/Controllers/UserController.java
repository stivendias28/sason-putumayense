package backend.sasonptumayense.Controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.User;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(name = "filter", required = false) String filter) {
        if(filter != null && !filter.isEmpty()) {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(
                    HttpStatus.OK, 
                    "OK", 
                    users
                        .stream()
                        .filter(user -> user.getName().toLowerCase().contains(filter.toLowerCase()) 
                            || user.getIdentificationNumber().toLowerCase().contains(filter.toLowerCase())
                            )
                            .collect(Collectors.toList())
                ), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", userService.getAllUsers()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getUserWithUsername(@RequestParam(name = "username", required = true) String username) {
        User user = userService.getUserByUsername(username);

        return (user == null) ?
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND, "User not found", null), HttpStatus.NOT_FOUND) :
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", user), HttpStatus.OK);
    }

}
