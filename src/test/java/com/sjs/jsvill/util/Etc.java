package com.sjs.jsvill.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Etc {

    @Test
    public void nullSizeTest() {
        List<String> testList = new ArrayList<>();
        testList.add(null);
        testList.add(null);
        System.out.println(testList);
        System.out.println(testList.size());
    }

}
