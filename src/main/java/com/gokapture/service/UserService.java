package com.gokapture.service;

import com.gokapture.entity.User;

public interface UserService {
    User save(User user);
    public User findByUsername(String username);
    public boolean existsByUsername(String username);
}
