package cn.kgc.agentsystem.systeminit;

import cn.kgc.agentsystem.biz.SystemConfigService;
import cn.kgc.agentsystem.biz.impl.SystemConfigServiceImpl;
import cn.kgc.agentsystem.common.Constants;
import cn.kgc.agentsystem.dao.SystemConfigDao;
import cn.kgc.agentsystem.pojo.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author takooya
 * @Description
 * @Date:created in 11:08 2018/3/29
 */
@Slf4j
public class Systeminit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*****系统配置项********/
        // 1.拿到IOC容器 WebApplicationContext
        log.info("初始化Spring WebApplicationContext 开始");
        Constants.ctx = WebApplicationContextUtils
                .getWebApplicationContext(sce.getServletContext());
        if (null == Constants.ctx)
            log.warn("初始化Spring WebApplicationContext 失败");
        log.info("初始化Spring WebApplicationContext 完成");
        // 2.拿到systemConfigService
        if (Constants.ctx != null) {
            System.out.println("初始化配置项开始");
            SystemConfigService systemConfigService = (SystemConfigService) Constants.ctx
                    .getBean("systemConfigServiceImpl");
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setIsStart(1);//仅使用已启用的配置项
            Constants.SYSTEM_CONFIG_LIST = systemConfigService.getSystemConfigListIsStart(systemConfig);// 3.调用 service的方法
            Constants.systemConfigSetter();
            log.info("[账户类型]:{}" , Constants.ACCOUNT_CONFIG_LIST);
            log.info("[服务类型]:{}" , Constants.SERVICE_CONFIG_LIST);
            log.info("[服务年限]:{}" , Constants.MAX_SERVICE_YEAR_CONFIG_LIST);
            log.info("[App地址]:{}" , Constants.APP_URL_CONFIG_LIST);
            log.info("[客户类型]:{}" , Constants.CUSTOM_TYPE_CONFIG_LIST);
            log.info("[证件类型]:{}" , Constants.CERTIFICATE_TYPE_CONFIG_LIST);
            log.info("[优惠类型]:{}" , Constants.DISCOUNT_TYPE_CONFIG_LIST);
            log.info("初始化配置项结束");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
