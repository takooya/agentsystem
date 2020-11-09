package cn.kgc.agentsystem.common;

import cn.kgc.agentsystem.VO.ResultVO;
import cn.kgc.agentsystem.pojo.User;

public class ResultVoUtil {
    public static <T> T success(T loginUser) {
        return loginUser;
    }

    public static ResultVO relogin() {
        return null;
    }
}
