package projectnewsaggregator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projectnewsaggregator.dto.UserLoginRequestDto;
import projectnewsaggregator.dto.UserRegistrationRequestDto;
import projectnewsaggregator.dto.UserResponseDto;
import projectnewsaggregator.exception.RegistrationException;
import projectnewsaggregator.service.UserService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto register(@Valid @RequestBody UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.authenticate(requestDto);
    }
}
