package com.github.egyptian_league;

import java.net.URL;

public class TestUtil {
    private TestUtil() {
    }

    public static URL getResourceUrl(String relativePath) {
        return Thread.currentThread().getContextClassLoader().getResource(relativePath);
    }
}
