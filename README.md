# number-base-converter
###### convert numbers and fractions to a source base then to a target base
---
#### This small app is converting numbers from any given source base to any target base. As there are 26 Latin letters and 10 digits, the maximum base is 26 + 10 = 36. So, the target and source base will be between 2 and 36.
---
### Some few converting inputs and outputs (and also see how the user interface looks):
---
##### #1
* Enter two numbers in format: {source base} {target base} (To quit type /exit) > 10 7
* Enter number in base 10 to convert to base 7 (To go back type /back) > 0.234
* Conversion result: 0.14315
---
##### #1.1
* Enter number in base 10 to convert to base 7 (To go back type /back) > 10.234
* Conversion result: 13.14315
---
* Enter number in base 10 to convert to base 7 (To go back type /back) > /back
---
---
##### #2
* Enter two numbers in format: {source base} {target base} (To quit type /exit) > 35 17
* Enter number in base 35 to convert to base 17 (To go back type /back) > af.xy
* Conversion result: 148.g88a8
---
##### #2.1
* Enter number in base 35 to convert to base 17 (To go back type /back) > aaaa.0
* Conversion result: 54e36.00000
---
* Enter number in base 35 to convert to base 17 (To go back type /back) > /back
---
---
##### #2.2
* Enter two numbers in format: {source base} {target base} (To quit type /exit) > 21 10
* Enter number in base 21 to convert to base 10 (To go back type /back) > 4242
* Conversion result: 38012
---
##### #2.3
* Enter number in base 21 to convert to base 10 (To go back type /back) > 4242.13a
* Conversion result: 38012.05550
---
* Enter number in base 21 to convert to base 10 (To go back type /back) > /back
---
* Enter two numbers in format: {source base} {target base} (To quit type /exit) > /exit
---

##### If you are wondering why this line `Enter two numbers in format: {source base} {target base} (To quit type /exit) > {source base} {target base}` shows only once, is because the {source base} and {target base} is saved until the user types /back and needs to add the again (different, or same values)

##### If you want more info about converting decimals, read about converting decimals and/or fractions to binary (base 2); this app converts from any base between 2 and 36
