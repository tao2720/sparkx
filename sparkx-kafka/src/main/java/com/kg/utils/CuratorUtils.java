package com.kg.utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorUtils {

    private static CuratorFramework client;

    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    static {
//        client = CuratorFrameworkFactory.builder()
//                .connectString("146.56.208.76")
//                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                .build();

        client = CuratorFrameworkFactory.newClient("192.168.10.100", retryPolicy);

    }

    public static CuratorFramework getCuratorClient() {
//        if(client.getState() == CuratorFrameworkState.STOPPED) { // 如果说你的client已经close了
//            client = CuratorFrameworkFactory.builder()
//                    .connectString("146.56.208.76")
//                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
//                    .build();
//        }
        if(client.getState() == CuratorFrameworkState.STOPPED) { // 如果说你的client已经close了
            client=CuratorFrameworkFactory.newClient("192.168.10.100", retryPolicy);
        }
        return client;
    }

    public static void close(CuratorFramework client) {
        if (null != client) client.close();
    }
}
