load 'aoc.ijs'
't oy' =: lines 'day13'
x =: 0
y =: 0 -.~ oy =: x: ".&> (<;._1 ',',oy)

(n{y)*v{~n=.{./:v=.y-y|".t  NB. 2298

minv =: ]|(^-&2x)  NB. x assumed prime
(*/y)|+/(-oy i. y)*((*/%])([*minv)])y  NB. 783685719679632
