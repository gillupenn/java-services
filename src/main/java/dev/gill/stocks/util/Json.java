package dev.gill.stocks.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/** Json Utilities */
public final class Json {
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SS";
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);
    public static final String EMPTY_OBJ = "{}";
    private static final ObjectMapper objectMapper = new ObjectMapper();


    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_FORMATTER));
        objectMapper.registerModule(javaTimeModule);
    }


    private static final ObjectWriter writer = objectMapper.writer();
    private static final ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();

    /** Serialize a given object to JSON string */
    public static String toJsonString(Object object) {

        if (Objects.isNull(object)) {
            return EMPTY_OBJ;
        }

        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonStringPretty(Object object) {

        if (Objects.isNull(object)) {
            return EMPTY_OBJ;
        }

        try {
            return prettyWriter.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJsonString(String jsonString, Class<T> type) {
        if (Objects.isNull(jsonString) || jsonString.isEmpty()) {
            throw new IllegalArgumentException("Empty JSON content not allowed");
        }
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static <T> T fromJsonArrayString(String jsonString, TypeReference<T> type) {
        if (Objects.isNull(jsonString) || jsonString.isEmpty()) {
            throw new IllegalArgumentException("Empty JSON content not allowed");
        }
        try {
            return objectMapper.readValue(jsonString, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

