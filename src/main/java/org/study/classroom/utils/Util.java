package org.study.classroom.utils;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isNumericStr(String str){
        if (str == null) {
            return false;
        }
        if(str.endsWith(",")){
            str = str.substring(0, str.length()-1);
        }
        Matcher matcher = Pattern.compile("(\\d+,)*\\d+").matcher(str);
        return matcher.matches();
    }

    public static String getName(String str){
        if (str == null )
            return  null;
        String[] strings = str.split("(\\d)*+");
        System.out.println(Arrays.toString(strings));
        if (strings.length == 0) {
            return null;
        }
        StringBuilder s = new StringBuilder("%");
        for (String s1 : strings) {
            if(!s1.equals("")){

                s.append(s1);
                s.append("%");
            }
        }
        return s.toString();
    }

    public static String getNumber(String string) {
        if (string == null) {
            return null;
        }
        String[] strings = string.split("([^\\d])*+");
        System.out.println(Arrays.toString(strings));
        if (strings.length == 0) {
            return null;
        }
        StringBuilder s = new StringBuilder("%");
        for (String s1 : strings) {
            if(!StringUtils.isEmpty(s1)){

                s.append(s1);
                s.append("%");
            }
        }
        return s.toString();
    }
    public static void main(String[] args) {
        String s = "唐21敦红";
        System.out.println(getNumber(s));
    }
}
