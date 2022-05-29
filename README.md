# Miscellaneous Java Projects

In this repo are several small Java projects I worked on as assignments while a student at BCIT.

## Word Counter

Word Counter is just that: a program that counts words. It scans an input text file and stores the words as objects in an ArrayList. As words are added, they are compared to the other words in the ArrayList. If a word being scanned matches a word already in the list, the word in the list's frequency is incremented. At the end of the input file being scanned, the words are sorted in terms of frequency from highest to lowest. Then, the user enters a number of most frequent words to view, and those words are displayed on the console. 

Word Counter was built as a part of a first-term object-oriented programming class.

## Primes

Primes is a Java impelementation of the Sieve of Eratosthenes, an algorithm to find the prime numbers between 0 and an upper bound (N). The Sieve of Eratosthenes works as follows:
1. Create a list of length N (the upper bound) of boolean variables initialized to true,
2. Set the booleans at index 0 and index 1 to false (0 and 1 are not prime),
3. Starting at index 2, set the value of every multiple of that index to false (ie. 2 is prime, but any multiple of 2 is not prime), and
4. Repeat step 3 until you reach index sqrt(N); at that point, alll prime numbers between 0 and N have been determined.

Primes was built as a part of a first-term object-oriented programming class.

## Timesheet

Timesheet is a program that stores mock time sheets as a 14-digit hexadecimal number. It converts input numbers into their hexadecimal counterparts. It uses masking to extract the the hours worked on any given day in a week.

Timesheet was built as a part of a first-term object-oriented programming class.


