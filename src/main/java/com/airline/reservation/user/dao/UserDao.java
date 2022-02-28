package com.airline.reservation.user.dao;

import com.airline.reservation.user.models.UserModel;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

public interface UserDao {
  Future<UserModel> createUser(RoutingContext routingContext, UserModel user);

  Future<UserModel> getUserById(int id);

  Future<UserModel> getAllUsers();

  Future<UserModel> updateUserRecord(Long id);

  Future<UserModel> deleteUser(String username);
}
