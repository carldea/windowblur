//
//  WindowBlur.m
//  WindowBlur
//
//  Created by Dea, Carl on 1/6/24.
//

#import "WindowBlur.h"
#import "Foundation/Foundation.h"
#import "AppKit/AppKit.h"

void testFunctionC(void* windowptr) {
    NSLog(@"testFunctionC was called");
    long myLongValue = (long)windowptr;
    NSLog(@"testFunctionC myLongValue = %ld", myLongValue);
//    
//    NSString* testString = (__bridge NSString*)(windowptr);
//    NSLog(@"testFunctionC testString = %@", testString);
//    
    NSWindow* win = (__bridge NSWindow*)(windowptr);
    NSLog(@"NSWindow = %@", win);
    
    
    dispatch_async(dispatch_get_main_queue(), ^{
        NSView* hostView = win.contentView;

        if (hostView != nil && hostView.subviews.count) {
            NSLog(@"  Before NSVisualEffectView creation subviews.count = %lu", hostView.subviews.count);

            NSView* jfxView = hostView.subviews[0];
            NSVisualEffectView *vfxView = [[NSVisualEffectView alloc] initWithFrame:[win.contentView bounds]];
            [vfxView setAppearance:[NSAppearance appearanceNamed:NSAppearanceNameVibrantDark]];
            [vfxView setBlendingMode:NSVisualEffectBlendingModeBehindWindow];
            [vfxView setMaterial:NSVisualEffectMaterialUnderWindowBackground];

            // make sure javafx layer is not opaque
            [vfxView setAutoresizingMask: (NSViewWidthSizable|NSViewHeightSizable)];
            [hostView addSubview: vfxView positioned: NSWindowBelow relativeTo: jfxView];
        }
    });
    
    
}

@implementation WindowBlur
      + (void) testFunction1:(id)windowptr {
//          NSLog(@"testFunction1:windowptr was called windowptr=%@", windowptr);

//    + (void) testFunction1:(void*)windowptr {
        
        
        @autoreleasepool {
            // Cast the void pointer to a long
            long myLongValue = (long)windowptr;
            
            // Now you can use myLongValue as a long integer
            NSLog(@"myLongValue value: %ld", myLongValue);
            
            long myLongValue2 = 4321l;
            NSLog(@"myLongValue2 value: %ld", myLongValue2);
            
            //       NSLog(@"1. testFunction1 called %@", windowptr);
            //    NSString* testString = (__bridge NSString*)(windowptr);
            //        NSString *testString = windowptr;
            //       NSLog(@"2. testFunction1 called %@", testString);
            // dispatch_async(dispatch_get_main_queue(), ^{
            
            //     NSWindow* win = (__bridge NSWindow*)(windowptr);
            // });
            
            
            //       NSWindow *win = *((__unsafe_unretained NSWindow **)(windowptr));
            
            //        NSWindow* win = (__bridge NSWindow*)(windowptr);
            //        NSLog(@"2. called with windowptr = %@", win);
            
            //         dispatch_async(dispatch_get_main_queue(), ^{
            //             NSWindow *window = windowptr;
            //             NSView* hostView = window.contentView;
            //
            //             if (hostView != nil && hostView.subviews.count) {
            //                 NSLog(@"  Before NSVisualEffectView creation subviews.count = %lu", hostView.subviews.count);
            //
            //                 NSView* jfxView = hostView.subviews[0];
            //                 NSVisualEffectView *vfxView = [[NSVisualEffectView alloc] initWithFrame:[window.contentView bounds]];
            //                 [vfxView setAppearance:[NSAppearance appearanceNamed:NSAppearanceNameVibrantDark]];
            //                 [vfxView setBlendingMode:NSVisualEffectBlendingModeBehindWindow];
            //                 [vfxView setMaterial:NSVisualEffectMaterialUnderWindowBackground];
            //
            //                 // make sure javafx layer is not opaque
            //                 [vfxView setAutoresizingMask: (NSViewWidthSizable|NSViewHeightSizable)];
            //                 [hostView addSubview: vfxView positioned: NSWindowBelow relativeTo: jfxView];
            //             }
            //         });
        }
    }
@end
