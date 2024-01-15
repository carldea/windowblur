package com.carlfx.windowblur;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public class TestUtil {
    public static String test(long windowPtr, String nativeAppearanceName) {
        System.out.println("Java side: windowPtr = " + windowPtr);
        NativeLong nsWindow = new NativeLong(windowPtr);
        WindowBlurLibrary.INSTANCE.testFunctionC(nsWindow, WindowBlurLibrary.fromJavaString(nativeAppearanceName));
//        WindowBlurLibrary.INSTANCE.testFunctionC2(nsWindow);
        return "Invoked effect";
    }
}
