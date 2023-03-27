# Zoo

## Introduction

A while ago I was given a specification that went something like:

--- 8< ---

You have been asked to write a part of a simulation of a small zoo.

The zoo allows dogs, cats and mice into it and only accepts aminals with unique names.

The different species of animal have differing satisfaction levels when being fed:

 - Dogs: 35
 - Cats: 25
 - Mice 10

And have different calls:

 - Dogs: "woof"
 - Cats: "meow"
 - Mice "eek"

 The animals in the zoo are fed once a day; on Tuesday and Thursday they are fed 40 and on all other days 20. When they are fed, each animal responds with its call, lower-case if it is satisfied (i.e the amount it has been fed is less than or equal to its satisfaction level), upper-case if not.

 On weekdays the animals are fed in alphabetical order of their names, on weekends they are fed in any order.

 You should include some tests to show the simulation working.

 --- 8< ---

 ## Solution

 Several solutions were submitted, this is mine. :)

