package com.github.egyptian_league.Json;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.egyptian_league.TestUtil;

public class JsonSerializerTest {

    @Test
    public void jsonDeserializationTest() {
        try {
            Path filePath = Path.of(TestUtil.getResourceUrl("json/dto.json").toURI());
            String jsonSource = String.join("", Files.readAllLines(filePath));
            Assertions.assertDoesNotThrow(() -> JsonSerializer.deserialize(jsonSource, DTO.class));
            System.out.println(JsonSerializer.deserialize(jsonSource, DTO.class).toString());
        } catch (IOException | URISyntaxException e) {
            Assertions.fail("'%s' failed to read".formatted("./dto.json"));
        }
    }

    @Test
    void JsonSerializationTest() {
        try {
            Path filePath = Path.of(TestUtil.getResourceUrl("json/dto.json").toURI());
            String jsonSource = String.join("", Files.readAllLines(filePath));
            JsonSerializerOptions options = new JsonSerializerOptions();

            Assertions.assertDoesNotThrow(() -> JsonSerializer.deserialize(jsonSource, DTO.class));
            DTO dto = JsonSerializer.deserialize(jsonSource, DTO.class);

            Assertions.assertDoesNotThrow(() -> JsonSerializer.serialize(dto, JsonSerializerOptions.DefaultOptions));

            options.WriteIndented = false;
            System.out.println("WriteIndented = false:");
            System.out.println(JsonSerializer.serialize(dto, options));

            System.out.println();

            options.WriteIndented = true;
            System.out.println("WriteIndented = true:");
            System.out.println(JsonSerializer.serialize(dto, options));
        } catch (IOException | URISyntaxException e) {
            Assertions.fail("'%s' failed to read".formatted("./dto.json"));
        }
    }
}
