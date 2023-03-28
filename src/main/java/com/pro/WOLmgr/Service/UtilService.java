package com.pro.WOLmgr.Service;

public interface UtilService {

    int makeRandomNumber();
    public void mailSend(String setFrom, String toMail, String title, String content);
}
