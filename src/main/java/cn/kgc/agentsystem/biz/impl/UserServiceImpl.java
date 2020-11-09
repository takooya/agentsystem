package cn.kgc.agentsystem.biz.impl;

import cn.kgc.agentsystem.biz.UserService;
import cn.kgc.agentsystem.common.MD5;
import cn.kgc.agentsystem.dao.UserDao;
import cn.kgc.agentsystem.enums.ResultEnum;
import cn.kgc.agentsystem.exception.MyException;
import cn.kgc.agentsystem.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    /*
    @Autowired
    是按照类型来装配目标对象的
    */
    /*
    @Autowired
    @Qualifier("名称")
    以上组合是按名称装配目标对象,注意装配类的首字母要小写后转配进入
    因为框架自动生成的对象名会将首字母小写
    */

    @Override
    public List<User> getUserList(User user) {
        return null;
    }

    @Override
    @Transactional
    public User login(User user) {
        if (user.getUserCode().trim().isEmpty()) {
            throw new MyException(ResultEnum.LOGIN_USERCODE_EMPTY);
        }
        if (user.getUserPassword().trim().isEmpty()) {
            throw new MyException(ResultEnum.LOGIN_PASSWORD_EMPTY);
        }
        User targetUser = this.getUserByIdOrUserCode(user);
        String passwordMD5 = MD5.MD5Encode(user.getUserPassword());
        if (targetUser == null || !targetUser.getUserPassword().equals(passwordMD5)) {
            throw new MyException(ResultEnum.LOGIN_INFO_WRONG);
        }
        targetUser.setLastLoginTime(new Date());
        userDao.modifyUser(targetUser);
        return targetUser;
    }

    /**  判断用户名密码是否与user匹配,注意这里的user必须从数据库查询的数据真实的用户 */
    @Override
    public boolean checkOver(User user, String userCode, String userPassword) {
        boolean judgeCode = false;
        boolean judgePassword = false;
        try {
            judgeCode = user.getUserCode().equals(userCode.trim());
            String md5UserPassword = MD5.MD5Encode(userPassword.trim());
            judgePassword = user.getUserPassword().equals(md5UserPassword);
        } catch (MyException e) {
            log.warn("[判断用户名密码]user={}\nuserCode={}\nuserPassword={}",user,userCode,userPassword);
            throw new MyException(ResultEnum.LOGIN_INFO_WRONG);
        }
        return judgeCode && judgePassword;
    }

    @Override
    public User getUserByIdOrUserCode(User user) {
        //TODO 判断是否有userCode和userId同时为空时
        return userDao.getUserByRoleIdOrUserCode(user);
    }

    @Override
    public int addUser(User user) {
        //TODO 用户添加必要字段的判断
        user.setUserPassword(MD5.MD5Encode(user.getUserPassword().trim()));
        return userDao.addUser(user);
    }

    @Override
    @Transactional
    public int modifyUser(User user) {
        //TODO 用户字段修改时的判断
        try{
            user.setUserPassword(MD5.MD5Encode(user.getUserPassword().trim()));
        }catch (MyException e){
            log.warn("[修改用户]user:{}",user);
            throw new MyException(ResultEnum.UPDATE_FAIL);
        }
        return userDao.modifyUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }

    @Override
    public int count(User user) {
        return 0;
    }
}
