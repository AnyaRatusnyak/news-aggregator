package projectnewsaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projectnewsaggregator.dto.UpdatePreferencesRequestDto;
import projectnewsaggregator.dto.UserPreferencesDto;
import projectnewsaggregator.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;

    @GetMapping("/preferences/{id}")
    public UserPreferencesDto getUserPreferencesByUserId(@PathVariable String id) {
        return userService.getUserPreferences(id);
    }

    @PatchMapping("/preferences/{id}")
    public UserPreferencesDto updatePreferences(@PathVariable String id,
                                         @RequestBody UpdatePreferencesRequestDto requestDto) {
        return userService.update(id, requestDto);
    }
}
