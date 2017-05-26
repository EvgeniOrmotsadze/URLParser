package com.evgo.splitter.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegExpUrlParser implements UrlParser {

    public List<String> parse(String url) throws Exception {
        List<String> urlParts = new ArrayList<String>();
        String regexPattern = "^(?:((\\w*):)\\/\\/)([^:\\/\\s]+)(?::(\\d*))?(?:\\/([^\\s?#]+)?([?][^?#]*)?(\\w*)?)?";
        //  Pattern pattern = Pattern.compile("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?");
        try {
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(url);
            matcher.find();
            for (int i = 2; i < matcher.groupCount() - 1; i++) {
                String part = matcher.group(i);
                if (part != null) {
                    urlParts.add(part);
                }
            }
            String params = matcher.group(matcher.groupCount() - 1);
            if (params != null) {
                urlParts.add(params.replace("?", ""));
            }
            return urlParts;
        } catch (Exception ex) {
            throw new Exception("Url Format Exception");
        }
    }
}
