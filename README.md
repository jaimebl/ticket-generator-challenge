# Ticket Generator Challenge
> A small challenge that involves building a Bingo 90 ticket generator.

## Usage

> Run java class

```shell
$ java -cp . BingoNinetyStripGenerator
```

```
   7   -  28  37   -   -  65  75   -
   5   -  27  35   -  58  67   -   -
   -  15  25   -  45  55   -   -  85
 -----------------------------------
   1  11   -  39  50   -  61   -   -
   8   -   -  31  41  51   -  71   -
  10   -  21   -  49  60   -   -  81
 -----------------------------------
   -  17  22   -   -   -  62  72  87
   -  12   -  32   -  52  68   -  89
   2  19   -   -  42   -   -  79  82
 -----------------------------------
   6   -   -   -  46  56   -  77  86
   -  16   -  38   -  59  69  80   -
   -   -  26  36   -   -  66  76  88
 -----------------------------------
   4   -  24  34   -  54   -   -  90
   -  14  29   -  48  57   -   -  84
   -  20  30   -  44   -  64  74   -
 -----------------------------------
   9   -   -  33  43  53   -   -  83
   3  18   -  40   -   -  70  78   -
   -  13  23   -  47   -  63  73   -
 -----------------------------------
```

> Run tests

```shell
$ mvn test
```

> Requirements
- Generate a strip of 6 tickets
    - Tickets are created as strips of 6, because this allows every number from 1 to 90 to appear across all 6 tickets. If they buy a full strip of six it means that players are guaranteed to mark off a number every time a number is called.
- Each row contains five numbers and four blank spaces
- Each column contains up to three numbers, which are arranged as follows:
    - The first column contains numbers from 1 to 9 (or 10),
    - The second column numbers from 10 (or 11) to 20,
    - The third, 20 (or 21) to 30 and so on up until the last column, which contains numbers from 81 to 90.
- Each column should contain at least 1 number (and not 3 white spaces)
- There can be no duplicate numbers between 1 and 90 in the strip (since you generate 6 tickets with 15 numbers each)
