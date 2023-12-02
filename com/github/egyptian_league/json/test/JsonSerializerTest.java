package com.github.egyptian_league.json.test;

import java.util.Scanner;

import com.github.egyptian_league.json.src.JsonSerializer;
import com.github.egyptian_league.json.src.JsonSerializerOptions;

public class JsonSerializerTest {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            StringBuilder json = new StringBuilder();
            JsonSerializerOptions options = new JsonSerializerOptions();

            while (in.hasNextLine()) {
                json.append(in.nextLine());
                json.append(System.lineSeparator());
            }
            in.close();

            DTO dto = JsonSerializer.deserialize(json.toString(), DTO.class);

            System.out.println("DTO Instance:");
            System.out.println(dto.toString());

            options.WriteIndented = false;
            System.out.println("WriteIndented = false:");
            System.out.println(JsonSerializer.serialize(dto, options));

            System.out.println();

            options.WriteIndented = true;
            System.out.println("WriteIndented = true:");
            System.out.println(JsonSerializer.serialize(dto, options));

            options.WriteIndented = true;
            System.out.println("WriteIndented = true:");
            System.out.println(JsonSerializer.serialize(dto.child, options));
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
