package com.merchandise.cardnote.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.merchandise.cardnote.entity.SecurityUser;
import com.merchandise.cardnote.entity.UserCollections;
import com.merchandise.cardnote.repository.UserCollectionsRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserCollectionsRepo userCollectionsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCollections> findUser = userCollectionsRepo.findByUsername(username);
        if (!findUser.isPresent())
            throw new UsernameNotFoundException("존재하지 않는 username입니다.");

        log.info("loadUserByUsername user.username = {}", username);

        return new SecurityUser(findUser.get());
    }

}