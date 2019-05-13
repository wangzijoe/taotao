package com.taotao.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    @Test
    public void testJedisCluster() {

        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大连接空闲数
        config.setMaxIdle(2);

        //集群结点
        Set<HostAndPort> hostAndPortHashSet = new HashSet<>();
        hostAndPortHashSet.add(new HostAndPort("101.37.148.209", 7001));
        hostAndPortHashSet.add(new HostAndPort("101.37.148.209", 7002));
        hostAndPortHashSet.add(new HostAndPort("101.37.148.209", 7003));
        hostAndPortHashSet.add(new HostAndPort("101.37.148.209", 7004));
        hostAndPortHashSet.add(new HostAndPort("101.37.148.209", 7005));
        hostAndPortHashSet.add(new HostAndPort("101.37.148.209", 7006));
        JedisCluster jc = new JedisCluster(hostAndPortHashSet, config);

//        jc.set("name", "Joe");
//        String value = jc.get("name");
//        System.out.println(value);
        jc.hdel("INDEX_CONTENT_REDIS_KEY");
        jc.close();
    }

}
