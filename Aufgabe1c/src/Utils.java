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

        //normalize strings with NFD
        String result = Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        return result;
    }
}
