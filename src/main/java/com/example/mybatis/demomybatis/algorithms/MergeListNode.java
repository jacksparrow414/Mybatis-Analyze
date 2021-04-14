package com.example.mybatis.demomybatis.algorithms;

import com.example.mybatis.demomybatis.datastructures.List.ListNode;
import com.example.mybatis.demomybatis.datastructures.List.ListUtil;

/**
 * @author jacksparrow414
 * @date 2021/4/14 16:00
 */
public class MergeListNode {

    public ListNode mergeTwoList(ListNode first, ListNode second) {
        // 初始化一个新链表
        ListNode node = new ListNode(-1);
        ListNode prev = node;
        while (first != null && second != null) {
            if (first.getVal() <= second.getVal()) {
                prev.setNext(first);
                first = first.getNext();
            }else {
                prev.setNext(second);
                second = second.getNext();
            }
            prev = prev.getNext();
        }
        // 不满足条件时，必有一个链表已经遍历完了，那么将剩下的链表链到末尾即可
        if (first == null) {
            prev.setNext(second);
        }else {
            prev.setNext(first);
        }
        return node.getNext();
    }

    public static void main(String[] args) {
        MergeListNode mergeListNode = new MergeListNode();
        ListNode first = ListUtil.generateListNode();
        ListNode second = ListUtil.generateListNode();
        mergeListNode.mergeTwoList(first, second);
    }
}
