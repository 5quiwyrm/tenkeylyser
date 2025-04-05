# Stats

This analyser supports up to trigram stats, of which there are none (haha).

The available stats are:
- Heatmap score
- Travel distance
- Alts
- Swipe-taps
- Repeated swipes

Note that all examples will be given with reference to the hyper tenkey layout, created by Valorance.

## Heatmap score
This is calculated with reference to a heatmap, found in src/tenkeylyser/customise.clj.
The heatmap score of a letter requiring a swipe is treated as the sum of the heatmap score of the locations that it goes through.
For example, the heatmap score of "p" in hyper is treated as the heatmap score of "t" plus the heatmap score of "s".

Python implementation:
```python
heatmap = {
  [-2  1]: 5.0,
  [-1  1]: 4.0,
  [0   1]: 3.0,
  [-2  0]: 3.0,
  [-1  0]: 2.0,
  [0   0]: 1.0,
  [-2 -1]: 4.0,
  [-1 -1]: 3.0,
  [0  -1]: 2.0
}
def hmscore(character, layout):
  locs = getloc(character, layout)
  # getloc gets the locations the hand has to go to to type the character on the layout.
  # This could be None
  if locs is None:
    return 0
  # A non-existent letter is treated as 0 cost to type
  score = 0
  for loc in locs:
    # looping through all locations to get the heatmap scores
    locscore = heatmap[loc]
    # Getting the score for the location
    if locscore is not None:
      # non-existant locations are treated as 0 cost to type
      score += locscore
      # Tallying up all scores
  return score
```

## Travel distance
This operates on bigrams.
It calculates the distance a your thumb has to travel to type a bigram. If the bigram happens to be an alt (change of hands), the distance is treated as 0.
For example, the score for the bigram "st" is 1.0. As the distance between those keys is 1.0 units.

Python implementation:
```python
def subvect(v0, v1):
  return [v0[0] - v1[0], v0[1] - v1[1]]
# Subtracts two vec2-s
def magnvect(v):
  return sqrt(v[0] ** 2, v[1] ** 2)
# Finds the magnitude of a vec2

def bigramdist(bigram, layout):
  if (gethand(bigram[0], layout) != gethand(bigram[1], layout)):
    return 0
  # Alts have bigram distance of 0
  loc1 = getloc(bigram[0], layout)
  loc2 = getloc(bigram[1], layout)
  locs = loc1 + loc2
  # Appending the locations imitates typing the whole bigram
  score = 0
  for i in range(len(locs) - 1):
    score += magnvect(subvect(locs[i:i + 2]))
    # Adding up all of the magnitudes
  return score
```

## Alts
This operates on bigrams.
It calculates whether a bigram involves a change in hands.
If it is, it returns 1, if not, it returns 0.
For example, the bigram "it" gets an alt score of 1, and  "th" gets a score of 0.

Python implementation:
```python
def isalt(bigram, layout):
  if (gethand(bigram[0], layout) != gethand(bigram[1], layout)):
    return 1
  else:
    return 0
```

## Swipe-taps

These describe a particularly nice feeling type of bigram, which are either:
- Swiping onto a key, then tapping it, OR
- Tapping a key then swiping onto another, OR
- Swiping onto a key, then swiping off of that key.
This effectively means a bigram where the ending location of the first letter is the same as the starting location of the second letter. "ke" on hyper is an example.

Python implementation:
```python
def isswipetap(bigram, layout):
  loc0 = getloc(bigram[0], layout)
  loc1 = getloc(bigram[1], layout)
  # pretend that you are allowed to index into None, as this is allowed by Clojure.
  if (loc0[-1] == loc1[0]):
    return 1
  else:
    return 0
```

## Repeated swipes
These describe repeated swipe of the same character. These do not feel nice (in my opinion).
For example, "ff" is a repeated swipe on hyper.

Python implementation:
```python
def isrepswipe(bigram, layout):
  locs = getloc(bigram[0], layout)
  if (len(locs) > 1 and bigram[0] == bigram[1]):
    return 1
  else:
    return 0 
``` 
