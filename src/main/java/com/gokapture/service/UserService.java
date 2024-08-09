package com.gokapture.service;

import com.gokapture.entity.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
