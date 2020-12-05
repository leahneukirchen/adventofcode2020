d =: ':' splitstring&> '\w+:\S+'&rxall&> cutpara 1!:1 <'day04'

look =: 4 : 0
{. > }. (y,('';'0')) {~ ({."1 y) i. < x
)

inr =: 4 : '(-: /:~) ({. x), y, (}. x)'

ks =: 0 = [: # (<;._1 ' byr iyr eyr hgt hcl ecl pid')&-.

hv =: 3 : 0
(>(('cm';'in') i. <_2 {. y) { 150 193;59 76; 1 0) inr ". _2 }. y
)

rv =: 3 : 0
l =. ks {."1 y
l =. l *. 1920 2002 inr ". 'byr' look y
l =. l *. 2010 2020 inr ". 'iyr' look y
l =. l *. 2020 2030 inr ". 'eyr' look y
l =. l *. '#[0-9a-f]{6}' rxeq 'hcl' look y
l =. l *. 'amb|blu|brn|gry|grn|hzl|oth' rxeq 'ecl' look y
l =. l *. '[0-9]{9}' rxeq 'pid' look y
     l *. hv 'hgt' look y
)

+/ks"1 {."1 d   NB. 228
+/rv"2 d        NB. 175
