package com.example.mybatis.demomybatis.datastructures.tree;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * 普通二叉树的结构可以仅仅包含下面三个属性，节点的值，节点的左子树、节点的右子树
 *
 * 树的高度/深度：是指从根节点算起1，一直到叶子节点的层级
 *
 * 树的度：针对一个节点而言的，一个节点有几颗子树，那么这个节点的度就是几
 * @author jacksparrow414
 * @date 2021/3/7
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BinaryTree {
    
    @NonNull
    private String val;
    
    private BinaryTree left;
    
    private BinaryTree right;
}
