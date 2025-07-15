package com.ingress_track.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingress_track.filter.RequestCachingFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ApiUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Object ResponseHandler(HttpServletRequest request, HttpStatus status, String message, Object data) {

        String rawBody = JsonUtil.compactJson(getRawBody(request));

        Map<String, Object> res = new HashMap<>();
        res.put("status", status.value());
        res.put("message", message);

        String key = (data instanceof Map<?, ?> map && map.keySet().stream().allMatch(k -> k instanceof String && map.get(k) instanceof String))
                ? "errors"
                : "data";

        TransactionLogs.log("[request URI: " + request.getRequestURI() +
                "][payload:"+rawBody+"]"+
                "][method:"+request.getMethod()+"]"+
                "[response:"+res.toString()+ "]");

        res.put(key, data);
        return res;
    }

    public static String getRawBody(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
        byte[] buf = wrapper.getContentAsByteArray();

        if (buf.length > 0) {
            try {
                return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                return "[Unsupported Encoding]";
            }
        }
        return "";
    }

}
