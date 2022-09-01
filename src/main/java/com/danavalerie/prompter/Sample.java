/*
   DISCLAIMER OF WARRANTY. The following code is provided "as is." The author makes no express or implied warranty
   as to its merchantability or fitness for any particular purpose, and you accept all risk should you decide to
   use this work in any form.

   This work is hereby dedicated to the public domain.
 */

package com.danavalerie.prompter;

public class Sample {

    public static void main(String[] args) {
        Prompter prompter = new Prompter(System.in);
        int age = prompter.promptForInt("How old are you? ");
        int year = prompter.promptForInt("What year were you born? ");
        System.out.println("Then, we must be in the year " + (age + year) + " or " + (age + year + 1));
    }

}
