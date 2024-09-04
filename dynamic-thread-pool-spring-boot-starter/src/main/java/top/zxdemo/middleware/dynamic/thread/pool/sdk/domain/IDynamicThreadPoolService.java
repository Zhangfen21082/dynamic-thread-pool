package top.zxdemo.middleware.dynamic.thread.pool.sdk.domain;

import top.zxdemo.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @description 动态线程池服务
 * @author ZhangXing
 */
public interface IDynamicThreadPoolService {
    // 查询配置实体列表
    List<ThreadPoolConfigEntity> queryThreadPoolList();

    // 查询某个线程池配置
    ThreadPoolConfigEntity queryThreadPoolConfigByName(String threadPoolName);

    // 更新线程池配置

    void updateThreadPoolConfig(ThreadPoolConfigEntity threadPoolConfigEntity);
}
