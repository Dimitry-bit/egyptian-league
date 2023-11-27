/*
 *
 * Copyright (c) 2023 Tony Medhat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.egyptian_league.json;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class JsonLexer {

    private ArrayList<JsonToken> tokens;
    private int index;
    private int lineIndex;

    public JsonLexer() {
        tokens = new ArrayList<>();
        lineIndex = 1;
        index = 0;
    }

    public JsonLexer(String source) {
        this();
        lex(source);
    }

    public boolean hasToken() {
        return (index < tokens.size());
    }

    public boolean hasValue() {
        // NOTE(Tony): Find a better solution, state machine ?
        return (index < tokens.size() - 2);
    }

    public ArrayList<JsonToken> getTokens() {
        return tokens;
    }

    public ArrayList<JsonToken> lex(String source) {
        tokens = new ArrayList<>();
        lineIndex = 1;
        index = 0;

        try (StringReader sr = new StringReader(source)) {
            while (true) {
                JsonToken token = readToken(sr);

                if (token == null) {
                    break;
                }
                tokens.add(token);
            }

            sr.close();
        } catch (IOException io) {
            System.err.println("lexer: " + io.getMessage());
            io.printStackTrace();
        }

        return tokens;
    }

    public JsonToken nextToken() {
        if (!hasToken()) {
            throw new NoSuchElementException("Token list is empty");
        }

        return tokens.get(index++);
    }

    public JsonToken nextValue() {
        StringBuilder sb = new StringBuilder();
        JsonTokenType type = JsonTokenType.NONE;
        JsonToken token = null;
        char c = '\0';

        skipTo(JsonToken.COLON);

        token = nextToken();
        c = token.getFirstChar();

        if (c == JsonToken.COMMA || c == JsonToken.COLON) {
            throw new JsonException("Expected a value, got: '%s'".formatted(token.value));
        }

        // Construct value
        {
            char endSeparator = '\0';

            if (c == JsonToken.LEFT_CURLY_BRACKET) {
                endSeparator = JsonToken.RIGHT_CURLY_BRACKET;
                type = JsonTokenType.OBJECT;
            } else if (c == JsonToken.LEFT_SQUARE_BRACKET) {
                endSeparator = JsonToken.RIGHT_SQUARE_BRACKET;
                type = JsonTokenType.ARRAY;
            } else {
                return token;
            }

            do {
                sb.append(token.value);
            } while (hasToken() && ((token = nextToken()).getFirstChar() != endSeparator));
            sb.append(endSeparator);
        }

        if ("".contentEquals(sb)) {
            throw new JsonException("Expected a value");
        }

        return new JsonToken(sb.toString(), type);
    }

    public JsonToken previousToken() {
        if (index - 2 < 0) {
            throw new NoSuchElementException("Token list contains one or none tokens");
        }

        return tokens.get(index - 2);
    }

    public void ungetToken() {
        if (index <= 0) {
            throw new NoSuchElementException("Token index is zero");
        }

        index--;
    }

    public JsonToken peek() {
        if (!hasToken()) {
            throw new NoSuchElementException("Token list is empty");
        }

        return tokens.get(index);
    }

    private JsonToken readToken(StringReader sr) throws IOException, JsonException {
        JsonToken token = null;
        int c = '\0';

        skipWhiteSpace(sr);
        c = sr.read();

        // Handle EOF
        if (c == -1) {
            JsonToken lastToken = tokens.get(tokens.size() - 1);
            if (!tokens.isEmpty() && (lastToken.getFirstChar() == JsonToken.RIGHT_CURLY_BRACKET)) {
                return null;
            } else {
                throw new JsonException("Expected end-of-object bracket :line " + lineIndex);
            }
        }

        // Extract Token
        {
            boolean isJsonSeparator = (c == JsonToken.LEFT_CURLY_BRACKET)
                    || (c == JsonToken.RIGHT_CURLY_BRACKET)
                    || (c == JsonToken.LEFT_SQUARE_BRACKET)
                    || (c == JsonToken.RIGHT_SQUARE_BRACKET)
                    || (c == JsonToken.COLON) || (c == JsonToken.COMMA);

            if (isJsonSeparator) {
                token = new JsonToken(Character.toString(c), JsonTokenType.SEPARATOR);
            } else {
                boolean isJsonLiteral = false;

                sr.reset();

                isJsonLiteral = ((token = lexString(sr, lineIndex)) != null)
                        || ((token = lexBool(sr)) != null)
                        || ((token = lexNull(sr)) != null)
                        || ((token = lexNumber(sr, lineIndex)) != null);

                if (!isJsonLiteral) {
                    throw new JsonException("Unexpected token '%c':line %d".formatted(c, lineIndex));
                }
            }

            sr.mark(0);
        }

        return token;
    }

    private void skipWhiteSpace(StringReader sr) throws IOException {
        int c = '\0';

        do {
            c = sr.read();

            if (!Character.isWhitespace(c)) {
                sr.reset();
                break;
            }
            sr.mark(0);

            if (c == '\n') {
                lineIndex++;
            }
        } while (c != -1);

    }

    private void skipTo(char c) {
        skipTo(Character.toString(c));
    }

    private void skipTo(String s) {
        while (hasToken() && !(nextToken().value.equals(s)))
            ;
    }

    private JsonToken lexString(StringReader sr, int lineIndex) throws IOException, JsonException {
        CharArrayWriter cw = new CharArrayWriter();
        int prevChar = '\0';
        int c = '\0';

        sr.mark(0);
        if ((c = sr.read()) != JsonToken.QUOTE) {
            sr.reset();
            return null;
        }
        cw.append((char) c);

        while ((c = sr.read()) != -1) {
            cw.append((char) c);

            if (c == JsonToken.QUOTE && prevChar != '\\') {
                break;
            }

            prevChar = c;
        }

        if (c == '"') {
            return new JsonToken(cw.toString(), JsonTokenType.STRING);
        }

        throw new JsonException("Expected end-of-string quote, got: '%s':line %d".formatted(cw.toString(), lineIndex));
    }

    private JsonToken lexBool(StringReader sr) throws IOException {
        int c = 0;
        CharArrayWriter cw = new CharArrayWriter(5);

        sr.mark(0);
        while ((c = sr.read()) != -1) {
            cw.append((char) c);

            if (cw.size() == JsonToken.LITERAL_TRUE.length()) {
                break;
            }
        }

        if (JsonToken.LITERAL_TRUE.contentEquals(cw.toString())) {
            return new JsonToken(JsonToken.LITERAL_TRUE, JsonTokenType.BOOLEAN);
        }

        if ((c != -1) && ((c = sr.read()) != -1)) {
            cw.append((char) c);

            if (JsonToken.LITERAL_FALSE.contentEquals(cw.toString())) {
                return new JsonToken(JsonToken.LITERAL_FALSE, JsonTokenType.BOOLEAN);
            }
        }

        sr.reset();
        return null;
    }

    private JsonToken lexNull(StringReader sr) throws IOException {
        char[] charBuffer = new char[4];

        sr.mark(0);
        if (sr.read(charBuffer, 0, charBuffer.length) == -1) {
            sr.reset();
            return null;
        }

        if (JsonToken.LITERAL_NULL.equals(String.valueOf(charBuffer))) {
            return new JsonToken(JsonToken.LITERAL_NULL, JsonTokenType.NULL);
        }

        sr.reset();
        return null;
    }

    private JsonToken lexNumber(StringReader sr, int lineIndex) throws IOException, JsonException {
        CharArrayWriter cw = new CharArrayWriter();
        boolean isError = false;

        {
            boolean hasSign = false;
            boolean isFraction = false;
            boolean isExponent = false;
            int prevChar = '\0';
            int c = '\0';

            sr.mark(0);
            while ((c = sr.read()) != -1) {
                if (cw.size() == 0 && (c == '-')) {
                    cw.append((char) c);
                    hasSign = true;
                    continue;
                }

                if (Character.isDigit(c)) {
                    cw.append((char) c);
                    sr.mark(0);
                    prevChar = c;
                    continue;
                }

                if (c == '.') {
                    isError = (isFraction || isExponent);
                    isFraction = true;
                } else if (Character.toLowerCase(c) == 'e') {
                    isError = (isExponent || (prevChar == '.'));
                    isExponent = true;
                } else if ((c == '-') || (c == '+')) {
                    isError = (Character.toLowerCase(prevChar) != 'e');
                } else {
                    break;
                }

                cw.append((char) c);
                prevChar = c;

                if (isError) {
                    break;
                }
            }

            if (Character.isDigit(c)) {
                cw.append((char) c);
            } else {
                switch (Character.toLowerCase(c)) {
                    case '.':
                    case 'e':
                    case '+':
                    case '-':
                        isError = true;
                        break;
                    default:
                        sr.reset();
                        break;
                }
            }

            if (hasSign && (cw.size() < 2)) {
                isError = true;
            }
        }

        if (isError) {
            throw new JsonException("Expected a number, got '%s':line %d".formatted(cw.toString(), lineIndex));
        }

        if (cw.size() >= 1) {
            return new JsonToken(cw.toString(), JsonTokenType.NUMBER);
        }

        sr.reset();
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder json = new StringBuilder();
        JsonLexer lexer = new JsonLexer();

        while (in.hasNextLine()) {
            json.append(in.nextLine());
            json.append(System.lineSeparator());
        }
        in.close();

        lexer.lex(json.toString());

        System.out.println("\nLexer Operations:");

        System.out.printf("Next: '%s'%n", lexer.nextToken().value);
        System.out.printf("Peek: '%s'%n", lexer.peek().value);
        System.out.printf("Call: 'ungetToken()'%n");
        lexer.ungetToken();
        System.out.printf("Next: '%s'%n", lexer.nextToken().value);
        System.out.printf("Next: '%s'%n", lexer.nextToken().value);
        System.out.printf("Prev: '%s'%n", lexer.previousToken().value);

        System.out.println("\nLexer Values:");

        while (lexer.hasValue()) {
            JsonToken t = lexer.nextValue();
            System.out.printf("%-9s : '%s'%n", t.type, t.value);
        }

        System.out.println("\nLexer Tokens:");

        ArrayList<JsonToken> tokens = lexer.getTokens();
        for (JsonToken t : tokens) {
            System.out.printf("%-9s : '%s'%n", t.type, t.value);
        }
    }
}