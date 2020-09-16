#ifndef __AVL_H__
#define __AVL_H__

#include <string.h>
#include <stdlib.h>
#include <stdio.h>


#define TRUE 1
#define FALSE 0


typedef struct avl *Avl;

typedef struct myAvl *MY_AVL;

typedef void(*Func)(void*);

Avl initAvl();

MY_AVL initMyAvl();

MY_AVL insertMyAvl(MY_AVL a,char* line,void* info,int aux);

int heightAvl(Avl a);

Avl insert(Avl estrutura,char* line,void* info);

void removeAvl(Avl estrutura,Func freeInfo);

void removeMyAvl(MY_AVL estrutura,Func f);

void freeNodo(Avl a);

int existMyAvl(MY_AVL estrutura,char* line);

int existAvl(Avl estrutura, char* line);

int totalElements(MY_AVL estrutura);

void* findInfo (Avl a,char* line,int* x);

Avl cloneAvl (Avl estrutura);

MY_AVL cloneMyAvl (MY_AVL estrutura);

void removeFromMY_AVL (MY_AVL estrutura,int x);

int infoNULL(Avl a);

void printInOrder(Avl root);

void printArv (MY_AVL arvore);



#endif