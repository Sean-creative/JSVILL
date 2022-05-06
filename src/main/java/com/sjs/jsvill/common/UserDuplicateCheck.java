package com.sjs.jsvill.common;

import lombok.ToString;

@ToString
public class UserDuplicateCheck {
    public String title;
    public String phone;

    public UserDuplicateCheck(String title, String phone) {
        this.title = title;
        this.phone = phone;
    }
}
