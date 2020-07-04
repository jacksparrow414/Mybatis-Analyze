package com.example.mybatis.demomybatis.shardingsphere.encrypt;

import java.util.Properties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shardingsphere.encrypt.strategy.spi.Encryptor;

/**
 * 自定义算法.
 *
 * @author jacksparrow414
 * @date 2020/7/4
 */
@Getter
@Setter
public final class Sha256Encryptor implements Encryptor {
    
    private Properties properties = new Properties();
    
    @Override
    public void init() {
    
    }
    
    @Override
    public String encrypt(final Object plaintext) {
        if (null == plaintext) {
            return null;
        }
        return DigestUtils.sha256Hex(String.valueOf(plaintext));
    }
    
    @Override
    public Object decrypt(final String ciphertext) {
        return ciphertext;
    }
    
    @Override
    public String getType() {
        return "SHA256";
    }
    
}
