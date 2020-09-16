#include "listaStrings.h"

/* Estrutura de uma Lista de Strings. */
struct lista{
	int nV;						/* Número de elementos da lista. */
	int limite;					/* limite alocado para a lista. */
	int maxString;			/* limite de cada string da lista. */
	ARRAY* elem;			/* Lista de elementos. */
};



/* Estrutura de uma String. */
struct array{
	char* array;				/* String. */
};


/* Estrutura de uma Página. */
struct vetor{	
	int nV;			//sp			/* Número de elementos da página. */
	int limite;		//pageSize		/* limite máximo de elementos numa página.*/
	int maxString;	//sizeElement		/* limite de cada string contida na página. */
	ARRAY* elem;     			/* Lista de elementos da página. */
};

/* Inicializar uma Página. */
VETOR iniciaVetor(int limite,int max){
	int i=0;
	VETOR vetor = malloc(sizeof(struct vetor));	
	vetor->elem = malloc(sizeof(ARRAY) * limite);
	while(i<limite){
		vetor->elem[i]=malloc(sizeof(struct array));
		vetor->elem[i]->array=malloc(max);
		i++;
	}
	vetor->nV = 0;
	vetor->limite = limite;
	vetor->maxString = max;
	return vetor;
}


/* Inicializar uma Lista de Strings. */
LISTA iniciaListaS(int limite,int max){
	int i=0;
	LISTA lista = malloc(sizeof(struct lista));	
	lista->elem = malloc(sizeof(ARRAY) * limite);
	while(i<limite){
		lista->elem[i]=malloc(sizeof(struct array));
		lista->elem[i]->array=malloc(max);
		i++;
	}
	lista->nV = 0;
	lista->limite = limite;
	lista->maxString=max;
	return lista;
}







/* Actualizar uma página com parte de informação da Lista de Strings. */
VETOR atualiza(LISTA lista,int inicio,int max,int tam){
	int i=0;
	int j = inicio;
	VETOR vetor = iniciaVetor(tam,max);
	while(i<tam && lista->elem[j]!=NULL){
		vetor->elem[i] = lista->elem[j];
		i++;j++;
	}
	return vetor;
}





/* Remover um elemento da Lista de Strings. */
LISTA tiraElem(LISTA lista,char* elem){
	int i=0;
	while(i<lista->nV){
		if(strcmp(lista->elem[i]->array,elem)){
			strcpy(lista->elem[i]->array,lista->elem[i+1]->array);
			strcpy(elem,lista->elem[i]->array);
		}
		i++;
	}
	lista->nV--;
	return lista;
}


/* Adicionar um elemento à Lista de Strings. */
LISTA adicionaElem(LISTA list,int pos, char* elem){
	strcpy(list->elem[pos]->array,elem);
	list->nV++;
	return list;
}



/* GETS E SETS */


char* getListaS(LISTA list,int indice){
	return list->elem[indice]->array;
}


int getListaSize(LISTA list){
	int tam=list->limite;
	return tam;
}

int getListanV(LISTA list){
	int num = list->nV;
	return num;
}


void setListalimite(LISTA list,int tam){
	list->limite = tam;
}

/* Re-alocar memória para a Lista de Strings. */
LISTA memRE(LISTA lista){	
	int i=(lista->limite)/2;
	if (lista->nV > (lista->limite*3)/4) {
		lista->limite *= 2;
		lista->elem = realloc(lista->elem,lista->limite*sizeof(ARRAY));
		while(i<lista->limite){
			lista->elem[i]=malloc(sizeof(struct array));
			lista->elem[i]->array=malloc(lista->maxString);
			i++;
		}
	}	
	return lista;
}


ARRAY* getListaelem(LISTA list){
	return list->elem;
}

void setListanV(LISTA list,int nov){
	list->nV = nov;
}

int getLimite(VETOR vet){
	int lim = vet->limite;
	return lim;
}

char* getElementoVetor(VETOR vet,int indice){
	char* res = vet->elem[indice]->array;
	return res;
}
