package com.example.mybatis.demomybatis.algorithms;

/**
 * 查找链表中倒数第K个节点.
 * @author jacksparrow414
 * @date 2021/3/8
 */
public class FindListNode {
    
    public ListNode find(ListNode node, int nodeNum) {
        if (node == null || nodeNum < 0) {
            return null;
        }
        ListNode first = node;
        ListNode second = node;
        for (int i = 0; i < nodeNum - 1; i++) {
            if (first.getNext() != null) {
                first = first.getNext();
            }else {
                return null;
            }
        }
        if (first.getNext() != null) {
            first = first.getNext();
            second = second.getNext();
        }
        return second;
    }
}
