package com.example.jokejavalibrary;

import java.util.Random;

public class MyClass {
    private static String[] jokes;
    private static Random random;


    public MyClass() {
        jokes = new String[3];
        jokes[0] = "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"";
        jokes[1] = "Q: How many prolog programmers does it take to change a lightbulb? A: Yes.";
        jokes[2] = "Why do Java developers wear glasses? Because they can't C#";
        jokes[3] = "build a Buildiing is easier than build an android studio";

        random = new Random();
    }

    public static String[] getJokes() {
        return jokes;
    }

    public static String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];

    }

}

