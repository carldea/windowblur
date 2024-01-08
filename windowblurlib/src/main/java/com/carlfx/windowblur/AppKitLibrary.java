package com.carlfx.windowblur;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface AppKitLibrary extends Library {
    NativeLong NULL = new NativeLong(0l);
    AppKitLibrary INSTANCE = Native.load(
            "AppKit",
            AppKitLibrary.class,
            Map.of(Library.OPTION_STRING_ENCODING, StandardCharsets.UTF_8.name()));

}
