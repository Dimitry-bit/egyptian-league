package com.github.egyptian_league.json;

import com.github.egyptian_league.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class JsonLexerTest {
    @Test
    public void JsonLexerOperationsTest() {
        try {
            Path filePath = Path.of(TestUtil.getResourceUrl("json/dto.json").toURI());
            String jsonSource = String.join("", Files.readAllLines(filePath));

            JsonLexer lexer = new JsonLexer();
            Assertions.assertDoesNotThrow(() -> lexer.lex(jsonSource));

            System.out.printf("Next: '%s'%n", lexer.nextToken().value);
            System.out.printf("Peek: '%s'%n", lexer.peek().value);
            System.out.printf("Call: 'ungetToken()'%n");
            lexer.ungetToken();
            System.out.printf("Next: '%s'%n", lexer.nextToken().value);
            System.out.printf("Next: '%s'%n", lexer.nextToken().value);
            System.out.printf("Prev: '%s'%n", lexer.previousToken().value);

            System.out.println("\nLexer Operations:");

            System.out.printf("Next: '%s'%n", lexer.nextToken().value);
            System.out.printf("Peek: '%s'%n", lexer.peek().value);
            System.out.printf("Call: 'ungetToken()'%n");
            lexer.ungetToken();
            System.out.printf("Next: '%s'%n", lexer.nextToken().value);
            System.out.printf("Next: '%s'%n", lexer.nextToken().value);
            System.out.printf("Prev: '%s'%n", lexer.previousToken().value);

            System.out.println("\nLexer Tokens:");

            ArrayList<JsonToken> tokens = lexer.getTokens();
            for (JsonToken t : tokens) {
                System.out.printf("%-12s : '%s'%n", t.type, t.value);
            }
        } catch (IOException | URISyntaxException e) {
            Assertions.fail("'%s' failed to read".formatted("./dto.json"));
        }
    }
}
