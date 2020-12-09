load 'aoc.ijs'
p =: |:({."1 r),:v =. ". each 1 }"1 r =. cutopen every lines 'day08'

step =: 4 : 0
'ip acc' =. y
if. ip >: # x do.
  (#x),acc
else.
  'o n' =. ip { x
  (>:ip + (o -: 'jmp')*<:n),acc + n * (o -: 'acc')
end.
)

([: }. ] {~ [: (~: i. 0:) {."1) p&step^:(<1000) 0 0   NB. 1451

patch =: ] 0}~ [: ((,|.)'jmp';'nop')&stringreplace&.> 0 { ]
rs =: 3 : '(( p (y }~) (patch y { p) )&step^:1000) 0 0'
{: rp {~ (#p) i.~ {."1 rp =: rs"0 i. #p  NB.  1160
