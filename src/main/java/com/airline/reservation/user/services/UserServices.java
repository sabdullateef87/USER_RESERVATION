package com.airline.reservation.user.services;

import com.airline.reservation.user.models.UserModel;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

public interface UserServices {
  Future<UserModel> createUser(RoutingContext routingContext, UserModel user);

  Future<UserModel> getUserById(RoutingContext routingContext, int id);
}
