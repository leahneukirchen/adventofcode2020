load 'aoc.ijs'
d =: lines 'day02'

part1=: 3 : 0
'a b c d' =. chopstring '- ' stringreplace y
(-: /:~) (".a),(+/d={.c),(".b)
)

+/ part1@> d    NB. 586

part2=: 3 : 0
'a b c d' =. chopstring '- ' stringreplace y
1=+/ ((<: (". a),(".b)) { d) = {.c
)

+/ part2@> d    NB. 352
