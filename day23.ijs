d =: 10 #.inv 158937462

step =: 3 : 0
'c h t' =. ({. ; }.@(4&{.) ; 4&}.) y
'a b' =. t ({. ; }.)~ >: {. /: (|. (<:c) |. 1+i.9) i. t
a,h,b,c
)

,":"0}.(]|.~I.@(1&=)) step^:100 d

NB. part2 TBD