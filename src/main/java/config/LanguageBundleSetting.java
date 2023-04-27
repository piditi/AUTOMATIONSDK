package config;


import util.Constant;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageBundleSetting {
    public static final Locale LOCALE = getLocale(Constant.LANGUAGE);
    public static final LanguageBundleSetting LANGUAGE_BUNDLE = getInstance();

    private final ResourceBundle resourceConfig;

    public static Locale getLocale(String language) {
        Locale locale;
        if ("vi".equals(language)) {
            locale = new Locale("vi", "VN");
        } else if ("ja".equals(language)) {
            locale = Locale.JAPANESE;
        } else if ("zh".equals(language)) {
            locale = Locale.CHINESE;
        } else {
            locale = Locale.ENGLISH;
        }
        return locale;
    }

    private LanguageBundleSetting() {
        resourceConfig = ResourceBundle.getBundle("language", LOCALE);
    }

    public static LanguageBundleSetting getInstance() {
        return new LanguageBundleSetting();
    }

    public String getValue(String key) {
        String rawString = resourceConfig.getString(key);
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);

        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);

        return utf8EncodedString;
    }
}

