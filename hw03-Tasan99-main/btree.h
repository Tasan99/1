#ifndef BTREE_H
#define BTREE_H

typedef struct btree_struct * btree;

btree btree_create(int root_payload);
void btree_addNode(btree root,int payload);
char * btree_find_depthFirst(btree root,int val,char *buf);
char * btree_find_breadthFirst(btree root,int val,char *buf);
void  btree_print(btree root,char *pref,int isLeft);
int btree_numNodes(btree root);
void btree_free(btree root);
#endif