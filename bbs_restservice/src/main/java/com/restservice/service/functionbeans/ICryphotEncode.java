package com.restservice.service.functionbeans;

/**
 * Created by liudeyu on 2019/7/24.
 */
public interface ICryphotEncode {

    String encrypt(String basePass);

    boolean isPasswordEqual(String base,String encodePass);
}
