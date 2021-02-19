---
tags: CSE12WI21

---

---

# PA7: Binary Search Trees (closed)

---

**This assignment is closed to collaboration.**

This assignment will teach you how to implement a Binary Search Tree and use it to order and filter data from a text file. 



### Deadlines

This PA is due on ** **Tuesday, March 2, at 11:59pm PST** **  

### Starting Resources

Link to FAQ: https://piazza.com/class/kirvel5rj2337n   
Link to PA Video: https://canvas.ucsd.edu/courses/21784/external_tools/82 

## CSE Mantra: *Start early, start often!*

*You will notice throughout the quarter that the PAs get harder and harder. By starting the quarter off with good habits, you can prepare yourself for future PAs that might take more time than the earlier ones.*


## Getting the Code

The starter code is in the Github repository that you are currently looking at. If you are not familiar with Github, here are two easy ways to get your code.

1. Download as a ZIP folder 

   If you scroll to the top of Github repository, you should see a green button that says *Code*. Click on that button. Then click on *Download ZIP*. This should download all the files as a ZIP folder. You can then unzip/extract the zip bundle and move it to wherever you would like to work. The code that you will be changing is in the folder called *pa4-starter*.

2. Using git clone (requires terminal/command line)

   If you scroll to the top of the Github repository, you should see a green button that says *Code*. Click on that button. You should see something that says *Clone with HTTPS*. Copy the link that is in that section. In terminal/command line, navigate to whatever folder/directory you would like to work. Type the command `git clone _` where the `_` is replaced with the link you copied. This should clone the repository on your computer and you can then edit the files on whatever IDE you see fit.

If you are unsure or have questions about how to get the starter code, feel free to make a Piazza post or ask a tutor for help.

## Part 1: An Implementation of `DefaultMap` (18 points)

You’ll provide a fast implementation of an interface called `DefaultMap` in `BST.java`.  


You will implement all the methods defined in the `DefaultMap` interface. You must ensure that each has the specified big-O bound in the **worst case**, and argue why based on the documentation of any libraries you use, or based on your implementation. Note that these are big-O bounds, so doing _better_ (like O(1) where O(log(n)) is required) is acceptable. In each, _n_ represents the number of entries in the map.

- `put`, required O(n)
- `replace`, required _O(n)_
- `remove`, required _O(n)_
- `set`, required _O(n)_
- `get`, required _O(n)_
- `size`, required _O(1)_
- `isEmpty`, required _O(1)_
- `containsKey`, required _O(n)_
- `keys`, required _O(n)_

In `BST`, you will need come up with the proper instance variables to achieve the above runtime requirements.

The specifications for the other methods are defined in header comments in the `DefaultMap.java` file, which you should follow. 

**Important:** for keys(), the returned result need to be in ascending order

***Note:*** You are not allowed to use the java `SortedMap` interface or `Collections.sort`, or any other implementations of BSTs or sorting!!!

Your implementation of `DefaultMap` will be graded automatically by tests that we provide. We’ll provide a very minimal sanity check in the grader. DO NOT rely on it for testing!

## Part 2: File System Filtering (16 points)

 <hr />

### FileData 

 <hr />

In our file system, a file is represented as a `FileData` object which contains the information of its name, directory, and last modified date. This is the same FileData from PA6 so feel free to reuse your code!

#### Instance Variables

##### `name`

The name of the given file in string format.

##### `dir`

The directory of where the file is stored, represented in a string format.

##### `lastModifiedDate`

The date of when the file is last modified, represented in a string format. Format: mm/dd/yyyy

#### Methods

In FileData.java, you will implement and thoroughly test the following methods:

- `public FileData(String name, String directory, String modifiedDate)`
- `public String toString()`

#### `public FileData(String name, String directory, String modifiedDate)`

A *constructor* that creates an instance of `FileData` object by intializing its instance variables with the given parameters. You may assume that the parameters passed in to the constructor will be non-null.


#### `public String toString()`

A method that returns the string representation of FileData by displaying the file information. It should strictly follow the format of `{Name: file_name, Directory: dir_name,  Modified Date: date}`.

 <hr />

### FileSystem

 <hr />

The FileSystem class will be used to represent the entire structure of the file system. You should store the file information in the instance variables provided to ensure that the lookup times are as efficient as possible.  You are **NOT ALLOWED** to add any additional instance variables or include any additional imports in `FileSystem.java`.

## Instance Variables

##### `nameTree`

A BST that uses the file name as the key and the `FileData` as the value.  

