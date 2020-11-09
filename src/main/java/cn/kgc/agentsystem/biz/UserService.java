package cn.kgc.agentsystem.biz;

import cn.kgc.agentsystem.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> getUserList(User user);
    User getUserByIdOrUserCode(User user);
    User login(User user);
    boolean checkOver(User user,String userCode, String userPassword);
    int addUser(User user);
    int modifyUser(User user);
    int deleteUser(User user);
    int count(User user);
}
