package com.appleframework.qos.core.config;

import org.apache.log4j.Logger;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.regex.Pattern;

/**
 * 获取系统配置
 * <p>
 * Created by haiyang on 15/7/29.
 */
public class PropertyConfigurer {

    private static boolean initializedLogger;
    private static final Logger logger;
    private static boolean loggedException;

    static {
        logger = Logger.getLogger(PropertyConfigurer.class);
        initializedLogger = true;
    }

    public static boolean contains(String key) {
        return getString(key) != null;
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(final String key, String def) {
        if (key == null) {
            throw new NullPointerException("key");
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException("key must not be empty.");
        }

        String value = null;
        try {
            if (System.getSecurityManager() == null) {
                value = System.getProperty(key);
            } else {
                value = AccessController.doPrivileged(new PrivilegedAction<String>() {
                    public String run() {
                        return System.getProperty(key);
                    }
                });
            }
        } catch (Exception e) {
            if (!loggedException) {
                logger.warn("Unable to retrieve a system property '" + key + "'; default values will be used.", e);
                loggedException = true;
            }
        }

        if (value == null) {
            return def;
        }

        return value;
    }

    public static boolean getBoolean(String key, boolean def) {
        String value = getString(key);
        if (value == null) {
            return def;
        }

        value = value.trim().toLowerCase();
        if (value.isEmpty()) {
            return true;
        }

        if ("true".equals(value) || "yes".equals(value) || "1".equals(value)) {
            return true;
        }

        if ("false".equals(value) || "no".equals(value) || "0".equals(value)) {
            return false;
        }

        logger.warn(
                "Unable to parse the boolean system property '" + key + "':" + value + " - " +
                        "using the default value: " + def);

        return def;
    }

    private static final Pattern INTEGER_PATTERN = Pattern.compile("-?[0-9]+");

    public static int getInteger(String key, int def) {
        String value = getString(key);
        if (value == null) {
            return def;
        }

        value = value.trim().toLowerCase();
        if (INTEGER_PATTERN.matcher(value).matches()) {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                // Ignore
            }
        }

        logger.warn(
                "Unable to parse the integer system property '" + key + "':" + value + " - " +
                        "using the default value: " + def);

        return def;
    }

    public static long getLong(String key, long def) {
        String value = getString(key);
        if (value == null) {
            return def;
        }

        value = value.trim().toLowerCase();
        if (INTEGER_PATTERN.matcher(value).matches()) {
            try {
                return Long.parseLong(value);
            } catch (Exception e) {
                // Ignore
            }
        }

        logger.warn(
                "Unable to parse the long integer system property '" + key + "':" + value + " - " +
                        "using the default value: " + def);

        return def;
    }

    private PropertyConfigurer() {
    }

}
