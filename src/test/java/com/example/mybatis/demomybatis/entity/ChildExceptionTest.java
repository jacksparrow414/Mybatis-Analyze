package com.example.mybatis.demomybatis.entity;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2021/4/11
 */
public class ChildExceptionTest {
    
    public class Father {
        public void father_function() throws IOException {
            new File("a.txt");
        }
    }
    
    class Son extends Father {
//        @Override
//        public void father_function() throws Exception {
//            new File("b.txt");
//        }
    }
    
    @Test
    void test() {
        Father fs = new Son();
        try {
            fs.father_function();
        }catch (IOException ie) {
            System.out.println("发生了异常");
        }
    }
    
    
}
