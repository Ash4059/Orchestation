package com.example.orchestation.Validation;

import java.util.Arrays;
import java.util.Optional;

public class EnumUtils {

     /**
     * Looks up an enum constant by its name.
     *
     * @param enumClass   The enum type to search in (e.g., Role.class).
     * @param value       The string value to match against enum names.
     * @param ignoreCase  Whether to ignore case when matching.
     * @param <T>         Generic type parameter for the enum.
     * @return            Optional containing the matched enum constant, or empty if not found.
     */

    public static <T extends Enum<T>> Optional<T> lookup(Class<T> enumClass, String value, boolean ignoreCase) {
        if (value == null) return Optional.empty(); // No value provided
        return Arrays.stream(enumClass.getEnumConstants()) // Stream through enum constants
                .filter(e -> ignoreCase ? e.name().equalsIgnoreCase(value) : 
                e.name().equals(value)) // Filter based on case sensitivity
                .findFirst(); // Return the first match wrapped in Optional
    }
}
