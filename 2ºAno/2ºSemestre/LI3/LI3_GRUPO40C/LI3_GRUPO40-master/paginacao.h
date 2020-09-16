#ifndef __PAGINACAO_H__
#define __PAGINACAO_H__


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "faturacao.h"
#include "CatCli.h"
#include "CatProd.h"
#include "Filial.h"


typedef struct catalogoProdutos *CATALOGO_PRODUTOS;
typedef struct catCli *CATCLI;
typedef struct catVenda *CATVENDA;
typedef struct Filial *FILIAL;

//diz numero de paginas que são necessárias para representar todos os elemntos
int quantasPaginas(char* arrayDeAlgo[]);




//muda de pagina para a pagina que o utilizador desejar e faz as colunas necessarias;

int imprimeColunas(char* arrayElem[],int pagina , CATCLI c,CATALOGO_PRODUTOS p,CATVENDA v,FATURACAO fact,FILIAL fi);


#endif