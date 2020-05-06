import java.text.Normalizer;

public class Utils {


    public static String getTimestamp(String input) {

        String[] splittedDateAndTime = input.split("T");
        String date = splittedDateAndTime[0];
        String time = splittedDateAndTime[1].split("\\+")[0];
        String timestamp = date + " " + time;

        return timestamp;
    }

    public static String getNormalizedString(String input) {

        // Normalize strings with NFD
        String intermediate = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        // Ersetze Quotes
        String result = removeQuotes(intermediate);
        return result;
    }

    private static String removeQuotes(String input) {

        // Ersetze Quotes durch ähnliches Zeichen
        String result = input.replaceAll("'", "`");

        return result;
    }
}
