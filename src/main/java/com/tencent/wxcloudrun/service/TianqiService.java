package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author: wenqiaogang
 * @DateTime: 2022/8/23 12:49
 * @Description: TODO
 */
public interface TianqiService {
    JSONObject getWeatherByCity(String id);

    JSONObject getWeatherByIP();
    Map<String, String> getTheNextThreeDaysWeather();

}
