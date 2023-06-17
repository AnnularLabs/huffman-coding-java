package com.huffmanCode;

import com.huffmanCode.util.HNode;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import static com.huffmanCode.config.letterConfig.letterFrequencies;
import static com.huffmanCode.util.huffmanUtil.createHuffman;
import static com.huffmanCode.util.huffmanUtil.initHuffmanTree;
import static com.huffmanCode.util.operateUtil.coding;
import static com.huffmanCode.util.operateUtil.decoding;

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

}
