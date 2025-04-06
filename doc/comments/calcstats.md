# calcstats

This document is the complementary comments document to src/tenkeylyser/calcstats.clj.

## hmscore
Calculates the heatmap score of a character (charatcer) in a layout (layout).
See doc/stats.md for more details.

## totalhmscore
Calculates the total heatmap score of a layout (viewlayout) given corpus data (data).

## subvect
Subtracts two vectors, v0 and v1 of numbers.

## magnvect
Finds the magnitude of a vector of numbers (v).

## bigramdist
Calculates the distance of a given bigram (bigram) in a given layout (layout).
Returns 0 if the bigram is an alt.
See doc/stats.md for more details.

## totaldist
Calculates the total bigramdist of a given layout (viewlayout) given corpus data (data).

## alt?
Calculates whether a given bigram (bigram) is an alt in a given layout (layout).
Returns 1 if yes, 0 if not.
See doc/stats.md for more details.

## totalalt
Calculates the total alt score for a layout (viewlayout) given corpus data (data). Effectively counts how many alts there are in a corpus.

## swipetap?
Calculates whether a given bigram (bigram) is a swipe-tap in a given layout (layout).
See doc/stats.md for more details.

## totalswipetap
Calculates the total swipetap score for a layout (viewlayout) given corpus data (data). Effectively counts how many swipe-taps there are in a corpus.

## repswipe?
Calculate whether a given bigram (bigram) is a repeated swipe in a given layout (layout).
See doc/stats.md for more details.

## totalrepswipe
Calculates the total repeated swipe score for a layout (viewlayout) given corpus data (data). Effectively counts how many repeated swipes there are in a corpus.