![](https://i.imgur.com/io0Fayy.png)




##### `dateTree`

A BST that uses the file date (format: mm/dd/yyyy) as the key and a list of FileData as the value. This list should keep track of the frames in the order that they arrive in.   

![](https://i.imgur.com/LJZtKCd.png)




##### `dc`

A `DateComparator` that will be used to compare the date on the files (See section below).

## Inner Class `DateComparator`  

At times, we may want to create custom ways to compare two objects. 
To support this, Java has a built-in interface called Comparator that expresses this idea of user-defined comparisons. An implementatation of Comparator provides a single method, called compare, that takes two elements of a particular type and returns a negative number if the first is “smaller” than the second, 0 if they are equal, and a positive number if the first is “larger” than the second.

Comparators can be particularly useful when we may want to compare items by different criteria. For example, we may have a class defining a person:

```
class Person { String name; int age; }
And sometimes we want to order People by name, and other times by age. We could define a Comparator for each of those cases:

class AgeComparator implements Comparator<Person> {
  public int compare(Person p1, Person p2) { return p1.age - p2.age; }
}
class NameComparator implements Comparator<Person> {
  public int compare(Person p1, Person p2) { return p1.name.compareTo(p2.name); }
}
```

For our purposes, we want to be able to easily compare the last modified date without worrying about the format. To do this, we will create a class called `DateComparator` inside of `FileSystem.java` that will switch the given dates to the correct format and then compare them. 

#### `compare` Method

`public int compare(String date1, String date2)`  
Given two strings that contain the last modifed date, if `date1` is more recent than `date2` return 1, if `date1` is less recent than `date2` return -1 and if `date1` is equal to `date2` return 0. For example, `01/01/2021` is less recent than `01/02/2021` - thus `compare("01/01/2021", "01/02/2021")` should return -1. Similarly, `12/01/2020` is less recent than `01/01/2021` and should return -1. You can assume that none of the inputs are null.

Hint: You might want to change the format of the dates   

Additionally, please note that `Comparator` and `Comparable` are **not** the same thing. If you are curious, here are the links to the Java docs: 

Comparator: https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html 
Comparable: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html

## FileSystem Methods

In `FileSystem.java`, you will implement and thoroughly test the following methods:

- `public FileSystem()`
- `public FileSystem(String inputFile)`
- `public void add(String name, String dir, String date)`
- `public ArrayList<String> findFileNamesByDate(String date)`
- `public FileSystem filter(String startDate, String endDate)`
- `public FileSystem filter(String wildCard)`
- `public List<String> outputNameTree()`
- `public List<String> outputDateTree()`


#### `public FileSystem()`

Default constructor that creates a new `FileSystem` object and initializes its instance variable.

#### `public FileSystem(String inputFile)`

*Constructor* that creates a new `FileSystem` object with the given `inputFile` that contains the file system information. We have provided some skeleton code for reading the contents of the text file. You will need to initailizes FileSystem's instance variables and populate FileSystem with each file's information.   
Each file information is represented by a line formatted as `filename, directory, date` within the content of `inputFile`. For example, it could be `mySample.txt, /home, 02/01/2021`. (Note that since it is a unix type file system, forward slashes are used to represent directory hierarchy).  
We have also provided a sample file, `input.txt`, to show how each file information is represented within the inputFile. Feel free to add more data to the file to test your FileSystem implementation thoroughly.   
You may assume that `inputFile` parameter is properly formatted and is non-null.


#### `public void add(String name, String dir, String date)`

This method should create a FileData object with the given file information and add it to the instance variables of FileSystem. If there is a duplicate file name, then the FileData with the most recent date should be used. For example, if the first FileData stored in the trees is `test.txt, /home, 01/01/2021` and the next FileData is `test.txt, /home, 01/02/2021`, the second FileData should *replace* the first FileData stored in the trees.   
If the `name`, `dir`, or `date` is `null`, then do not add anything to the FileSystem.

#### `public ArrayList<String> findFileNamesByDate(String date)`

Given a `date`, return an ArrayList of file names that correspond to this date. This list should have the file names in the order that they were added. When a duplicate file is passed in, it should *update* the original FileData without changing its position in the ArrayList.   
If the `date` given is `null`, return `null`.

#### `public FileSystem filter(String startDate, String endDate)`

Given a `startDate` and an `endDate` (format: mm/dd/yyyy), return a new FileSystem that contains only the files that are within the range (`startDate` is inclusive, `endDate` is exclusive).
Assume the given parameters are valid and non-null.  

Example: Let's call `filter("01/01/2021", "02/14/2021")` on a `FileSystem` with the following `dateTree`:   

![](https://i.imgur.com/h3Uxt37.png)  



It should return a **FileSystem** with a `dateTree` that looks like the following (note: there should be a populated `nameTree` with the same entries):   

![](https://i.imgur.com/bBNgMHG.png)  


#### `public FileSystem filter(String wildCard)`

Give a string `wildCard`, return a new FileSystem that contains only the files with names that contain the `wildCard` string. Note that this wildcard can be found anywhere in the file name (if the wild card is `test`, then `test.txt`, `thistest.txt` and `thistest` would all be files that should be selected in the filter)  
Assume the given parameter is valid and non-null. 

Example: Let's call `filter("mySam")` on a `FileSystem` with the following `dateTree`:  

![](https://i.imgur.com/YBsdlMK.png)  



It should return a **FileSystem** with a `nameTree` that looks like the following (note: there should be a populated `dateTree` as well - it is not shown here):   

![](https://i.imgur.com/MuJ6PM0.png)

#### `public List<String> outputNameTree()`

Return a List<String> that contains the `nameTree` where each entry is formatted as:    
"<file name>: <FileData toString()>"  

This list should be in alphabetical order.   

Input file: 

```
mySample.txt, /home, 02/01/2021
mySample1.txt, /root, 02/01/2021
mySample2.txt, /user, 02/06/2021
```

Example Output: 

```
["mySample.txt: {Name: mySample.txt, Directory: /home, Modified Date: 02/01/2021}", 
"mySample1.txt: {Name: mySample1.txt, Directory: /root, Modified Date: 02/01/2021}", 
"mySample2.txt: {Name: mySample2.txt, Directory: /user, Modified Date: 02/06/2021}"]

```

#### `public List<String> outputDateTree()`

Return a List<String> that contains the `dateTree` where each entry is formatted as:  
"<date>: <FileData toString()>"  

The List should be in order from most recent to oldest. If there are multiple files associated with the same date, add them to the List in reverse order they were added into the ArrayList (see example below).

Input file: 

```
mySample.txt, /home, 02/01/2021
mySample1.txt, /root, 02/01/2021
mySample2.txt, /user, 02/06/2021
```

Example Output:

```
["02/06/2021: {Name: mySample2.txt, Directory: /user, Modified Date: 02/06/2021}", 
"02/01/2021: {Name: mySample1.txt, Directory: /root, Modified Date: 02/01/2021}", 
"02/01/2021: {Name: mySample.txt, Directory: /home, Modified Date: 02/01/2021}"]
```

 <hr />

## Testing

 <hr />

### BSTTest.java and FileSystemTest.java

For this PA, your unit tests will be graded for completion only, however, we **strongly** encourage you to thoroughly test every public method in your class (helper methods you create should inherently be *private*). You are required to have at least **two unique unit tests for each method** written by yourself. 



## Part 3: Gradescope Assignment (9 points)

Respond to the following prompts in *Programming Assignment 7 - questions*:

1. What is the benefit of returning a `FileSystem` for the filter methods vs a List of the files that meet the filter requirements?
2. Describe what the best case would be for a non-empty BST, specifically, what does the tree look like? How is this the best case? What methods benefit from the best case scenario? 
4. Justify the runtime bounds (worst case) for the methods you wrote in `BST`.


## Style (5 points)

The following files will be graded on style:

* BST.java
* BSTTest.java
* FileData.java
* FileSystem.java
* FileSystemTest.java

To find the full style guideline, follow this link: https://docs.google.com/document/d/1XCwv_vHrp1X4vmlmNJIiXnxPuRLPZQkQEGNrJHJ0Ong/edit?usp=sharing  

All guidelines that we will be following this quarter are marked in the Style Guidelines document. These are required and will be graded. 

On this PA, **all guidelines must be followed**, they are summarized below: 

- file headers
- method headers (not required for test methods)
- Lines cannot be longer than 100 characters
- Inconsistent indentation
- Lines **must not** be indented more than 6 times. If you have a need to
  indent more than 6 levels, build a helper method or otherwise reorganize your code
- Test method must have meaningful names
- Helper method must have meaningful names
- descriptive variable names
- magic numbers

## Asking for Help

This is a **closed** PA. That said, there are many ways to ask for related help:

- Ask anything as a private Piazza question (though you are not guaranteed a detailed answer)
- PA6 is still open, and you can learn a lot by completing PA6 fully. You can do all of the `BST` methods without thinking about PA6, but it's a great idea to get detailed help on PA6 before tackling the methods in PA7 if you don't have a complete PA6
- You can always ask for clarification about built-in Java libraries
- You can always ask for clarification and help with the BST implementations we work through in class

## Submitting

#### Part 1 & 2

On the Gradescope assignment **Programming Assignment 7 - code** please submit the following file structure:

* BST.java
* BSTTest.java
* FileData.java
* FileSystem.java
* FileSystemTest.java

The easiest way to submit your files is to drag them individually into the submit box and upload that to Gradescope. You may submit as many times as you like till the deadline. 

#### Part 3

Please submit your answers to the questions from part 3 on the Gradescope assignment **Programming Assignment 7 - questions**. You may submit as many times as you like till the deadline.



## Scoring (50 points total)

- 18 points: implementation of `DefaultMap` [automatically graded]
- 16 points: implementation of `FileSystem` [automatically graded]
- 2 point: BSTTest and FileSystemTest graded on completition [manually graded]
- 9 points: Gradescope Questions [manually graded]
- 5 points: Style [manually graded]





