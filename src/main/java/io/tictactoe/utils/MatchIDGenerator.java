package io.tictactoe.utils;

import java.util.Random;

public class MatchIDGenerator {

    private static final Random RANDOM = new Random();

    protected static final String[] DICTIONARY = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F"
    };
    protected static final int dictionaryLength = DICTIONARY.length;
    private static final int DEFAULT_MATCH_ID_SIZE = 10;

    public static String generateMatchID(int size) {
        final StringBuilder builder = new StringBuilder(size);
        for(int i = 0; i < size; i++) {
            final int index = RANDOM.nextInt(dictionaryLength - 1);
            builder.append(DICTIONARY[index]);
        }
        return builder.toString();
    }
    public static String generateMatchID() {
        return MatchIDGenerator.generateMatchID(DEFAULT_MATCH_ID_SIZE);
    }
}
