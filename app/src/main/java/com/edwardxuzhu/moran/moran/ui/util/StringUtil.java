package com.edwardxuzhu.moran.moran.ui.util;

import java.util.regex.Pattern;

/**
 * Created by edwardxu.zhu on 2015/10/27.
 */
public class StringUtil {
    //email
    private final static Pattern emailer
            = Pattern.compile("\\\\w+([-+.']\\\\w+)*@\\\\w+([-.]\\\\w+)*\\\\.\\\\w+([-.]\\\\w)*");

    public static boolean isEmpty(String input){
        if(input == null || "".equals(input)){
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(String email){
        if(email == null || email.trim().length() == 0){
            return false;
        }
        return emailer.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        if(password.trim().length() < 6){
            return false;
        } else
            return true;
    }

    public static boolean isSamePassword(String password, String password2){
        if(password.equals(password2)){
            return true;
        }else
            return false;
    }

    public static boolean isRepeatNickname(String nickname){
        return true;
    }

    public static String encryptPhoneNumber(String phone){
        if(phone == null){
            return "***";
        }
        if(phone.length() < 10){
            return phone;
        }
        return phone.substring(0,3) + "***" + phone.substring(6,10);
    }

    public static String convertUserDeliveryAddress(String address){
        String[] info = address.split("#");
        StringBuffer text = new StringBuffer();
        text
                .append(info[1]) //province
                .append(info[2]) //city
                .append(info[3]) // zone
                .append(info[4]) // street
                .append("\n")
                .append(info[0]) // name
                .append(" ")
                .append(info[6]) // phone
        ;

        return text.toString();
    }
}
