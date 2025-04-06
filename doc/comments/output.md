# output

This document is the complementary comments document to src/tenkeylyser/output.clj.

## pprintret
This was used as a utility during development. It pretty-prints a value (x) then returning it, effectively acting like an inspector into a value without changing code around it.

## printlayoutstats
Prints the stats of a tenkey layout (viewlayout) given a corpus.
Also prints the layout, layout name and corpus used.

## filterlocs
Filters for a characters in a side of the board (x) with a specific swipe direction (l).

## ccoerce
Returns '~' if given ' ', ' ' if given nil, and the input if it doesn't match '~' or nil.
