package fr.eni.encheres.api.config;

import java.util.HashSet;
import java.util.Set;

public class InMemoryTokenBlacklist {
    private final Set<String> blacklist = new HashSet<>();

    public void addToBlacklist(String token) {
        blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
