package twyla.config;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class Text {

    private static final String MESSAGE_SOURCE = "datasource";

    private static ResourceBundleMessageSource resourceBundleMessageSource;

    private static String get(String key, Object[] parameters, Locale locale) {

        if (resourceBundleMessageSource == null) {
            resourceBundleMessageSource = new ResourceBundleMessageSource();
            resourceBundleMessageSource.setBasenames(MESSAGE_SOURCE);
        }

        return resourceBundleMessageSource.getMessage(key, parameters, locale);
    }

    public static String get(String key) {

        return get(key, new Object[] {}, null);
    }

    public static String get(String key, String parameter1) {

        return get(key, new Object[] { parameter1 }, null);
    }

    public static String get(String key, String parameter1, String parameter2) {

        return get(key, new Object[] { parameter1, parameter2 }, null);
    }

    public static String get(String key, String parameter1, String parameter2, String parameter3) {

        return get(key, new Object[] { parameter1, parameter2, parameter3 }, null);
    }

}
