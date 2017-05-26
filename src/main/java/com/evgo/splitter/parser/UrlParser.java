package com.evgo.splitter.parser;


import java.util.List;

public interface UrlParser {

    List<String> parse(String url) throws Exception;

}
