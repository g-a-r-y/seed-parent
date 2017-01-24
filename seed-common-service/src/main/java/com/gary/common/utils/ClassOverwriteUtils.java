package com.gary.common.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ClassOverwriteUtils {
    private static final Method defineClassMethod;

    static {
        try {
            defineClassMethod = ClassLoader.class.getDeclaredMethod(
                    "defineClass", String.class, byte[].class, int.class, int.class
            );
            if (!defineClassMethod.isAccessible()) {
                defineClassMethod.setAccessible(true);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static String classNameToPath(String className) {
        return CharMatcher.is('.').replaceFrom(className, "/") + ".class";
    }

    private static void doOverwriteClass(String className, URL configUrl, URLClassLoader classLoader) {
        try {
            URL classUrl = new URL(configUrl, classNameToPath(className));
            try (InputStream classStream = classUrl.openStream()) {
                byte[] byteCode = ByteStreams.toByteArray(classStream);
                defineClassMethod.invoke(classLoader, className, byteCode, 0, byteCode.length);
            }
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void overwriteClasses() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        if (classLoader instanceof URLClassLoader) {
            URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
            try {
                List<URL> configUrls =
                        Collections.list(urlClassLoader.findResources("overwrite-classes.txt"));
                for (URL configUrl : configUrls) {
                    String config = Resources.toString(configUrl, Charsets.UTF_8);
                    try (Scanner scanner = new Scanner(config)) {
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            doOverwriteClass(line, configUrl, urlClassLoader);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("only URLClassLoader is supported");
        }
    }
}
