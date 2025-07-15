package com.ingress_track.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {


        private static final ObjectMapper objectMapper = new ObjectMapper();

        public static String compactJson(String json) {
            try {
                JsonNode tree = objectMapper.readTree(json);
                return objectMapper.writeValueAsString(tree); // compact output
            } catch (Exception e) {
                return json; // fallback if not JSON
            }
        }


}
