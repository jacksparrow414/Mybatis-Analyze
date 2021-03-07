package com.example.mybatis.demomybatis.datastructures.tree;

/**
 * @author jacksparrow414
 * @date 2021/3/7
 */
public class TreeUtil {
    
    public static BinaryTree generateBinaryTree() {
        BinaryTree treeA = new BinaryTree("A");
        BinaryTree treeB = new BinaryTree("B");
        BinaryTree treeC = new BinaryTree("C");
        BinaryTree treeD = new BinaryTree("D");
        BinaryTree treeE = new BinaryTree("E");
        BinaryTree treeF = new BinaryTree("F");
        BinaryTree treeG = new BinaryTree("G");
        BinaryTree treeH = new BinaryTree("H");
        BinaryTree treeI = new BinaryTree("I");
        BinaryTree treeJ = new BinaryTree("J");
        BinaryTree treeK = new BinaryTree("K");
        treeA.setLeft(treeB);
        treeA.setRight(treeC);
        treeB.setLeft(treeD);
        treeB.setRight(treeE);
        treeC.setLeft(treeF);
        treeC.setRight(treeG);
        treeD.setLeft(treeH);
        treeH.setRight(treeK);
        treeF.setLeft(treeI);
        treeG.setRight(treeJ);
        return treeA;
    }
}
