load 'aoc.ijs'
d =: lines 'day18'

ev1 =: ".@|.@('())('&stringreplace)
+/ev1 every d  NB. 3159145843816

ev2 =: ".@('((((((','))))))',~[)@(('+';')+(';'*';'))*((';'(';'((';')';'))')&stringreplace)
+/ev2 every d   NB. 55699621957369