#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "btree.h"

struct btree_struct {
	int payload;
	bool rightNext;
	struct btree_struct* left;
	struct btree_struct* right;
};

btree btree_create(int rootPayload) {
	btree node=malloc(sizeof(struct btree_struct));
	node->payload=rootPayload;
	node->rightNext=false;
	node->left=NULL;
	node->right=NULL;
	return node;
}

void btree_addNode(btree root,int payload) {
	if (root->rightNext) {
		if (root->right==NULL) {
			root->right=btree_create(payload);
		} else {
			btree_addNode(root->right,payload);
		}
		root->rightNext=false;
	} else {
		if (root->left==NULL) {
			root->left=btree_create(payload);
		} else {
			btree_addNode(root->left,payload);
		}
		root->rightNext=true;
	}
}

char * btree_find_depthFirst(btree root, int val, char *buf) {
  // Check if the root is NULL, return NULL if it is
  if (root == NULL) return NULL;

  // If the value is found in the root, add a '*' to the buffer and return the buffer
  if (root->payload == val) {
    strcat(buf, "*");
    return buf;
  }

  // Create a buffer for the recursive calls
  char rbuf[20];
  rbuf[0] = '\0';

  // Make a recursive call to search for the value in the left subtree
  char *result = btree_find_depthFirst(root->left, val, rbuf);
  if (result != NULL) {
    // If the value is found in the left subtree, add an 'L' to the buffer and append the result
    strcat(buf, "L");
    strcat(buf, result);
    return buf;
  }

  // Reset the buffer for the next recursive call
  rbuf[0] = '\0';

  // Make a recursive call to search for the value in the right subtree
  result = btree_find_depthFirst(root->right, val, rbuf);
  if (result != NULL) {
    // If the value is found in the right subtree, add an 'R' to the buffer and append the result
    strcat(buf, "R");
    strcat(buf, result);
    return buf;
  }

  // If the value is not found in either subtree, return NULL
  return NULL;
}
	
	/*------------------------------------------------------
	TODO: Search the tree specified by "root" to find the
	first node that has a payload of "val", and return the
	string path from the root to that node, where an "L" in 
	the path specifies take the left branch, and "R" specifies
	take the right branch, and a "*" says look here.

	The search should occur by first checking the payload
	at the root node, then checking the entire left sub-tree,
	then checking the entire right sub-tree

	The "buf" parameter provides a pointer to memory where
	the path can be stored. You may assume buf starts out
	with a 0x00 null string terminator at position 0.

	If "val" is not a payload of any node in the tree, a NULL
	pointer should be returned.

	RECOMMENDED ALGORITHM - Recursive Search
	- If the root is NULL, return NULL - value not found
	- If the root payload matches val, add "*" to the end of
	   buf and return. You found the result here
	- Make an rbuf local variable to hold the results of 
	   searching the sub-trees recursively. You may assume the 
	   path is less than 100 characters long. but be sure
	   the first character of rbuf is 0x00.
	- Invoke btree_find_depthFirst recursively on the left
	   sub-tree. If a result is found, add an "L" and the
	   result to buf, and return buf.
	- Invoke btree_find_depthFirst recursively on the right
	   sub-tree. If a result is found, add an "R" and the 
	   result to buf, and return buf.
	- If you get here, the result wasn't found in the tree,
	   so return NULL.
	--------------------------------------------------------*/


void btree_find_atDepth(btree *root, int val, char *buf, int *foundPtr, int depth) {

    if (*foundPtr == 1) {
        return;
    }

    if (root == NULL) {
        *foundPtr = -1;
        return;
    }

    if (depth > 0) {
        int leftFound = 0, rightFound = 0;
        char leftPath[100] = { 0 }, rightPath[100] = { 0 };
        btree_find_atDepth(root->left, val, leftPath, &leftFound, depth - 1);

        if (leftFound == 1) {
            *foundPtr = 1;
            strcat(buf, "L");
            strcat(buf, leftPath);
            return;
        }

        btree_find_atDepth(root->right, val, rightPath, &rightFound, depth - 1);
        if (rightFound == 1) {
            *foundPtr = 1;
            strcat(buf, "R");
            strcat(buf, rightPath);
            return;
        }

        if (leftFound == -1 && rightFound == -1) {
            *foundPtr = -1;
            return;
        }
    } else {
        if (root->payload == val) {
            *foundPtr = 1;
            strcat(buf, "*");
        }
    }
}

