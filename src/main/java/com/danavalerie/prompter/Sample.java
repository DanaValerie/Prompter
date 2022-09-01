package com.danavalerie.prompter;

public class Sample {

    public static void main(String[] args) {
        Prompter prompter = new Prompter(System.in);
        int age = prompter.promptForInt("How old are you? ");
        int year = prompter.promptForInt("What year were you born? ");
        System.out.println("Then, we must be in the year " + (age + year) + " or " + (age + year + 1));
    }

}
