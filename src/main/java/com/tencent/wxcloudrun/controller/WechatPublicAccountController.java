/**
 * FileName: WechatPublicAccountController
 * Author:   SanBai
 * Date:     2022/11/5 12:50
 * Description:
 */
package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import cn.hutool.crypto.SecureUtil;

/**
 * 〈〉
 *
 * @author SanBai
 * @create 2022/11/5
 * @since 1.0.0
 */
@RestController
@RequestMapping("wechat/publicAccount")
public class WechatPublicAccountController {
    // 微信页面填写的token，必须保密
    private static final String TOKEN = "access_token";

    @GetMapping("validate")
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp, nonce, TOKEN};
        Arrays.sort(arr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        // 这里利用了hutool的加密工具类
        String sha1 = SecureUtil.sha1(sb.toString());
        // 3. 加密后的字符串与signature对比，如果相同则该请求来源于微信，原样返回echostr
        if (sha1.equals(signature)) {
            return echostr;
        }
        // 接入失败
        return null;
    }
}
