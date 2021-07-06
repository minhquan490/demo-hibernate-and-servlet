package utils;

import java.util.UUID;

public class RandomUUID {
    
    public static String getRandomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}