// package top.itwinkle.publib;
// Yanjin pkg，  services:  jin.itwinkle.top 6100--6400

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HW 11 - server
 *
 * <p> Purdue University -- CS 180 -- 2021</p>
 *
 * @author Jin Yan Purdue CS
 *
 * @version Nov 1oth, 2021
 */

public class StatisticsServer {
    final static String hostname="localhost";  //想想什么环境下需要定义常量 final ？ 为什么放在文件头处定义？
    final static Integer portno=6101;


    public static void main(String[] args) throws IOException {

        StatisticsServer server = new StatisticsServer();

        ServerSocket serverSocket = new ServerSocket(portno);
        Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

/*        writer.write("Client connected.");
        writer.println();
        writer.flush();*/

        while(true) {
            String message = reader.readLine();
            String statistics = server.stringLength(message) + server.numWords(message) + server.punctuation(message)
                    + server.notSpace(message) + server.freqOfDig(message) + server.freqOfLet(message);

            writer.write(statistics);
            writer.println();
            writer.flush();
        }
    }




    public String stringLength (String len) {
        return "length: " + len.length() + "\n";
    }

    public String numWords (String numbers) {
        String input = numbers.trim().replaceAll(" +", " ");
        int space =  0 ;
        int word = 0 ;
        for (int i = 0 ; i < input.length() ; i++) {
            char spaces = input.charAt(i);
            if(spaces == ' ') {
                space ++;
            }
        }
        word = space + 1;
        return "number of words: " + word + "\n";
    }

    public String punctuation (String punct) {
        int numOfPunct = 0 ;
        for (int i = 0 ; i < punct.length() ; i++) {
            char numPunct = punct.charAt(i);
            int numAsci = numPunct;
            /*空格 32 英文标点符号 33-47  58-64  91-96 123-126  数字： 48-57 字符65-90 97-122
                这里写成 >=33 是为了程序的可读性，想想是否需要换成 >32 为什么？ */
            if (((numAsci>=33) && (numAsci<=47))
                    || ((numAsci>=58) && (numAsci<=64))
                    || ((numAsci>=91) && (numAsci<=96))
                    || ((numAsci>=123) && (numAsci<=126))){
                numOfPunct ++;
            }
        }
        return "number of punctuations: " + numOfPunct + "\n";
    }

    public String notSpace (String noSpace) {
        int numChar = 0 ;
        for (int i = 0; i < noSpace.length() ; i++) {
            char characters = noSpace.charAt(i);
            if (characters != ' ') {
                numChar ++;
            }
        }
        return "number of characters: " + numChar + "\n";
    }

    public String freqOfDig (String digits) {
        int[] storeDigits = new int[10];
        for (int i = 0 ; i < digits.length() ; i++) {
            char onlyDigits = digits.charAt(i);
            if (onlyDigits >= '0' && onlyDigits <= '9') {
                storeDigits[onlyDigits - '0'] ++;
            }
        }
        String output = "";
        for (int i = 0; i < 10 ; i++) {
            if (storeDigits[i] !=0)
                output += i + "-" + storeDigits[i] + ", ";
        }
        return output + "\n";
    }

    public String freqOfLet (String letters) {
        String lowerCase = letters.toLowerCase();
        int[] storeLetters = new int[26];
        for (int i = 0 ; i < lowerCase.length() ; i++) {
            char onlyLetters = lowerCase.charAt(i);
            if (onlyLetters >= 'a' && onlyLetters <= 'z') {
                storeLetters[onlyLetters - 'a'] ++;
            }
        }
        String output = "";
        for (int i = 0; i < 26 ; i++) {
            if (storeLetters[i] !=0)
                output += (char)('a'+i) + "-" + storeLetters[i] + ",";
        }
        return output + "\n";
    }







}
