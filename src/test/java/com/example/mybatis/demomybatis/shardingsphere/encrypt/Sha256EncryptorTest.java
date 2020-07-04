package com.example.mybatis.demomybatis.shardingsphere.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jacksparrow414
 * @date 2020/7/4
 */
@SpringBootTest
public class Sha256EncryptorTest {
    
    @Test
    public void testSha256Encryptor() {
        String ciphertext = "我是jack";
        System.out.println(DigestUtils.sha256Hex(ciphertext));
    }
}
