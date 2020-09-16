#ifndef _VENDAS_H_
#define _VENDAS_H_

#include <stdlib.h>
#include "CatCli.h"
#include "CatProd.h"
#include "avl.h"

#define CAMPOSVENDA 7
#define SIZE_BUF_SALES 128



typedef struct venda *VENDA;
typedef struct catVenda *CATVENDA;

typedef struct catalogoProdutos *CATALOGO_PRODUTOS;

typedef struct catCli *CATCLI;

typedef struct avl *Avl;

typedef struct myAvl *MY_AVL;

typedef struct ordenaVendasC *ORDENAVENDASC;

typedef struct ordenaVendasP *ORDENAVENDASP;

ORDENAVENDASP iniciaVendasP();

ORDENAVENDASP insereVendasP(ORDENAVENDASP catvp, char prod[]);

ORDENAVENDASC iniciaVendasC();

ORDENAVENDASC insereVendasC(ORDENAVENDASC catvc, char cli[]);

VENDA criaVenda();

CATVENDA inicializaVendaDiv();

void freeVenda(VENDA nada);

char** tokenizeLinhaVendaDyn(char* vendaRaw);

VENDA mkVendaStruct(char* linhaVenda);

CATVENDA insereVEND (CATVENDA catV, char* linha);

int validaVendas( CATCLI clientes,CATALOGO_PRODUTOS produtos, char* vendida) ;

VENDA atualizaVendas(VENDA venda,char* cliente,char* produto,int mes,int filial,int quant,double preco,char* tipo);

char* procuraProduto(CATALOGO_PRODUTOS catProd,VENDA v,int tam);

int numProdNCompra(CATALOGO_PRODUTOS catProd,CATVENDA v);

int numCliNCompra(CATCLI catCli,CATVENDA v);

int  travessia (MY_AVL nod,Avl v);

//int percorreArvore(CATCLI cli,avl_node_t *arvore);

//int percorreVenda (CATCLI cli,CATVENDA v);
int travessiaArrayNUM(int a,Avl e,Avl nod);


int travessiaAux (MY_AVL i,MY_AVL v);

int treeTraversal(int a, Avl nod, char* arr[]);


int transformaEmvenda(int a,CATVENDA ardeu,Avl v);

int transformaEmvendaCLI(int a,CATVENDA ardeu,Avl v);

int firstTransversal(MY_AVL e,char* array[]);

int travessiaArrayAux(int u,Avl e,Avl nod, char* arr[]);

//void printVEND(CATVENDA v);

#endif