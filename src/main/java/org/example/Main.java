package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ArrayList<HNode> nodeList = initHuffmanTree();

        createHuffman(nodeList, letterFrequencies.length);

        Scanner scanner = new Scanner(System.in);

        System.out.println("输入Words🤖");
        String words = scanner.nextLine();
        String wordsCode = coding(nodeList, words);
        System.out.println("编码结果 --> " + wordsCode);

        System.out.println("输入Code🤖");
        String wordsDecoding = scanner.nextLine();
        String word = decoding(nodeList, wordsDecoding);
        System.out.println("解码结果 --> " + word);
    }

    public static char[] letterFrequencies = {
            'q', 'j', '!', '?', 'z',
            'x', 'v', 'k', 'w', 'y',
            'f', 'b', 'g', 'h', 'm',
            '.', 'p', 'd', 'u', ' ',
            'c', 'l', 's', 'n', 't',
            'o', 'i', 'r', 'a', 'e'
    };


    // 定义HuffmanTree的类型
    public static class HNode {
        private char str;
        private int weight;
        private int parent;
        private int leftChild;
        private int rightChild;

        // 设置已有节点的默认值例如（abcdefg...）
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

        // Getter方法
        public char getStr() {
            return str;
        }

        public int getWeight() {
            return weight;
        }

        public int getParent() {
            return parent;
        }

        public int getLeftChild() {
            return leftChild;
        }

        public int getRightChild() {
            return rightChild;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public void setLeftChild(int leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(int rightChild) {
            this.rightChild = rightChild;
        }
    }


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
        for (int i = 0; i < letterFrequencies.length; i++) {
            char currentChar = letterFrequencies[i];
            nodeList.add(new HNode(currentChar, i + 1));
        }

        // 添加HuffmanTree生成的节点(使用默认值)
        for (int i = 1; i <= letterFrequencies.length - 1; i++) {
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
            int[] resultMin = SelectMin(nodeList, i - 1);
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
            int child = findIndex(words.charAt(i)) + 1;
            int parent = nodeList.get(child).getParent();

            // 初始化栈
            Stack<Integer> codeStack = new Stack<>();
            while(parent != -1) {
                if (nodeList.get(parent).leftChild == child) {
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

    /**
     * 寻找当前字符所处的下标位置
     * @param word
     * @return
     */
    public static int findIndex(char word) {
        for (int i = 0; i < letterFrequencies.length; i++) {
            if (letterFrequencies[i] == word) {
                return i;
            }
        }
        return -1;
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
