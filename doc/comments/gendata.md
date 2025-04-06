# gendata

This document is the complementary comments document to src/tenkeylyser/gendata.clj.

## parsecorpus
This function takes a corpus name (corpusname), and, after reading the file contents of "corpora/{corpusname}.txt", returns an object with:
- :name -> name of the corpus,
- :count -> number of characters in the corpus,
- :monograms -> monogram frequencies,
- :bigrams -> bigram frequencies,
- :trigrams -> trigram frequencies.
Note that all '\r's are filtered out to ensure CRLF/LF compatibility.
