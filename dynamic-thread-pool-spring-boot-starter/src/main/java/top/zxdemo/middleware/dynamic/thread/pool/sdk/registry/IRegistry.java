package top.zxdemo.middleware.dynamic.thread.pool.sdk.registry;

import top.zxdemo.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @description 注册中心接口，上报
 * @author ZhangXing
 */
public interface IRegistry {

    // 所有的
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolEntities);

    // 单个的
    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
