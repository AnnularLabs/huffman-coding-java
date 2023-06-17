package com.huffmanCode.util;

import com.huffmanCode.Main;
import com.huffmanCode.config.letterConfig;

import java.util.ArrayList;

/**
 * ClassName: huffmanUtil
 * Description:
 *
 * @Author Agility6
 * @Create 2023-06-17
 * @Version 1.0
 */
public class huffmanUtil {

  /**
   * 初始化HuffmanTree
   * @return
   */
  public static ArrayList<HNode> initHuffmanTree() {

    // 创建动态数组，添加HNode结点
    ArrayList<HNode> nodeList = new ArrayList<>();
    // 下标为0的舍去
    nodeList.add(null);



    // 添加HuffmanTree字符节点
    for (int i = 0; i < letterConfig.letterFrequencies.length; i++) {
      char currentChar = letterConfig.letterFrequencies[i];
      nodeList.add(new HNode(currentChar, i + 1));
    }

    // 添加HuffmanTree生成的节点(使用默认值)
    for (int i = 1; i <= letterConfig.letterFrequencies.length - 1; i++) {
      nodeList.add(new HNode());
    }

    return nodeList;
  }

  /**
   * 对HuffmanTree进行初始化
   * @param nodeList
   * @param nodeListLength
   */
  public static void createHuffman(ArrayList<HNode> nodeList, int nodeListLength) {
    for (int i = nodeListLength + 1; i < 2 * nodeListLength; i++) {
      // 挑选数组中最小的两个
      int[] resultMin = commonUtil.SelectMin(nodeList, i - 1);
      int minFirst = resultMin[0];
      int minSecond = resultMin[1];
      /**
       * 1. minFirst的权重 + minSecond的权重
       * 2. 改变minFirst和minSecond,Parent的值
       * 3. 设置新生成的HNode的左孩子和右孩子(遵循左小右大规则)
       */
      nodeList.get(i).setWeight(nodeList.get(minFirst).getWeight() + nodeList.get(minSecond).getWeight());
      nodeList.get(minFirst).setParent(i);
      nodeList.get(minSecond).setParent(i);
      nodeList.get(i).setLeftChild(minFirst);
      nodeList.get(i).setRightChild(minSecond);
    }
  }

}
