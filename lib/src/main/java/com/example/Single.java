package com.example;

/**
 * @author zhengxiaobin@xiaoyouzi.com
 * @since 16/12/15
 */
public class Single {
    private static Single ourInstance = new Single();

    public static Single getInstance() {
        return ourInstance;
    }

    private Single() {
    }
}
