package projectnewsaggregator.service;

import projectnewsaggregator.dto.*;
import projectnewsaggregator.exception.RegistrationException;
import projectnewsaggregator.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
    UserResponseDto authenticate(UserLoginRequestDto requestDto);
    User findById(String id);
    UserPreferencesDto getUserPreferences(String id);
    UserPreferencesDto update (String id, UpdatePreferencesRequestDto requestDto);
}
