#ifndef __FILIAL_H__
#define __FILIAL_H__

#include "vendas.h"
#include "CatCli.h"
#include "CatProd.h"
#include "vendas.h"

#define F 3

typedef struct avl *Avl;
typedef struct myAvl *MY_AVL;
typedef struct venda *VENDA;
typedef struct catVenda *CATVENDA;
typedef struct Filial *FILIAL;

FILIAL iniciaFilial();
int  insereFilial (FILIAL filial,char* charvenda,char*ttt);
void AddToArrayF(Avl node, char* arr[], int *i);
int travessiaArrayFILIAL(int u,Avl f1,Avl f2, Avl f3,char* arr[]);
#endif