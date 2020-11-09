package cn.kgc.agentsystem.dao;

import cn.kgc.agentsystem.pojo.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDao {
    /**获得用户列表,表关联查询,as_user和as_role
     * User入参,其中有用属性有:
     * 1,userName:like匹配
     * 2,userCode:like匹配
     * 3,roleId:等于匹配
     * 4,isStart:等于匹配
     * 5,6,starNum,pageSize
     * 返回map<User>*/
    List<User> getUserList(User user);

    User getUserByRoleIdOrUserCode(User user);

    //User findUserByCode(String userCode);

    //User getLoginUser(User user);

    @Transactional
    int addUser(User user);

    @Transactional
    int modifyUser(User user);

    int deleteUser(User user);

    int count(User user);

    //int isExistLoginUser(User user);
}
