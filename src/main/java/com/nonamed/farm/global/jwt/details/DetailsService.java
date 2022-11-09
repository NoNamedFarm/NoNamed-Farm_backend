package com.nonamed.farm.global.jwt.details;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nonamed.farm.domain.user.domain.repository.UserRepository;
import com.nonamed.farm.domain.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Details loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .map(Details::new)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
