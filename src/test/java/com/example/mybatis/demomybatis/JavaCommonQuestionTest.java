package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.fixture.FinalMapVO;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

/**
 * java common question test.
 */
public final class JavaCommonQuestionTest {

    @Test(expected = UnsupportedOperationException.class)
    public void assertFinalMapUnPut() {
        FinalMapVO finalMapVO = new FinalMapVO(ImmutableMap.of("haha","hahha"));
        finalMapVO.getMap().put("kk","kk");
    }
}
