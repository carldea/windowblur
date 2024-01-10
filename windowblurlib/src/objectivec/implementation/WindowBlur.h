//
//  WindowBlur.h
//  WindowBlur
// xyz
//  Created by Dea, Carl on 1/6/24.
//

#import <Foundation/Foundation.h>
#import <AppKit/AppKit.h>

void testFunctionC(void* windowptr);
void testFunctionC2(void* windowptr);

@interface WindowBlur : NSObject
    + (void) testFunction1:(id)windowptr;
//    + (void) testFunction1:(void*)windowptr;
//    + (void) testFunction1:(NSString*)windowptr;
@end
