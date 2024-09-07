package top.zxdemo.middleware.dynamic.thread.pool.sdk.registry.redis;

import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import top.zxdemo.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import top.zxdemo.middleware.dynamic.thread.pool.sdk.domain.model.valobj.RegistryEnumVO;
import top.zxdemo.middleware.dynamic.thread.pool.sdk.registry.IRegistry;

import java.time.Duration;
import java.util.List;

/**
 * @description Redis注册中心
 * @author ZhangXing
 */
public class RedisRegistry implements IRegistry {

    private RedissonClient redissonClient;

    public RedisRegistry(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    @Override
    public void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolEntities) {
        // 从Redis中获取一个与 THREAD_POOL_CONFIG_LIST_KEY 对应的 RList 对象，用于存储线程池配置列表
        RList<ThreadPoolConfigEntity> list = redissonClient.getList(RegistryEnumVO.THREAD_POOL_CONFIG_LIST_KEY.getKey());

        // 删除Redis中现有的线程池配置列表，以确保列表是最新的
        list.delete();

        // 将新的线程池配置列表添加到Redis中
        list.addAll(threadPoolEntities);
    }

    @Override
    public void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity) {
        // 构造一个用于标识线程池配置参数的缓存键，格式为 "THREAD_POOL_CONFIG_PARAMETER_LIST_KEY_应用名称_线程池名称"
        String cacheKey = RegistryEnumVO.THREAD_POOL_CONFIG_PARAMETER_LIST_KEY.getKey() + "_" + threadPoolConfigEntity.getAppName() + "_" + threadPoolConfigEntity.getThreadPoolName();

        // 从Redis中获取一个与 cacheKey 对应的 RBucket 对象，用于存储单个线程池配置参数
        RBucket<ThreadPoolConfigEntity> bucket = redissonClient.getBucket(cacheKey);

        // 将线程池配置参数存储到Redis中，并设置存储时间为30天
        bucket.set(threadPoolConfigEntity, Duration.ofDays(30));
    }

}
