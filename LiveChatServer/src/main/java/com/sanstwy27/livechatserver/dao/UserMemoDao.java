package com.sanstwy27.livechatserver.dao;

import com.sanstwy27.livechatserver.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */
@Repository
public class UserMemoDao {

    Map<String, UserEntity> userEntityMap = new ConcurrentHashMap<String, UserEntity>();

    public UserEntity findOne(String target) {
        return userEntityMap.get(target);
    }

    public void save(UserEntity userEntity) {
        userEntityMap.put(userEntity.getUuid(), userEntity);
    }
}
