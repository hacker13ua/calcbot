package org.esurovskiy.tg;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CatMutator {
    private static final int SKIP = 1000;
    private static final String TEXT = "ABCDEF";

    public static void main(String[] args) throws Exception {
        try (FileInputStream is =
                     new FileInputStream(
                             "/home/esurovskiy/cat.bmp");
             FileOutputStream os =
                     new FileOutputStream(
                             "/home/esurovskiy/mutate_cat.bmp")
        ) {
            int b = 0;
            int counter = 0;
            int length = TEXT.length();
            final byte[] bytes = TEXT.getBytes();
            int textCounter = 0;
            while ((b = is.read()) != -1) {
                if (counter < SKIP) {
                    os.write(b);
                } else if (counter == SKIP) {
                    os.write(length);
                } else if (counter > SKIP &&
                        counter < SKIP + length + 1) {
                    os.write(bytes[textCounter]);
                    textCounter++;
                } else {
                    os.write(b);
                }
                counter++;
            }
        }
    }
}
