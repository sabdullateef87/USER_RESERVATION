package com.airline.reservation.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("spring.datasource")
@Data
@Configuration
public class DatasourceProperties {
  private String url;
  private String username;
  private String password;
  private String databaseName;
}
