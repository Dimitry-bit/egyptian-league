package com.github.egyptian_league.json.src;

public class JsonSerializerOptions {
    public int MaxDepth;
    public int tabWidth;
    public boolean WriteIndented;

    public JsonSerializerOptions() {
        MaxDepth = 65;
        tabWidth = 2;
        WriteIndented = false;
    }
}