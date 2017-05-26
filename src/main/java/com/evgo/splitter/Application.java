package com.evgo.splitter;


import com.evgo.splitter.parser.RegExpUrlParser;
import com.evgo.splitter.parser.StateMachineUrlParser;
import com.evgo.splitter.parser.UrlParser;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        UrlParser regExpUrlParser = new RegExpUrlParser();
        UrlParser stateMachineUrlParser = new StateMachineUrlParser();
        String url = args[0];
        try {
            long startTimeRegExp = System.nanoTime();
            regExpUrlParser.parse(url);
            long endTimeRegExp = System.nanoTime();

            long startTimeState = System.nanoTime();
            List<String> parse = stateMachineUrlParser.parse(url);
            long endTimeState = System.nanoTime();

            parse.stream().forEach(System.out::println);

            System.out.println("Regex : " + (endTimeRegExp - startTimeRegExp) / 100000 + " msec");
            System.out.println("State : " + (endTimeState - startTimeState) / 100000 + " msec");

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }


    }


}
