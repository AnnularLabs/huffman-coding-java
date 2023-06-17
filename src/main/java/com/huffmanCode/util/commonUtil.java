package com.huffmanCode.util;

import com.huffmanCode.Main;
import com.huffmanCode.config.letterConfig;

import java.util.ArrayList;

/**
 * ClassName: ArraysUtil
 * Description:
 *
 * @Author Agility6
 * @Create 2023-06-17
 * @Version 1.0
 */
public class commonUtil {
    /**
     * 选择数组中最小的两个节点
     * @param nodeList
     * @param SelectLength
     * @return
     */
    public static int[] SelectMin(ArrayList<HNode> nodeList, int SelectLength) {
        int[] result = new int[2];
        ArrayList<HNode> newHuffmanTree = new ArrayList<>();
        // 下标为0的不需要
        newHuffmanTree.add(null);
        // 1. 筛选没有被选中过的HNode 选中则parent值不为-1 如果已经被选中过也许传入保证顺序
        for (int i = 1; i <= SelectLength; i++) {
            // 未被选择过
            if (nodeList.get(i).getParent() == -1) {
                newHuffmanTree.add(nodeList.get(i));
            } else {
                newHuffmanTree.add(null);
            }
        }

        result[0] = SearchHuffmanTreeMinValue(newHuffmanTree);
        // 将选择的第一个最小值标记为null
        newHuffmanTree.set(result[0], null);
        result[1] = SearchHuffmanTreeMinValue(newHuffmanTree);


        return result;
    }

    /**
     * 寻找筛选完数组中最小值
     * @param newNodeList
     * @return
     */
    public static int SearchHuffmanTreeMinValue(ArrayList<HNode> newNodeList) {
        int i = 0;
        int j = newNodeList.size() - 1;

        while (i < j) {
            if (newNodeList.get(i) == null ||
                    newNodeList.get(j) != null &&
                            newNodeList.get(i).getWeight() >= newNodeList.get(j).getWeight()) {
                i++;
            } else if (newNodeList.get(j) == null || newNodeList.get(j).getWeight() >= newNodeList.get(i).getWeight()) {
                j--;
            }
        }

        return j;
    }

    /**
     * 寻找当前字符所处的下标位置
     * @param word
     * @return
     */
    public static int findIndex(char word) {
        for (int i = 0; i < letterConfig.letterFrequencies.length; i++) {
            if (letterConfig.letterFrequencies[i] == word) {
                return i;
            }
        }
        return -1;
    }
}
