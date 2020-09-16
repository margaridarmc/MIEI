#ifndef __CATPRODUCTS_H__
#define __CATPRODUCTS_H__

#define TAM_PRODUTOS 7

#define L_PRODUTOS 2

#define ABECEDARIO 26

#define TAM_BUFFER 64

#include <ctype.h>
#include "avl.h"



typedef struct catalogoProdutos *CATALOGO_PRODUTOS;

typedef struct avl *Avl;

/**
 * @brief Declaração do tipo MY_AVL, um tipo abstrato. 
 */
typedef struct myAvl *MY_AVL;


CATALOGO_PRODUTOS iniciaProdutos();

void ProdutoFree(char produto[]);

void freeCatalogoProds(CATALOGO_PRODUTOS catalogo);

CATALOGO_PRODUTOS insereProduto(CATALOGO_PRODUTOS catalogo, char* prod);

int existeProduto(CATALOGO_PRODUTOS catalogo, char prod[]);

int contaProdutosL(CATALOGO_PRODUTOS catalogo,char letra);

int numeroProdutos(CATALOGO_PRODUTOS catalogo);

int validaProdutos (char* produto);


#endif