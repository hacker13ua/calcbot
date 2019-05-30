package org.esurovskiy.tg;

import java.io.FileInputStream;

public class CatCutter {
    private static final int SKIP = 1000;

    public static void main(String[] args) throws Exception{
        try(FileInputStream is = new FileInputStream(
                "/home/esurovskiy/mutate_cat.bmp")){
            byte[] skipArray = new byte[SKIP];
            is.read(skipArray);
            final int length = is.read();
            byte[] textArray = new byte[length];
            is.read(textArray);
            System.out.println(new String(textArray));
        }
    }
}
