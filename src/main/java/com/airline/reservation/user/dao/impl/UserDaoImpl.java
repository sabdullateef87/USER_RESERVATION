package com.airline.reservation.user.dao.impl;

import com.airline.reservation.user.dao.UserDao;
import com.airline.reservation.user.dao.util.UserDaoUtil;
import com.airline.reservation.user.dao.util.UserServiceUtil;
import com.airline.reservation.user.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mssqlclient.MSSQLConnectOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

  @Autowired
  SqlConnectionDao db;

  @Autowired
  UserServiceUtil uutil;

  @Override
  public Future<UserModel> createUser(RoutingContext routingContext, UserModel user) {
    Promise<UserModel> prom = Promise.promise();
    db.getConnection()
        .onSuccess(connection -> {
          JsonArray params = UserDaoUtil.getCreateNewUserParams(user);
          String query = UserDaoUtil.getCreateNewUserMySql();
          connection.queryWithParams(query, params, create -> {
            if (create.succeeded()) {
              ResultSet rs = create.result();
              List<JsonObject> rsRow = rs == null ? null : rs.getRows();
              if (rsRow != null && rsRow.size() > 0) {
                JsonObject rsRowObj = rsRow.get(0);
                uutil.getObjectFromJson(rsRowObj, UserModel.class)
                    .onSuccess(prom::complete).onFailure(prom::fail);
                connection.close();
              } else {
                prom.fail("User could not be created successfully");
              }
            } else {
              prom.fail(create.cause());
              log.error(String.valueOf(create.cause()));
              connection.close();
            }
          });
        })
        .onFailure(prom::fail);
    return prom.future();
  }

  @Override
  public Future<UserModel> getUserById(int id) {
    Promise<UserModel> prom = Promise.promise();
    db.getConnection()
        .onSuccess(connection -> {
          JsonArray param = UserDaoUtil.getUserByIdParams(id);
          String query = UserDaoUtil.getUserById();
          connection.queryWithParams(query, param, result -> {
            if (result.succeeded()) {
              ResultSet rs = result.result();
              List<JsonObject> rsRow = rs == null ? null : rs.getRows();
              if (rsRow != null && rsRow.size() > 0) {
                JsonObject rsRowObj = rsRow.get(0);
                uutil.getObjectFromJson(rsRowObj, UserModel.class)
                    .onSuccess(prom::complete).onFailure(prom::fail);
                connection.close();
              } else {
                prom.fail("User could not be created successfully");
              }
              log.info("" + rsRow);
            } else {
              prom.fail(result.cause());
              log.error(String.valueOf(result.cause()));
              connection.close();
            }
          });
        });
    return prom.future();
  }

  @Override
  public Future<UserModel> getAllUsers() {
    return null;
  }

  @Override
  public Future<UserModel> updateUserRecord(Long id) {
    return null;
  }

  @Override
  public Future<UserModel> deleteUser(String username) {
    return null;
  }
}
