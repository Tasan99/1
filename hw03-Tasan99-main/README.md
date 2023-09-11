# HW03 - Searching Trees

## Assignment Goals

This assignment concentrates on using the C language to manage data structures. The main data structure we will use is a binary tree.

## Background: Balanced Unordered Binary Trees

![Binary Tree](https://github.com/BinghamtonUniversityCS140/images/blob/master/Binary_tree_v2.svg)
(Picture from the Wikipedia [Binary Tree](https://en.wikipedia.org/wiki/Binary_tree) article, by Radke7CB - Own work, CC BY-SA 4.0, https://commons.wikimedia.org/w/index.php?curid=114167467)

A binary tree is a data structure in which each node can have no children, a left child, and/or a right child. Each binary tree starts with a "root", the "1" node in the picture above. The "1" node has left child "7" and right child "9", and so on. Binary trees are very useful and lend themselves very nicely to recursive programming. For example, the number of nodes in a binary tree is 1 + the size of the tree rooted at the left node + the size of the tree rooted at the right node. In C, you would code this as:

```c
int numNodes(btree root) {
   if (root==NULL) return 0;
   return 1+numNodes(root->right)+numNodes(root->left);
}
```

Trees are recursive because there is really no difference between a node and a tree. Since each node has a (potentially NULL) left node pointer and right node pointer, we can treat the left and right nodes as roots of sub-trees! Then our recursive function has to deal with the root, and results of the function on the left and right sub-trees. In the numNodes example, the root counts for one node, so the size of the tree is just 1 (the root) plus the size of the left sub-tree plus the size of the right sub-tree. The base case occurs when a left pointer or right pointer is NULL, in which case the size of the left or right subtree is zero.

I have provided code which creates a new node from each command line argument and inserts that node into a *balanced* binary tree. Balanced means that each node at any given level of the tree has roughly the same number of nodes as every other node at that level. The tree in the illustration above is *not* balanced. For example , the 2 node has no children, but the 6 node at the same level has two children.

I have also provided code to print a binary tree, but my printout orients the tree with the root at the left side instead of the top, and "left" branches are up in my printout; "right" branches are down; so that I can print out the tree line by line.

Notice that the binary tree we are using is not an *ordered* binary tree. In an ordered binary tree, every left node is less than or equal to the root, and every right node is greater than the root. That's not the case in the illustration above, or in the trees we are using in this homework.

This assignment is primarily concerned about finding the path from the root of the tree to a specific value in the tree. There are several ways to do this, and the answer may not be unique because more than one node can have the same value. For example, in the illustration above, there are two "9" nodes and two "5" nodes.

We will represent the path to the node using a character string. We assume you start at the root. We use an \* to indicate "look here", so if the root contains the value we are looking for, the path is just "\*". We use an L to indicate "take the left branch" and an R to indicate "take the right branch". For example, the path to the 11 node is "LRR\*", which says, starting at "1", go left to "7", then right to "6", then right again to "11", and look there. Notice there are two paths to "5", "LRL\*" and "RRL\*". Either one is correct.

One search method is called a *depth-first* search. A depth first search searches an entire sub-tree before switching to another sub-tree. In our case, we will search the left sub-tree first. If we can't find the node on the left sub-tree, then we will search the right sub-tree. The nice thing about a depth-first search in a binary tree is that we can use a recursive algorithm to search the tree.

An alternative is to do a *breadth-first* search through the tree. A breadth first search looks at all the nodes at the current level of the tree before looking at the children of the current level. For example, a bread-first search of the tree in the illustration above would start at the top level, check "1", then descend to the second level and check "7" and "9"; then to the third level and check "2", "6", and "9"; and finally the fourth level and check "5", "11", and "5". A bread-first search is much harder to implement recursively. However, it is possible to write a recursive function that examines all the nodes of a tree at a specific level. We can then start at the root level. If we don't find a value there, then look at the level below the root - the level with depth of 1. The *depth* of a node in a tree is the number of branches required to get from the root node to that node. If we can't find the value at depth 1, try all the nodes with depth 2, and so on.

For example, a breadth-first search for "6" in the tree illustrated above would start with the "1" node at depth 0. Since "1" is not "6", we need to then look at the depth 1 nodes, "7" and "9". Since we still haven't found the "6" node, we then look at the level 3 nodes, "2", "6" and "9". Now, we find the "6" node, whose path is "LR*".

## Background: Returning Strings from C Functions

There is no "string" data type in C. In C, a "string" is just an array of characters, terminated by a *null terminator*, a "character" whose ASCII value is 0x00.

Since C does not allow you to return an entire array, the only way to "return" a string is to return a pointer to an array of characters in memory, specified in C as `char *`. The problem is what memory can you use keep you string?

Most programmers try using a local variable such as `char buf[100];` to keep a result string, and then `return &buf[0]` or use the C shorthand `return buf;` which means the same thing. **This doesn't work** because C returns the memory for all local variables back to the operating system when a function invocation ends! That means you are returning a pointer to memory you no longer have control over, and when you dereference that pointer, the memory may have changed.

There are three alternatives to using a local variable to keep a string result to be returned, as follows:

1. Use a static or global variable for the returned string instead of a local variable. Memory for global and static variables are reserved when the program starts, and not returned to the operating system until the entire program ends. If you declare `static char buf[100];`, then fill in `buf` in your function, you can `return buf`, and your user will be able to use the result. I used this mechanism in HW01 in the `coord_format` example.

   Using a static or global variable has only one problem. If you invoke the function again, it will modify the static buffer. As long as your caller is aware of that problem, you can usually live with that restriction. But it can lead to some unexpected results! For example, the following line:

   `printf("c1=%s c2=%s\n",coord_format(c1),coord_format(c2));`

   ... will produce totally unexpected results. The same coordinate will get printed twice!

2. Reserve space for the string using the C `malloc` library function, and return a pointer to the malloc'ed space. For example, `char * buf=malloc(100);` reserves 100 bytes, and puts a pointer to that space in `buf`. I can then fill in that space, for example `sprintf(buf,"(%f,%f)",c->x,c->y);`, and `return buf`.

   My caller can then use the returned result. It will stay around as long as the user needs it. The only disadvantage of this strategy is that the user must invoke `free(result)` when he or she is finished with the result. If the caller does not free the result, it will cause a memory leak.

   One example of this strategy is the system library `strdup` function. You can run `result=strdup(buf)`, which will cause `strdup` to `malloc` space large enough to hold the string in `buf`, and copy the contents of the string in `buf` to `result`. When you are done with `result` you need to `free(result);` to avoid a memory leak.

3. Require the caller to specify a space to put the result, and optionally return a pointer to that result. The caller must provide a parameter to the function that is a pointer to memory specified by the caller that is large enough to hold the result. The function then writes the result to the space provided, and optionally returns a pointer to that result.

   The best example of this strategy is the `sprintf` library function. For example, if you invoke `sprintf(buf,"(%f,%f)",c->x,c->y);`, then `sprintf` will convert the floating point values `c->x` and `c->y` to ASCII, insert then in the format string `"(%f,%f)"`, and write the result into the memory specified by the `buf` parameter.

   The disadvantage of this strategy is that it requires an extra parameter and requires the caller to pre-allocate a buffer large enough to hold the largest possible result.

   The advantage of this strategy is that the caller can be completely responsible for managing the memory. For example, the caller *can* use a local variable for the result, because that local variable will be available for the entire time the caller is running (including the time to invoke the lower level function.) This is particularly useful in a recursive function, where each level of recursion can make a local variable for the recursive results, invoke itself recursively pointing at the local variables, and combine those local variables to create the result that it passes up to it's caller.

   We will use this strategy in our depth first and breadth first searches.

Your job for this homework is to write the depth-first and bread-first search code.

## Provided Code

There are two files in this repository already, as follows:

### btree.h

The btree.h file includes all the declarations and data types required to use a balanced unordered binary tree, or *btree*.

As in HW02, we will use an *abstract data type* strategy for the btree. That means the btree.h file contains a typedef for `btree` that defines it as pointer to the `btree_struct`, but the `btree_struct` definition is **not** included. However, declarations of all the btree "method" functions are in the btree.h file.

### btree.c

This file contains the code to manage binary trees. It has several components:

- A definition of the `btree_struct` structure. This structure defines a single node of the binary tree. However, if we have a pointer to the root node of the tree, then we can get to all the nodes in the tree, so this serves as a tree as well as a single node of the tree.

- Completed definitions of most of the "method" functions defined in btree.h. All of these functions have been provided to you *except* the definitions of the `btree_search_depthFirst` and `btree_search_breadthFirst` functions. You will need to finish these functions. There are comments in the code to suggest one possible implementation.

You may add new functions to btree.c, and you will need to edit the search functions, but you should not need to modify any of the other code in btree.c.

### tryTree.c

This file contains a `main` function to demonstrate how btrees can be used. The main function reads the command line arguments and creates a balanced binary tree with a node for each argument. Then, the main function goes through a "command" loop, where it prompts for an input value. If it finds a numeric input value, it runs both a depth-first and breadth-first search for that value in the binary tree and re-prompts. If it finds the letter "p", it prints the binary tree and re-prompts. If it finds the letter "q", it quits and ends the program.

### Makefile

I have provided a Makefile that defines Make variables for the C compiler (CC) and C flags (CFLAGS) to be used for this assignment (and this course.) I have included three explicit make rules:

- `test` : has a dependency on the btree executable file, and runs the btree executable, passing in some command line arguments, and proving "standard input" values that are responses to the prompts, as if the user had typed these in.
- `try` : has a dependency on the btree executable file, and runs the btree executable, passing in some command line arguments, but allows you to type responses to the prompt yourself.
- `clean` : removes all the extra files created by the Makefile (the btree executable file)

## Doing the Assignment

1. Clone the repository on a UNIX machine. See [How to Use GitHub](https://www.cs.binghamton.edu/~tbartens/HowTo/Using_GitHub) for details on cloning, etc.
2. Read through the existing code as well as the explanations in this README file to understand the infrastructure. You can try compiling and running the code, but until you code the breadth first and depth first search functions, the searches won't work.
3. Edit the btree.c file to complete the findBF function.
4. Build and test the result using the make command. If you code the findBF function correctly, you should get the correct breadth-first search results. If your code is not working correctly, try using gdb to stop your code where it is failing, and step through the code a line at a time to see what is going wrong. See [How to Use GDB](http://www.cs.binghamton.edu/~tbartens/HowTo/Using_gdb) for more on debugging with gdb.
5. Once your code is working, commit and push your results into your master git repository, and get the hash code using `git rev-parse HEAD`. Paste that hash code into the submission area for hw02 in Brightspace.

If you get stuck, and can't figure out how to make progress on this assignment, please contact either the professor or one of the TA's during office hours, or via e-mail. The main purpose of this assignment is to see where there are problems or areas that need more attention... not to make you frustrated, so please let us know if you are confused or can't get something to work.

## Assignment Grading

This homework assignment is worth 10 points. You will get the full 10 points if the TA's can download your repository using the git hash code from Brightspace onto a computer science machine, and run `make test`, and get the correct results. You will receive the following deductions:

- -7 if btree.c causes a compile error.
- -2 if btree.c causes a compiler warning
- Up to -5 if make test produces incorrect results.
- -7 if the findBF function has not been modified.
- -2 for every 24 hours (or part of 24 hours) after the submission date.
