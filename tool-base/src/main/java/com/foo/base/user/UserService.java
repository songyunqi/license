package com.foo.base.user;

import com.foo.base.service.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CService<User, Long, UserRequest> {

    @Autowired
    UserMapper userMapper;

    public User getOne(Long aLong) {
        return null;
    }

    public int save(User t) {
        return 0;
    }

    public int delete(List<Long> ids) {
        return 0;
    }

    public int update(List<User> list) {
        return 0;
    }

    public int batchImport(List<User> list) {
        return 0;
    }

    public List<User> list(UserRequest tRequest) {
        return null;
    }

}
