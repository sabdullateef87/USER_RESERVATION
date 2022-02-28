package com.airline.reservation.user.dao.util;

import com.airline.reservation.user.models.UserModel;
import com.microsoft.sqlserver.jdbc.StringUtils;
import io.netty.util.internal.StringUtil;
import io.vertx.core.json.JsonArray;

public class UserDaoUtil {
  public static JsonArray getCreateNewUserParams(UserModel userModel) {
    JsonArray param = new JsonArray();
    if (!StringUtils.isEmpty(userModel.getUserName())) {
      param.add(userModel.getUserName());
    } else {
      param.addNull();
    }
    if (!StringUtils.isEmpty(userModel.getFirstName())) {
      param.add(userModel.getFirstName());
    } else {
      param.addNull();
    }
    if (!StringUtils.isEmpty(userModel.getLastName())) {
      param.add(userModel.getLastName());
    } else {
      param.addNull();
    }
    if (!StringUtils.isEmpty(userModel.getEmail())) {
      param.add(userModel.getEmail());
    } else {
      param.addNull();
    }
    if (!StringUtils.isEmpty(userModel.getAddress())) {
      param.add(userModel.getAddress());
    } else {
      param.addNull();
    }
    if (!StringUtils.isEmpty(userModel.getPhoneNumber())) {
      param.add(userModel.getPhoneNumber());
    } else {
      param.addNull();
    }
    return param;
  }

  public static String getCreateNewUserMySql() {
    return "CALL sp_create_new_user(?, ?, ?, ?, ?, ?)";
  }

  public static JsonArray getUserByIdParams(int id) {
    JsonArray param = new JsonArray();
    param.add(id);

    return param;
  }

  public static String getUserById() {
    return "CALL sp_get_user_by_id(?)";
  }
}
