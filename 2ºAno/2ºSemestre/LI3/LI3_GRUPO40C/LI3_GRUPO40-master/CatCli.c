#include "CatCli.h"
#define TAM 26


struct catCli{
	MY_AVL CatalogoC[TAM];};


/* Estrutura de um nodo da Avl. */
struct avl {
	char* code;								/* String com informação. */
	int height;								/* Altura, auxiliar ao algoritmo. */
	void* info;								/* Apontador para uma estrutura, se necessário. */
	struct avl *left, *right;				/* Apontadores para outras Avl's (esquerda e direita). */
};

/* Estrutura MY_AVL com uma Avl e um contador de elementos da mesma. */
struct myAvl {
	int total;								/* Total de elementos na estrutura Avl. */
	Avl avl;								/* Estrutura Avl que lhe corresponde. */
};


CATCLI initCatCli(){
	int c=0;
	CATCLI CatClientes = malloc (sizeof(struct catCli));
	while(c<TAM){
		CatClientes->CatalogoC[c] = initMyAvl();
		c++;}
    return CatClientes;
}

void ClienteFree(char cliente[]){
	free(cliente);
}


int validaClientes (char* cliente){
  int i=0;
  if(strlen(cliente)!=5) return 0; //são 5 caracteres

  for(i=0; i<1; i++){
        if( !(cliente[i]>='A') || !(cliente[i]<='Z') ) return 0;
    }
  for(i=1;i<5 ;i++){
    if( !(isdigit(cliente[i])) || !( (cliente[1]>='1' && cliente[1]<='4') || (cliente[1]=='5' && cliente[2]=='0'&& cliente[3]=='0'&& cliente[4]=='0'))) return 0;
  }
return 1;
}


CATCLI insereCli(CATCLI catClientes, char cliente[]){
	int i = cliente[0]-'A';
	catClientes->CatalogoC[i] = insertMyAvl(catClientes->CatalogoC[i],cliente,NULL,1);
	return catClientes;
}

BOOL jaExisteC (CATCLI catClientes, char cliente[]){
	int exist,i;
	if(cliente[0] >= 'A' && cliente[0] <= 'Z' ){
		i = cliente[0]-'A';
		exist = (existMyAvl(catClientes->CatalogoC[i],cliente)!=0);
		return exist;
	}
	else return FALSE;
}

int contaCliLetra (CATCLI catClientes,char letra){
	int res=0;
	int l = letra- 'A';
	res=totalElements(catClientes->CatalogoC[l]);
	return res;
} 

int contaCliCat(CATCLI catClientes){
	char l='A';
	int count=0;
	while (l<= 'Z'){
		   count+=contaCliLetra(catClientes,l);
		   l++;
		}
	return count;
}


/*
void printCLI(CATCLI clientela){
	int i=0;
	while(i<26){
		printf("LISTAAAAA\n");
		avl_print(clientela->CatalogoC[i],"%s\n");
		i++;
	}
}*/