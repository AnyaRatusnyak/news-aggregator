package projectnewsaggregator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectnewsaggregator.dto.*;
import projectnewsaggregator.exception.AuthenticationException;
import projectnewsaggregator.exception.EntityNotFoundException;
import projectnewsaggregator.exception.RegistrationException;
import projectnewsaggregator.mapper.UserMapper;
import projectnewsaggregator.model.User;
import projectnewsaggregator.repository.UserRepository;
import projectnewsaggregator.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException("Can't register user");
        }
        User user = userMapper.toModel(request);
        System.out.println("Saving user: " + user);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto authenticate(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() ->
                new EntityNotFoundException("Can`t find a user with email: " + requestDto.getEmail()));
        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new AuthenticationException("Invalid email or password");
        }
        return userMapper.toDto(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Can`t find a user with id: " + id));
    }

    @Override
    public UserPreferencesDto getUserPreferences(String id) {
        User user = findById(id);
        return userMapper.preferencesToDto(user);
    }

    @Override
    public UserPreferencesDto update(String id, UpdatePreferencesRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t find a user with id: " + id));
        user.setPreferences(requestDto.getPreferences());
        userRepository.save(user);
        return userMapper.preferencesToDto(user);
    }
}
