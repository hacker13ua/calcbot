package org.esurovskiy.tg;

public class SimpleEncryptor implements Encryptor {
    @Override
    public String encrypt(final String text) {
        final char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i]++;
        }
        return new String(chars);
    }
}
