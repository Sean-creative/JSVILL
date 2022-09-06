package com.sjs.jsvill.controller.util;

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
