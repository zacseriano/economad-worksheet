package zacseriano.economadworksheets.shared.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    public static String normalize(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();
    }

	public static String[] formatString(String s) {
		String result = s.replaceAll("\\s+", "");
		String[] formattedData = result.split(Pattern.quote(","));
		return formattedData;
	}
}
