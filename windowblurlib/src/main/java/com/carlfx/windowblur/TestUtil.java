package com.carlfx.windowblur;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public class TestUtil {
    public static String test(long windowPtr) {
        System.out.println("Java side: windowPtr = " + windowPtr);
        Pointer classPtr = WindowBlurLibrary.INSTANCE.objc_getClass("WindowBlur");
//        Pointer testFunction1Sel = WindowBlurLibrary.INSTANCE.sel_registerName("testFunction1:");
//        NativeLong nsString = FoundationLibrary.fromJavaString("Test cast NSString");
//        System.out.println("NativeLong nsString = " + nsString.longValue());
//        WindowBlurLibrary.INSTANCE.testFunctionC(nsString);

        NativeLong nsWindow = new NativeLong(windowPtr);
//        WindowBlurLibrary.INSTANCE.testFunctionC(nsWindow);
        WindowBlurLibrary.INSTANCE.testFunctionC2(nsWindow);

//        Pointer testFunction1Sel = WindowBlurLibrary.INSTANCE.sel_registerName("testFunction1:");
//        Pointer windowPointer = new Pointer(windowPtr);

//        long test = 1234033333333333l;
//        WindowBlurLibrary.INSTANCE.objc_msgSend(classPtr, testFunction1Sel, test);
//        WindowBlurLibrary.INSTANCE.objc_msgSend(classPtr, testFunction1Sel, windowPointer);
        return "Invoked effect";
    }
}
