# layoutools

This document is the complementary comments document to src/tenkeylyser/layoutools.clj.

## getloc
Returns the locations a finger has to go through to type a character (charatcer) in a given layout (layout). Note that the function returns nil when the letter is not present.

## gethand
Returns the hand with which a character (character) is typed in a given layout (layout).
If a character is present on both hands, the left is prioritised.
nil is returned when there the letter is not present.
