package top.zxdemo.middleware.dynamic.thread.pool.sdk.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zxdemo.middleware.dynamic.thread.pool.sdk.domain.DynamicThreadPoolService;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description  SpringBoot加载配置入口
 * @author ZhangXing
 */

@Configuration
public class DynamicThreadPoolAutoConfig {
    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolAutoConfig.class);

    private String applicationName;
    // threadPoolExecutorMap 可能有多个线程池

    @Bean("dynamicThreadPollService")
    public DynamicThreadPoolService dynamicThreadPoolService(ApplicationContext applicationContext, Map<String, ThreadPoolExecutor> threadPoolExecutorMap) {
        applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        if (StringUtils.isBlank(applicationName)) {
            applicationName = "缺省的";
            logger.warn("动态线程池，启动提示。SpringBoot 应用未配置 spring.application.name 无法获取到应用名称！");
        }

        logger.info("线程池信息：{}", JSON.toJSONString(threadPoolExecutorMap.keySet()));


        return new DynamicThreadPoolService(applicationName, threadPoolExecutorMap);
    }
}
