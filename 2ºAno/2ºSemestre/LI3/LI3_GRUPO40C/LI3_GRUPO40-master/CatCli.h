#ifndef __CLIENTES_H__
#define __CLIENTES_H__

#include <ctype.h>
#include "avl.h"


#define TAM 26
#define NLETRAS 1

#define TRUE 1
#define FALSE 0

typedef struct catCli *CATCLI;
typedef int BOOL;
typedef struct avl *Avl;

/**
 * @brief Declaração do tipo MY_AVL, um tipo abstrato. 
 */
typedef struct myAvl *MY_AVL;


CATCLI initCatCli();
void ClienteFree(char cliente[]);
int validaClientes (char* cliente);
CATCLI insereCli(CATCLI catClientes, char cliente[]);
BOOL jaExisteC (CATCLI catClientes, char cliente[]);
int contaCliLetra (CATCLI catClientes,char letra);
int contaCliCat(CATCLI catClientes);


void printCLI(CATCLI clientela);

#endif