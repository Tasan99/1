#include <stdio.h>
#include <stdlib.h> // for atoi
#include "btree.h"

int main(int argc,char **argv) {
	if (argc<3) {
		printf("Invoke as %s n1 n2 ... \n",argv[0]);
		return 1;
	}
	btree root=btree_create(atoi(argv[1]));
	for(int i=2;i<argc;i++) {
		btree_addNode(root,atoi(argv[i]));
	}
	char buf[100]={0};
	while(1) {
		printf("Enter a number to find:\n");
		int q;
		int isNum=scanf(" %d",&q);
		if (isNum==EOF) break;
		if (isNum) {
			buf[0]='\0';
			char *path=btree_find_depthFirst(root,q,buf);
			if (path==NULL) printf("Unable to find %d in the btree with depth first search\n",q);
			else printf("Found %d in the btree using depth first search with path: %s\n",q,path);
			buf[0]='\0';
			path=btree_find_breadthFirst(root,q,buf);
			if (path==NULL) printf("Unable to find %d in the btree with breadth first search\n",q);
			else printf("Found %d in the btree using breadth first search with path: %s\n",q,path);
		} else {
			char flag=getchar();
			if (flag=='p') btree_print(root,"",3);
			else if (flag=='q') break;
			else {
				printf("Not recognized... enter a number, a p to print the tree, or a q to quit\n");
			}
		}
	}

	btree_free(root);
	return 0;
}