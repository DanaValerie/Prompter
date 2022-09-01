package com.danavalerie.prompter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Function;

public final class Prompter {

    private final BufferedReader _in;
    private String _errorMessage;

    public Prompter(InputStream in) {
        this(in, null);
    }

    public Prompter(InputStream in, String errorMessage) {
        _in = new BufferedReader(new InputStreamReader(in));
        _errorMessage = errorMessage == null ? "Invalid value, please try again." : errorMessage;
    }

    public String promptForString(String prompt) {
        return prompt(prompt, Function.identity());
    }

    public byte promptForByte(String prompt) {
        return prompt(prompt, Byte::parseByte);
    }

    public short promptForShort(String prompt) {
        return prompt(prompt, Short::parseShort);
    }

    public int promptForInt(String prompt) {
        return prompt(prompt, Integer::parseInt);
    }

    public long promptForLong(String prompt) {
        return prompt(prompt, Long::parseLong);
    }

    public double promptForFloat(String prompt) {
        return prompt(prompt, Float::parseFloat);
    }

    public double promptForDouble(String prompt) {
        return prompt(prompt, Double::parseDouble);
    }

    private <T> T prompt(String prompt, Function<String,T> parseFunction) {
        try {
            while (true) {
                System.out.print(prompt);
                System.out.flush();
                String line = _in.readLine();
                try {
                    return parseFunction.apply(line);
                } catch (NumberFormatException ex) {
                    System.out.println(_errorMessage);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
