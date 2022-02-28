package com.airline.reservation.user.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  public Vertx vertx() {
    return Vertx.vertx();
  }

  @Bean
  public JDBCClient jdbcClient(Vertx vertx, DatasourceProperties datasourceProperties) {
    JsonObject config = new JsonObject();
    config
        .put("url", datasourceProperties.getUrl())
        .put("user", datasourceProperties.getUsername())
        .put("password", datasourceProperties.getPassword());

    return JDBCClient.createShared(vertx, config);
  }
}
