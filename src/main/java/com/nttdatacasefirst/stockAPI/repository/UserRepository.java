package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
