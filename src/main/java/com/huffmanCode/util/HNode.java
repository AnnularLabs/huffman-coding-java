package com.huffmanCode.util;

/**
 * ClassName: HNode
 * Description:
 *
 * @Author Agility6
 * @Create 2023-06-17
 * @Version 1.0
 */
public class HNode {
  private char str;
  private int weight;
  private int parent;
  private int leftChild;
  private int rightChild;

  // --------- init ----------
  public HNode(char str, int weight) {
    this.str = str;
    this.weight = weight;
    this.parent = -1;
    this.leftChild = -1;
    this.rightChild = -1;
  }

  // 设置Huffman编码生成的初始结点
  public HNode() {
    this.str = ' ';
    this.weight = 999;
    this.parent = -1;
    this.leftChild = -1;
    this.rightChild = -1;
  }

  // ---------- get/set ----------

  public char getStr() {
    return str;
  }

  public void setStr(char str) {
    this.str = str;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getParent() {
    return parent;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }

  public int getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(int leftChild) {
    this.leftChild = leftChild;
  }

  public int getRightChild() {
    return rightChild;
  }

  public void setRightChild(int rightChild) {
    this.rightChild = rightChild;
  }
}
