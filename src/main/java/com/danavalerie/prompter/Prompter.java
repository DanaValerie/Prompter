/*
   DISCLAIMER OF WARRANTY. The following code is provided "as is." The author makes no express or implied warranty
   as to its merchantability or fitness for any particular purpose, and you accept all risk should you decide to
   use this work in any form.

   This work is hereby dedicated to the public domain.
 */

package com.danavalerie.prompter;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

/**
 * This class is meant to be a drop-in replacement for the use of Scanner when writing textbooks. It fits nearly
 * all beginner use cases of scanner, it's more functional than scanner, and it's easier to understand than
 * Scanner for a beginning Java programmer. The code is public domain, feel free to copy/paste into your
 * textbook or lessons. And in the spirit of public domain, you can even remove these notices, if you wish.
 *
 * @author Dana Valerie Reese, 2022
 */
public final class Prompter {

    private static String _errorMessage = "Invalid value, please try again.";

    private Prompter() {
    }

    public static void setErrorMessage(String newErrorMessage) {
        _errorMessage = _errorMessage;
    }

    /**
     * Reads a line of text as a freeform string.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static String promptForString(String prompt) {
        return prompt(prompt, Function.identity());
    }

    /**
     * Reads a line of text as a byte. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static byte promptForByte(String prompt) {
        return prompt(prompt, Byte::parseByte);
    }

    /**
     * Reads a line of text as a short. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static short promptForShort(String prompt) {
        return prompt(prompt, Short::parseShort);
    }

    /**
     * Reads a line of text as an int. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static int promptForInt(String prompt) {
        return prompt(prompt, Integer::parseInt);
    }

    /**
     * Reads a line of text as a long. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static long promptForLong(String prompt) {
        return prompt(prompt, Long::parseLong);
    }

    /**
     * Reads a line of text as a float. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static float promptForFloat(String prompt) {
        return prompt(prompt, Float::parseFloat);
    }

    /**
     * Reads a line of text as a double. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static double promptForDouble(String prompt) {
        return prompt(prompt, Double::parseDouble);
    }

    /**
     * Reads a line of text as a BigDecimal. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static BigDecimal promptForBigDecimal(String prompt) {
        return prompt(prompt, BigDecimal::new);
    }

    /**
     * Reads a line of text as a BigInteger. If a valid value is not entered, the error message is displayed,
     * and the user re-prompted repeatedly until a valid value is entered.
     *
     * @param prompt The prompt to be displayed
     * @return the value entered by the user
     */
    public static BigInteger promptForBigInteger(String prompt) {
        return prompt(prompt, BigInteger::new);
    }

    private static <T> T prompt(String prompt, Function<String, T> parseFunction) {
        try {
            while (true) {
                System.out.print(prompt);
                System.out.flush();
                String line = readLine();
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

    private static String readLine() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = System.in;
        while (true) {
            int b = in.read();
            if (b < 0) {
                throw new EOFException();
            }
            switch (b) {
                case 13:
                    // ignore carriage return
                    break;
                case 10:
                    return baos.toString(); // use platform default encoding
                default:
                    baos.write(b);
            }

        }
    }

}
