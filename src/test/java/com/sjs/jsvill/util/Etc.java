package com.sjs.jsvill.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void File() {
        String path = System.getProperty("user.dir") + "/" + "test.png";
        File convertFile = new File(path);
        try {
            convertFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