char *btree_find_path(btree *root, int val) {
    int found = 0;
    int depth = 0;
    char buf[100] = { 0 };

    while (found == 0) {
        btree_find_atDepth(root, val, buf, &found, depth);
        depth++;
    }

    if (found == -1) {
        return NULL;
    }

    return buf;
}



	/*------------------------------------------------------
	TODO: Search the tree specified by "root" to find the
	first node that has a payload of "val", and return the
	string path from the root to that node, where an "L" in 
	the path specifies take the left branch, and "R" specifies
	take the right branch, and a "*" says look here.

	The search should occur by first checking the payload
	at the root node, then checking all nodes that are one
	step away from the root, then all nodes that are two
	steps away,and so on.

	The "buf" parameter provides a pointer to memory where
	the path can be stored. You may assume buf starts out
	with a 0x00 null string terminator at position 0.

	If "val" is not a payload of any node in the tree, a NULL
	pointer should be returned.

	RECOMMENDED ALGORITHM - find at depth
	- Create an integer "found" variable that can have three
	   values: 1=>the value has been found; 0=>still looking;
	   -1=>Not in the tree at all. Initialize to 0
	- loop through depth, starting at 0, as long as found is 0
		- Check to see if the variable is found at this depth
	- If found is -1, return NULL.
	- Return the result of finding at depth

	In order to check for a value at a specific depth, I created
	a recursive function: 
		void btree_find_atDepth(btree root,int val,char *buf,int *foundPtr,int depth);

	The definition of this function is pretty simple:
	- if *foundPtr==1, return... someone else found this value
	- if (root==NULL) set *foundPtr=-1 and return... the value is not in this sub-tree
	- if (depth>0):
		- Create fleft and fright found variables and pleft and 
		   pright path variables
		- Invoke btree_find_atDepth recursively on the left sub-tree
		   using depth-1. 
		- If the recursive find is 1, set *foundPtr to 1, and add 
		   an "L" and the recursive path to the path, and return.
		- Invoke btree_find_atDepth recursively on the right 
			sub-tree using depth-1. 
		- If the recursive find is 1, set *foundPtr to 1, and add 
		   an "R" and the recursive path to the path, and return.
		- If both left and right recursions resulted in found of -1,
		   set *foundPTr to -1 and return
	- else depth==0, 
		- if the value of the root payload matches val, set
		   *foundPtr to 1, and add * with a null terminator
		   to the path.		
	--------------------------------------------------------*/	

void btree_print(btree root,char * prefix,int isLeft) {
	if (root==NULL) {
		printf("%s+-NULL\n",prefix);
		return;
	}
	char nxtPref[100];
	if (root->left) {
		sprintf(nxtPref,"%s%c     ",prefix,(isLeft==0)?'|':' ');
		btree_print(root->left,nxtPref,1);
	}
	printf("%s+%3d%s\n",prefix,root->payload,(root->left || root->right)?"-+":"");
	if (root->right) {
		sprintf(nxtPref,"%s%c     ",prefix,(isLeft==1)?'|':' ');
		btree_print(root->right,nxtPref,0);
	}
}

int btree_numNodes(btree root) {
	if (root==NULL) return 0;
	return 1+btree_numNodes(root->right)+btree_numNodes(root->left);
}

void btree_free(btree root) {
	if (root->left) btree_free(root->left);
	root->left=NULL;
	if (root->right) btree_free(root->right);
	root->right=NULL;
	free(root);
}