package com.airline.reservation.user.dao.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SqlConnectionDao {

  @Autowired
  SQLClient sqlClient;

  @Autowired
  Vertx vertx;

  public SqlConnectionDao(SQLClient sqlClient) {
    this.sqlClient = sqlClient;
  }

  public Future<SQLConnection> getConnection() {
    return vertx.executeBlocking(promise -> sqlClient.getConnection(conn -> {
      if (conn.failed()) {
        promise.fail(conn.cause());
        return;
      }
      promise.complete(conn.result());
    }));
  }
}
