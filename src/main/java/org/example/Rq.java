package org.example;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String actionName;
    private final Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?", 2);
        actionName = cmdBits[0].trim();
        String queryString;
        if (cmdBits.length > 1) {
            queryString = cmdBits[1].trim();
        } else {
            queryString = "";
        }

        String[] queryStringBits = queryString.split("&");

        for (String queryParam : queryStringBits) {
            String[] queryParamBits = queryParam.split("=", 2);
            String key = queryParamBits[0].trim();
            String value;
            if (queryParamBits.length > 1) {
                value = queryParamBits[1].trim();
            } else {
                value = "";
            }

            if (value.isEmpty()) {
                continue;
            }

            paramsMap.put(key, value);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        } else {
            return defaultValue;
        }
    }
}
