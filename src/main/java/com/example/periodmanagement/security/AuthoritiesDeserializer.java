package com.example.periodmanagement.security;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthoritiesDeserializer extends JsonDeserializer<List<SimpleGrantedAuthority>> {
    @Override
    public List<SimpleGrantedAuthority> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (node.isArray()) {
            for (JsonNode element : node) {
                authorities.add(new SimpleGrantedAuthority(element.get("authority").asText()));
            }
        }
        return authorities;
    }
}
