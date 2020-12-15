load 'aoc.ijs'
load 'regex'

land =: 23 b.
lor =: 17 b.

part1 =: 3 : 0
mem =. 1 $. (2^16);0;0
for_l. lines 'day14' do.
  if. 'mask' rxin >l do.
    mask =. 7 }. >l
    maska =. #. -. '1' i. mask
    maskb =. #. '0' i. mask
  else.
    'addr val' =. ". every '\d+' rxall >l
    mem =. mem addr }~ maskb lor maska land val
  end.
end.
+/ 5 $. mem
)
part1''  NB. 7997531787333


masked =: 4 : 0
m =. x='X'
(#. (x='1') +."1 (#: i. 2^+/m) (I. m)}"1 1 m) land y lor #. x='0'
)

part2 =: 3 : 0
mem =. 1 $. (2^36);0;0
for_l. lines 'day14' do.
  if. 'mask' rxin >l do.
    mask =. 7 }. >l
  else.
    'addr val' =. ". every '\d+' rxall >l
    mem =. mem (mask masked addr) }~ val
  end.
end.
+/ 5 $. mem
)
part2''  NB. 3564822193820
