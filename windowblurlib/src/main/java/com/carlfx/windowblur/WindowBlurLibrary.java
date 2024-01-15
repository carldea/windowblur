package com.carlfx.windowblur;

import com.sun.jna.*;
import com.sun.jna.platform.mac.CoreFoundation;

import java.nio.charset.Charset;
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

    void testFunctionC(NativeLong address, NativeLong address2);
    void testFunctionC2(NativeLong address);
    NativeLong stringCls = FoundationLibrary.INSTANCE.objc_getClass("NSString");
    Pointer stringSel = FoundationLibrary.INSTANCE.sel_registerName("string");
    Pointer allocSel = FoundationLibrary.INSTANCE.sel_registerName("alloc");
    Pointer initWithBytesLengthEncodingSel = FoundationLibrary.INSTANCE.sel_registerName("initWithBytes:length:encoding:");
    long NSUTF16LittleEndianStringEncoding = 0x94000100;

    static String toNativeString(NativeLong nativeLong) {
        if (NULL.equals(nativeLong)) {
            return null;
        }
        CoreFoundation.CFStringRef cfString = new CoreFoundation.CFStringRef(new Pointer(nativeLong.longValue()));
        try {
            return CoreFoundation.INSTANCE.CFStringGetLength(cfString).intValue() > 0 ? cfString.stringValue() : "";
        } finally {
            cfString.release();
        }
    }
    static NativeLong fromJavaString(String s) {
        if (s.isEmpty()) {
            return FoundationLibrary.INSTANCE.objc_msgSend(stringCls, stringSel);
        }

        byte[] utf16Bytes = s.getBytes(Charset.forName("UTF-16LE"));
        return FoundationLibrary.INSTANCE.objc_msgSend(FoundationLibrary.INSTANCE.objc_msgSend(stringCls, allocSel),
                initWithBytesLengthEncodingSel, utf16Bytes, utf16Bytes.length, NSUTF16LittleEndianStringEncoding);
    }
}