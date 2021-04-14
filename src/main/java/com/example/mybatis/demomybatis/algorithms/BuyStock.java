package com.example.mybatis.demomybatis.algorithms;

/**
 * 买股票问题.
 * 题目描述：
 * 给定一个数组，代表每天股票的价格，求能够获得的股票的最大利润是多少.
 * 思路：
 * 股票的最大利润就是数组中两个数字的最大差.
 * 这两个数字最大值、最小值如何确定？
 * 肯定是要对数组进行遍历，那么按照这个思路，每个遍历的数字就是当前的最大值，最小值可以先设置为第一项
 * 要进行两个判断，
 * 当前数字-历史最低值的结果是否比历史最大利润大，如果大，则更新股票历史最大收益
 * 当前数字和历史最低值比较，是否比历史最低小，如果小，则更新股票历史最低值
 * @author jacksparrow414
 * @date 2021/4/14 10:12
 */
public class BuyStock {

    public int maxProfit(int[] stocks) {
        int maxProfit = 0;
        int minPrice = stocks[0];
        for (int i = 1; i < stocks.length; i++) {
            // 如果当前价格大于最低价格，则计算最大利润，当前的最大利润和已存在的最大利润进行比较
             maxProfit = Math.max(maxProfit, stocks[i] - minPrice);
             // 是否更新股票的最低价格
             minPrice = Math.min(minPrice, stocks[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BuyStock buyStock = new BuyStock();
        int[] stockPrice = new int[]{7,1,5,3,6,4};
        int stockMaxProfit = buyStock.maxProfit(stockPrice);
        System.out.println("股票的最大利润是："+ stockMaxProfit);
    }
}
