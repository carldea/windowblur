package com.carlfx.windowblur;

import com.sun.jna.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface WindowBlurLibrary extends Library {
    NativeLong NULL = new NativeLong(0l);
    WindowBlurLibrary INSTANCE = Native.load(
            "WindowBlur",
            WindowBlurLibrary.class,
            Map.of(Library.OPTION_STRING_ENCODING, StandardCharsets.UTF_8.name()));

    // https://developer.apple.com/documentation/objectivec/1418952-objc_getclass?language=objc
    Pointer objc_getClass(String className);

    // https://developer.apple.com/documentation/objectivec/1418760-objc_lookupclass?language=objc
    Pointer objc_lookUpClass(String className);

    // https://developer.apple.com/documentation/objectivec/1418571-sel_getname?language=objc

    Pointer class_getName(Pointer cls);
    Pointer object_getClassName(Pointer classPtr);

    // https://developer.apple.com/documentation/objectivec/1418557-sel_registername?language=objc
    Pointer sel_registerName(String selectorName);

    // https://developer.apple.com/documentation/objectivec/1456712-objc_msgsend?language=objc
    // The return type is actually "generic". You might need to declare this function
    // multiple times with different return types if you need them.
    Pointer objc_msgSend(Pointer receiver, Pointer selector, Object... args);

    //NativeLong objc_msgSend(NativeLong receiver, Pointer selector, NativeLong ...objAddress);

    void testFunctionC(NativeLong address);
}