package com.evgo.splitter.parser;

import java.util.ArrayList;
import java.util.List;


public class StateMachineUrlParser implements UrlParser {

    public List<String> parse(String url) throws Exception {
        try {
            List<String> urlParts = new ArrayList<>();
            String schema = url.substring(0, url.indexOf(":"));
            urlParts.add(schema);
            String afterSchema = url.substring(url.indexOf(":") + 3);

            String wholeDomain = afterSchema.substring(0, afterSchema.indexOf("/"));
            String afterDomain = afterSchema.substring(afterSchema.indexOf("/") + 1);

            if (wholeDomain.contains(":")) {
                String domain = wholeDomain.substring(0, wholeDomain.indexOf(":"));
                urlParts.add(domain);
                String port = wholeDomain.substring(wholeDomain.indexOf(":") + 1);
                urlParts.add(port);
            } else {
                urlParts.add(wholeDomain);
            }

            if (afterDomain.contains("?")) {
                String path = afterDomain.substring(0, afterDomain.indexOf("?"));
                urlParts.add(path);
                String params = afterDomain.substring(afterDomain.indexOf("?") + 1);
                urlParts.add(params);
            } else {
                urlParts.add(afterDomain);
            }
            return urlParts;
        } catch (Exception ex) {
            throw new Exception("Url Format Exception");
        }
    }
}
