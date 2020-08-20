package com.example.mybatis.demomybatis.shardingsphere.algorithm;

import java.util.Collection;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

/**
 * @author jacksparrow414
 * @date 2020/8/20
 */
@NoArgsConstructor
public final class CustomPreciseShardingAlgorithm implements PreciseShardingAlgorithm {
    
    @Override
    public String doSharding(final Collection collection, final PreciseShardingValue preciseShardingValue) {
        int hashedKey = Math.abs(preciseShardingValue.getValue().hashCode());
    
        for (Object dbName : collection) {
            String dbNameStr = (String) dbName;
        
            String dbRange = dbNameStr.substring(dbNameStr.indexOf("-") + 1);
            String[] dbRangeArr = dbRange.split("-");
            Integer begin = Integer.valueOf(dbRangeArr[0]);
            Integer end = Integer.valueOf(dbRangeArr[1]);
        
            if (hashedKey >= begin && hashedKey <= end) {
                return dbNameStr;
            }
        }
        return null;
    }
}
