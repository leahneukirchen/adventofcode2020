d =: 5 2 8 16 18 0 1

h =: 3 : 0
if. 1 = #y do.
  0
else.
  --/ _2 {. y
end.
)

part1 =: 3 : 0
for. i. 2020 do.
  d =: d,h I. d = {:d
end.
2019 { d
)

part1''  NB. 517

NB. part2 tbd
