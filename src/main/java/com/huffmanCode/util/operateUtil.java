package com.huffmanCode.util;

import com.huffmanCode.Main;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ClassName: operateUtil
 * Description:
 *
 * @Author Agility6
 * @Create 2023-06-17
 * @Version 1.0
 */
public class operateUtil {

  /**
   * 进行编码
   * @param nodeList
   * @param words
   * @return
   */
  public static String coding(ArrayList<HNode> nodeList, String words) {
    /**
     * 1. 获取输入words
     * 2. 获取每个字符的child,parent
     * 3. child代表着当前字符 -> 逐层往上找 存放到数组中 如果最后parent为零代表到达根节点
     * 4. 编码的规则，利用child和parent关系，如果parent的左孩子等于child则编码为0，否则为1
     */
    String resultCode = "";
//        char[] wordsChar = words.toCharArray();

    for (int i = 0; i < words.length(); i++) {
      int child = commonUtil.findIndex(words.charAt(i)) + 1;
      int parent = nodeList.get(child).getParent();

      // 初始化栈
      Stack<Integer> codeStack = new Stack<>();
      while(parent != -1) {
        if (nodeList.get(parent).getLeftChild() == child) {
          codeStack.push(0);
        } else {
          codeStack.push(1);
        }
        child = parent;
        parent = nodeList.get(child).getParent();
      }

      while (codeStack.size() > 0) {
        resultCode += codeStack.pop();
      }

    }

    return resultCode;
  }

  public static String decoding(ArrayList<HNode> nodeList, String code) {
    String resultCode = "";
    int currentIndex;
    int i = 0;
    while (i < code.length()) {
      currentIndex = nodeList.size() - 1;
      while (nodeList.get(currentIndex).getLeftChild() != -1) {
        if (code.charAt(i) == '0') {
          currentIndex = nodeList.get(currentIndex).getLeftChild();
        } else {
          currentIndex = nodeList.get(currentIndex).getRightChild();
        }
        i++;
      }
      resultCode += nodeList.get(currentIndex).getStr();
    }

    return resultCode;
  }
}
