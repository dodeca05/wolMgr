package com.pro.WOLmgr.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckVariable {
    private static CheckVariable instance = null;
    private boolean isEmailSmtpCheck = true;
    private CheckVariable() {}
    public static CheckVariable getInstance() {
        if(instance == null) {
            synchronized (CheckVariable.class) {
                if(instance == null) {
                    instance = new CheckVariable();
                }
            }
        }
        return instance;
    }
}
