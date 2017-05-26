package com.evgo.splitter;


import com.evgo.splitter.parser.RegExpUrlParser;
import com.evgo.splitter.parser.StateMachineUrlParser;
import com.evgo.splitter.parser.UrlParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UrlParseTest {

    @Test
    public void parseRegExp() throws Exception {
        UrlParser regExpUrlParser = new RegExpUrlParser();
        List<String> parse = regExpUrlParser.parse("http://host:8090/path?params");
        assertEquals(parse.get(0),"http");
        assertEquals(parse.get(1),"host");
        assertEquals(parse.get(2),"8090");
        assertEquals(parse.get(3),"path");
        assertEquals(parse.get(4),"params");
    }

    @Test
    public void parseStateMachine() throws Exception {
        UrlParser parseState = new StateMachineUrlParser();
        List<String> parse = parseState.parse("http://host:8090/path?params");
        assertEquals(parse.get(0),"http");
        assertEquals(parse.get(1),"host");
        assertEquals(parse.get(2),"8090");
        assertEquals(parse.get(3),"path");
        assertEquals(parse.get(4),"params");
    }

    @Test
    public void parseStateMachineWithoutPort() throws Exception {
        UrlParser parseState = new StateMachineUrlParser();
        List<String> parse = parseState.parse("http://host/path?params=test");
        assertEquals(parse.get(0),"http");
        assertEquals(parse.get(1),"host");
        assertEquals(parse.get(2),"path");
        assertEquals(parse.get(3),"params=test");
    }

    @Test
    public void parseStateMachineWithoutParams() throws Exception {
        UrlParser parseState = new StateMachineUrlParser();
        List<String> parse = parseState.parse("http://host/path");
        assertEquals(parse.get(0),"http");
        assertEquals(parse.get(1),"host");
        assertEquals(parse.get(2),"path");
    }

    @Test(expected = Exception.class)
    public void invalidFormat() throws Exception {
        UrlParser parseState = new StateMachineUrlParser();
        parseState.parse("http/host/path");
    }


}
