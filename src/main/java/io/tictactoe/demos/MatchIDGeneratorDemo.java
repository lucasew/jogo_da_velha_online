package io.tictactoe.demos;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import io.tictactoe.utils.MatchIDGenerator;

public class MatchIDGeneratorDemo {
    public static void main(String[] args) {
        System.out.println(MatchIDGenerator.generateMatchID(10));
    }
}
