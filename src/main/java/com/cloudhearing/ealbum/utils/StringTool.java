package com.cloudhearing.ealbum.utils;

public class StringTool {

    static public String getSNTail(int inputIndex, int inputTotal) {
        String totalStr = String.valueOf(inputTotal);
        int length = totalStr.length();
        //System.out.println(length);
        String indexStr = String.valueOf(inputIndex);
        int strLength = indexStr.length();
        String temp = "";
        if (strLength < length) {
            int zeroPatch = (length - strLength);
            for (int i = 0; i < zeroPatch; i++) {
                temp = temp + "0";
            }
        }
        temp = temp + indexStr;
        //System.out.println(temp);
        return temp;
    }
}
