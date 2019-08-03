package com.restservice.service.functionbeans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liudeyu on 2019/7/24.
 */
@Service(value = "md5_encode")
public class Md5CryphotImp implements ICryphotEncode {

    private final String salt = "ldyencodefun";

    @Override
    public String encrypt(String basePass) {
        if (StringUtils.isEmpty(basePass)) {
            return basePass;
        }
        try {
            String tmpStr = salt + basePass;
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(tmpStr.getBytes(), 0, basePass.getBytes().length);
            String md5 = new BigInteger(digest.digest()).toString(16);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return basePass;
    }

    @Override
    public boolean isPasswordEqual(String base, String encodePass) {
        String tmpEn= StringUtils.isEmpty(encodePass)?"":encodePass;
        return tmpEn.equals(encrypt(base));
    }
}
