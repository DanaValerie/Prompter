/*
   DISCLAIMER OF WARRANTY. The following code is provided "as is." The author makes no express or implied warranty
   as to its merchantability or fitness for any particular purpose, and you accept all risk should you decide to
   use this work in any form.

   This work is hereby dedicated to the public domain.
 */

package com.danavalerie.prompter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

/**
 * This class is meant to be a drop-in replacement for the use of Scanner when writing textbooks. It works better
 * than scanner, it's more functional than scanner, and it's easier to understand than Scanner for a beginning
 * Java programmer. The code is public domain, feel free to copy/paste into your textbook. You can even remove
 * these notices, if you wish.
 *
 * @author Dana Valerie Reese, 2022
 */
public final class Prompter {

    private final String _errorMessage;
    private final BufferedReader _in;

    /**
     * Creates a prompter using the default error message.
     */
    public Prompter() {
        this(null);
    }

    /**
     * Creates a prompter with a user-specified error message.
     */
    public Prompter(String errorMessage) {
        _in = new BufferedReader(new InputStreamReader(System.in));
        _errorMessage = errorMessage == null ? "Invalid value, please try again." : errorMessage;
    }

    /**
     * Reads a line of text as a freeform string.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public String promptForString(String prompt) {
        return prompt(prompt, Function.identity());
    }

    /**
     * Reads a line of text as a byte. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public byte promptForByte(String prompt) {
        return prompt(prompt, Byte::parseByte);
    }

    /**
     * Reads a line of text as a short. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public short promptForShort(String prompt) {
        return prompt(prompt, Short::parseShort);
    }

    /**
     * Reads a line of text as an int. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public int promptForInt(String prompt) {
        return prompt(prompt, Integer::parseInt);
    }

    /**
     * Reads a line of text as a long. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public long promptForLong(String prompt) {
        return prompt(prompt, Long::parseLong);
    }

    /**
     * Reads a line of text as a float. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public float promptForFloat(String prompt) {
        return prompt(prompt, Float::parseFloat);
    }

    /**
     * Reads a line of text as a double. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public double promptForDouble(String prompt) {
        return prompt(prompt, Double::parseDouble);
    }

    /**
     * Reads a line of text as a BigDecimal. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public BigDecimal promptForBigDecimal(String prompt) {
        return prompt(prompt, BigDecimal::new);
    }

    /**
     * Reads a line of text as a BigInteger. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public BigInteger promptForBigInteger(String prompt) {
        return prompt(prompt, BigInteger::new);
    }

    private <T> T prompt(String prompt, Function<String, T> parseFunction) {
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
