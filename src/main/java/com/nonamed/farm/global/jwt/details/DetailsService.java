package com.nonamed.farm.global.jwt.details;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.vacation_project.entity.account.AccountRepository;
import com.example.vacation_project.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public Details loadUserByUsername(String id) throws UsernameNotFoundException {

        return accountRepository.findByAccountId(id)
                .map(Details::new)
                .orElseThrow(() -> new NotFoundException("user 정보를 찾을 수 없습니다"));
    }

}
