package edu.school21.Spring.beans;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor{
    @Override
    public String preProcess(String str) {
        return str.toLowerCase(Locale.ROOT);
    }
}
