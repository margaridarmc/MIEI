#ifndef _FACTURACAO_H_
#define _FACTURACAO_H_


#define MES 12

#include "vendas.h"
typedef struct avl *Avl;
typedef struct myAvl *MY_AVL;
typedef struct venda *VENDA;
typedef struct catVenda *CATVENDA;
typedef struct faturacao *FATURACAO;


FATURACAO iniciaFat ();
int  insereFaturacao (FATURACAO faturacao,char* charvenda,char* tt);
int numFat(FATURACAO fact, int mes, char* produto,int nVN,int nVP,double fVN, double fVP,VENDA cvendas);
int numFATemMES(MY_AVL fatAVL, char* produto,int nVN,int nVP,double fVN, double fVP,VENDA cvendas);
void AddToArray(Avl node, char* arr[], int *i);
int faturMes (char* arr[],int tam, int nVN, int nVP,float fVN,float fVP);
int elimRep (char**arrP,char**arrG, int tam);
#endif
