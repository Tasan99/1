CC = gcc
CFLAGS = -Wall -std=c18 -ggdb
treevals = 12 321 22 14 43 91 321 91 14 43

test : tryTree
	 printf "%s\n" p 321 14 91 43 q | ./tryTree ${treevals}

try : tryTree
	./tryTree ${treevals}

gdb : tryTree
	gdb --args ./tryTree ${treevals}	
	
checkMem : tryTree
	printf "%s\n" p 321 q | valgrind --leak-check=full --show-leak-kinds=all -s ./tryTree ${treevals}		
	
tryTree : tryTree.c btree.c btree.h
	${CC} ${CPPFLAGS} ${CFLAGS} -o tryTree tryTree.c btree.c
	
clean :
	-rm tryTree