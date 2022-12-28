package fr.hyriode.cosmetics.utils;

public class StringUtil {

    public static String cutString(String string, int maxLength) {
        StringBuilder sb = new StringBuilder(string);

        int i = 0;
        while (i + maxLength < sb.length() && (i = sb.lastIndexOf(" ", i + maxLength)) != -1) {
            sb.replace(i, i + 1, "\n");
        }

        return sb.toString();
    }

}