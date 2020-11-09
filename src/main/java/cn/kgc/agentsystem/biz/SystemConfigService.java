package cn.kgc.agentsystem.biz;

import cn.kgc.agentsystem.pojo.SystemConfig;

import java.util.List;

public interface SystemConfigService {
    List<String> getSystemConfigListIsStart(SystemConfig systemConfig);
}
