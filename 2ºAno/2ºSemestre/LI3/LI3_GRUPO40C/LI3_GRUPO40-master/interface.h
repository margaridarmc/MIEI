#ifndef __INTERFACE_H__
#define __INTERFACE_H__

#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <time.h>

#include "CatCli.h"
#include "CatProd.h"
#include "listaStrings.h"
#include "vendas.h"
#include "paginacao.h"
#include "faturacao.h"
#include "Filial.h"

typedef struct myAvl *MY_AVL;
typedef struct catVenda *CATVENDA;
typedef struct avl *Avl;
typedef struct venda *VENDA;
typedef struct catalogoProdutos *CATALOGO_PRODUTOS;
typedef struct faturacao *FATURACAO;


void menu();
void interface(CATCLI catc,CATALOGO_PRODUTOS catp,CATVENDA vendas,FATURACAO fact,FILIAL filial);
void querie1 (CATCLI catCli,CATALOGO_PRODUTOS catProd,CATVENDA vendas,FATURACAO fact,FILIAL filial, int cv,int pv,int vv);
int QUERIE2 (CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial);
int querie3(CATCLI catCli,CATALOGO_PRODUTOS catProd,CATVENDA vendas,FATURACAO fact,FILIAL filial);
int QUERIE4 (CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial);
void fazCatalogos(CATCLI cliente,char* clientsFile,CATALOGO_PRODUTOS produto,char* prodFile,CATVENDA vendas,char* vendasFile,FATURACAO fact,FILIAL filial,int cv,int pv,int vv);
int QUERIE4 (CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial);
int querie5(CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial);
int querie6(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial);
int querie7(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial);
int prodporMes(Avl arv, char*stringC,int dim);
int querie8(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial);
int auxquerie8(FATURACAO fact,int iMes,int *nV,double *fV);
int querie9(CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial);
int querie10(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial);
int querie12(CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial);
int ordenaArrays(int*arr3,char**arr2,int j);
#endif