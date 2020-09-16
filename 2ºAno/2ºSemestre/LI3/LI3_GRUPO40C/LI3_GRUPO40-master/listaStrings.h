#ifndef __LISTASTRINGS_H__
#define __LISTASTRINGS_H__

#include <string.h>
#include <stdlib.h>

typedef struct lista *LISTA;
typedef struct vetor *VETOR;


VETOR iniciaVetor(int limite,int max);
LISTA iniciaListaS(int limite,int max);
VETOR atualiza(LISTA lista,int inicio,int max,int tam);
LISTA tiraElem(LISTA lista,char* elem);
LISTA adicionaElem(LISTA list,int pos, char* elem);
LISTA memRE(LISTA lista);



#endif