# readwrite

This document is the complementary comments document to src/tenkeylyser/readwrite.clj.

## layoutfileext
Stands for "layout file extension". Refactored out for ease of change. I never changed this.

## readlayout
Reads a layout from a file in the layouts/ directory, using the layout name (name) as a key.

## writelayout
Writes a layout to a file in the layouts directory. Originally used for the layout editor feature, but I gave up in implementing it.

## writedata
Writes ngram data (data) to a file in the data/ directory. Used in conjunction with readdata (see below).

## readdata
Reads ngram data from a file in the data/ directory, using the corpus name (corpusname) as a key.
