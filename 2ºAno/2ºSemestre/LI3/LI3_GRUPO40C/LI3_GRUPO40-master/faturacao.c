#include "faturacao.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct avl {
	char* code;								
	int height;							
	void* info;							
	struct avl *left, *right;				
};

struct myAvl {
	int total;								
	Avl avl;								
};
struct venda{
  char* cliente;              
  char* produto;            
  double preco;             
  int quant;            
  char* tipo;         
  int filial;             
  int mes;      
  char* vendaCompleta;        
};

struct catVenda{
    MY_AVL  catvendas;
    };

struct faturacao{
	MY_AVL fatMES [MES];
};



FATURACAO iniciaFat (){
	int c=1;
	FATURACAO faturacao = malloc (sizeof(struct faturacao));
	while(c<=MES){
		faturacao->fatMES[c] = initMyAvl();
		c++;}
    return faturacao;
}



int  insereFaturacao (FATURACAO faturacao,char* charvenda,char*tt){
	  VENDA vendaDiv= mkVendaStruct(tt);
	  int nMes=vendaDiv->mes;
	  insertMyAvl(faturacao->fatMES[nMes],charvenda,NULL,1);
	  return 0;

	}


void AddToArray(Avl node, char* arr[], int *i){
    if(node == NULL)
        return ;

    arr[*i] = node->code;
    ++*i;
    AddToArray(node->left, arr, i);
    AddToArray(node->right, arr, i);
}

int faturMes (char* arr[],int tam, int nVN, int nVP,float fVN,float fVP){
	int i;
	int x=0,y=0;
	float z=0,w=0;
	VENDA venda=criaVenda();
	for(i=0;i<tam;i++){
		venda=mkVendaStruct(arr[i]);
		if(strcmp(venda->tipo,"N")==1) { x+=1;z+=venda->preco;}
		else { y++;w+=venda->preco;}
	}
	nVN=x;
	nVP=y;
	fVN=z;
	fVP=w;
	return 0;
}
