package com.example.mybatis.demomybatis.datastructures.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jacksparrow414
 * @date 2021/4/14 11:14
 */
@Getter
@Setter
public class ListNode {

    private int val;

    private ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
