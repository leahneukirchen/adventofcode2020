load 'aoc.ijs'
d =: ({.;".@}.) every lines 'day12'

rotl =: ] ({. ,~ [: - {:)@[&0~ 90 %~ [

step1 =: 4 : 0
'xx yy dx dy' =. y
'ins n' =. x
select. ins
case. 'N' do. y + 0,n,0,0
case. 'S' do. y - 0,n,0,0
case. 'E' do. y + n,0,0,0
case. 'W' do. y - n,0,0,0
case. 'L' do. xx,yy,n rotl dx,dy
case. 'R' do. xx,yy,(360 - n) rotl dx,dy
case. 'F' do. y + n*dx,dy,0,0
end.
)

+/|2 {. 0 0 1 0 (step1 F.. ]) d  NB. 1294

step2 =: 4 : 0
'ins n' =. x
select. {. ins
case. 'N' do. y + 0,0,0,n
case. 'S' do. y - 0,0,0,n
case. 'E' do. y + 0,0,n,0
case. 'W' do. y - 0,0,n,0
case. 'L' do. (2{.y),n rotl 2}.y
case. 'R' do. (2{.y),(360-n) rotl 2}.y
case. 'F' do. y + n*(2}.y),0,0
end.
)

+/|2 {. 0 0 10 1 (step2 F.. ]) d  NB. 20592
