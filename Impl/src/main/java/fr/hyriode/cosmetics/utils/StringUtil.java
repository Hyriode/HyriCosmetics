package fr.hyriode.cosmetics.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static List<String> splitIntoPhrases(final String input, final int maxLineLength) {
        final List<String> phrases = new ArrayList<>();
        StringBuilder currentPhrase = new StringBuilder();
        final String[] words = input.split(" ");

        for (final String word : words) {
            if (currentPhrase.length() + word.length() + 1 > maxLineLength) {
                phrases.add("§7" + currentPhrase);
                currentPhrase = new StringBuilder();
            }
            currentPhrase.append(word).append(" ");
        }
        phrases.add("§7" + currentPhrase.toString());
        return phrases;
    }

}