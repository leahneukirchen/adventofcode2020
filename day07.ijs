load 'aoc.ijs'

ex =: 3 : 0
'n a b z' =. y
(".`0:@.(-:&'no') n); a,' ',b
)

parse =: 3 : 0
(<' 'joinstring 2 {. f),<ex"1 }. _4]\ f=.(<;._1)' ',y
)

d =: (parse every lines 'day07'), ('other bags.'); <0 2$''

look =: 4 : '{. > }. y {~ ({."1 y) i. < x'

part1 =: ([: +./ [: >@$:@> 1{"1 look&d)`1:@.(-:&'shiny gold')
<: +/part1 every {."1 d   NB. 278 slow :/

part2 =: [: >:@(+/) (>@{. * $:&>@}.)"1 @ look&d`0:@.(-:&'other bags.')
<: part2 'shiny gold'  NB. 45158
