package com.airline.reservation.user.services.impl;

import com.airline.reservation.user.dao.UserDao;
import com.airline.reservation.user.models.UserModel;
import com.airline.reservation.user.services.UserServices;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserServices {

  @Autowired
  UserDao userDao;

  @Override
  public Future<UserModel> createUser(RoutingContext routingContext, UserModel user) {
    Promise<UserModel> prom = Promise.promise();
    userDao.createUser(routingContext, user)
        .onSuccess(res -> {
          System.out.println("Inside creating user service !!!");
          prom.complete(res);
        }).onFailure(prom::fail);
    return prom.future();
  }

  @Override
  public Future<UserModel> getUserById(RoutingContext routingContext, int id) {
    Promise<UserModel> prom = Promise.promise();
    userDao.getUserById(id)
        .onSuccess(res -> {
          log.info("" + res);
          prom.complete(res);
        }).onFailure(prom::fail);
    return prom.future();
  }
}
