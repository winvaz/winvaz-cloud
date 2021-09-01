package com.icore.winvaz.winvazbus.service;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/6/4 16:51
 * @Version 1.0.0
 */
public interface Consumer {
    void receive(String message);
}
