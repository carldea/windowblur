//
//  WindowBlur.m
//  WindowBlur
//
//  Created by Dea, Carl on 1/6/24.
//

#import "WindowBlur.h"
#import "Foundation/Foundation.h"
#import "AppKit/AppKit.h"

void testFunctionC(void* windowptr, NSString *nsAppearanceName) {
    NSLog(@"testFunctionC was called");
    long myLongValue = (long)windowptr;
    NSLog(@"testFunctionC myLongValue = %ld", myLongValue);

    // converting C string into a NSString
    //NSString *myNSString = [NSString stringWithUTF8String:nsAppearanceName];
    NSLog(@"testFunctionC nsAppearanceName = %@", nsAppearanceName);

    // Casting pointer into a NSWindow pointer
    NSWindow* win = (__bridge NSWindow*)(windowptr);
    NSLog(@"NSWindow = %@", win);
    
    
    dispatch_async(dispatch_get_main_queue(), ^{
        NSView* hostView = win.contentView;

        if (hostView != nil && hostView.subviews.count) {
            NSLog(@"  Before NSVisualEffectView creation subviews.count = %lu", hostView.subviews.count);

            NSMutableArray *mutableSubviews = [hostView.subviews mutableCopy];
            for (NSView *subview in [mutableSubviews copy]) {
                // Perform some condition to determine if the subview should be removed
                if ([subview isKindOfClass:[NSVisualEffectView class]]) {
                    [subview removeFromSuperview];
                    [mutableSubviews removeObject:subview];
                }
            }

            // Now, mutableSubviews contains the modified array with items removed
            [hostView setSubviews:mutableSubviews];
            
            // iterate over sub views
            NSArray *subviews = [hostView subviews];
            NSUInteger count = [subviews count];

            for (NSUInteger i = 0; i < count; i++) {
                NSView *subview = [subviews objectAtIndex:i];
                
                // Perform operations on each subview
                NSLog(@"->>>>> %@", subview);
            }
            
            NSView* jfxView = hostView.subviews[0];
            // if one already exists then remove it.
            
            NSVisualEffectView *vfxView = [[NSVisualEffectView alloc] initWithFrame:[win.contentView bounds]];
            [vfxView setAppearance:[NSAppearance appearanceNamed:nsAppearanceName]];
            [vfxView setBlendingMode:NSVisualEffectBlendingModeBehindWindow];
            [vfxView setMaterial:NSVisualEffectMaterialUnderWindowBackground];

            // make sure javafx layer is not opaque
            [vfxView setAutoresizingMask: (NSViewWidthSizable|NSViewHeightSizable)];
            [hostView addSubview: vfxView positioned: NSWindowBelow relativeTo: jfxView];
        }
    });
    
    
}

void testFunctionC2(void* windowptr) {
    NSLog(@"testFunctionC2 was called");
    long myLongValue = (long)windowptr;
    NSLog(@"testFunctionC2 myLongValue = %ld", myLongValue);

    NSWindow* win = (__bridge NSWindow*)(windowptr);
    NSLog(@"NSWindow = %@", win);

    dispatch_async(dispatch_get_main_queue(), ^{
        NSView* hostView = win.contentView;

        if (hostView != nil && hostView.subviews.count) {
            NSLog(@"  Before NSVisualEffectView creation subviews.count = %lu", hostView.subviews.count);
            
            
            NSView* jfxView = hostView.subviews[0];
            // Remove jfxView from its current superview
            [jfxView removeFromSuperview];

            NSVisualEffectView *vfxView = [[NSVisualEffectView alloc] initWithFrame:[win.contentView bounds]];
            [vfxView setAppearance:[NSAppearance appearanceNamed:NSAppearanceNameVibrantDark]];
            [vfxView setBlendingMode:NSVisualEffectBlendingModeBehindWindow];
            [vfxView setMaterial:NSVisualEffectMaterialUnderWindowBackground];

            // Add jfxView to vfxView
            [vfxView addSubview:jfxView];
            [jfxView setFrame:vfxView.bounds];  // Make sure jfxView fills the vfxView

            // Add vfxView to hostView
            [hostView addSubview:vfxView positioned:NSWindowBelow relativeTo:nil];
            [vfxView setAutoresizingMask:(NSViewWidthSizable | NSViewHeightSizable)];
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
