package com.example.mybatis.demomybatis.shardingsphere.jdbc;

import lombok.NoArgsConstructor;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author duhongbo
 * @date 2020/8/20 15:42
 */
@NoArgsConstructor
public final class CustomPreciseShardingAlgorithm implements PreciseShardingAlgorithm {
    
    @Override
    public String doSharding(final Collection collection, final PreciseShardingValue preciseShardingValue) {
        return null;
    }
}
