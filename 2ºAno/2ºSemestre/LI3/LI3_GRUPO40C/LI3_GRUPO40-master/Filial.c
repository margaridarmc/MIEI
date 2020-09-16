#include "Filial.h"

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

struct Filial{
	MY_AVL fil[F];
};

FILIAL iniciaFilial(){
	int c=1;
	FILIAL filial = malloc(sizeof(struct Filial));
	while(c<=F){
		filial->fil[c]=initMyAvl();
		c++;
	}
	return filial;
}

int  insereFilial (FILIAL filial,char* charvenda,char*ttt){
	  VENDA vendaDiv= mkVendaStruct(ttt);
	  int ff=vendaDiv->filial;
	  insertMyAvl(filial->fil[ff],charvenda,NULL,1);
	  return 0;
	}


void AddToArrayF(Avl node, char* arr[], int *i){
    if(node == NULL)
        return ;
    VENDA venda = criaVenda();
    venda=mkVendaStruct(node->code);
    arr[*i] = venda->cliente;
    ++*i;
    AddToArrayF(node->left, arr, i);
    AddToArrayF(node->right, arr, i);
}


//adiciona a um array os clientes que compram nas tres filiais

int travessiaArrayFILIAL(int u,Avl f1,Avl f2, Avl f3,char* arr[]){
    int count = 0;
    if (f1!=NULL && f2!=NULL&& f3!=NULL  ) {

        if(f3->left!=NULL) count += travessiaArrayFILIAL(u,f1, f2,f3->left, arr);
        if(existAvl(f2,f3->code) && existAvl(f1,f3->code) ) {arr[u + count++] = f3->code;}  
        if(f3->right!=NULL)count += travessiaArrayFILIAL(u + count,f1, f2,f3->right, arr);
    }

    return count;
}


