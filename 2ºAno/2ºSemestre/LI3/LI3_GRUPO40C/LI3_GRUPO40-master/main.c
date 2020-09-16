#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define _GNU_SOURCE 
#include <ctype.h>
#include "interface.h"
#include "faturacao.h"
#include "Filial.h"

int main(int argc, char** argv){	
	CATCLI catClients = initCatCli();
	CATVENDA vendas = inicializaVendaDiv();
	CATALOGO_PRODUTOS p = iniciaProdutos();
	FATURACAO fact = iniciaFat();
	FILIAL filial = iniciaFilial();
	interface(catClients,p,vendas,fact,filial);
/*
	int on=1,i;
	CATALOG_CLIENTS CatClients = initClients();
	CATALOGO_PRODUCTOS CatProdutOs = initProducts();
	FILIAL Filiais[3];
	FACTURACAO Fact = initFact();
	for(i=0;i<3;i++)
		Filiais[i] = initFilial();
	while(on){
		running = interpretador(CatClients,CatProducts,Filiais,Fact);
		if(running == -1){
			freeMemory(CatClients,CatProducts,Filiais,Fact);
			CatClients = initClients();
			CatProducts = initProducts();
			Fact = initFact();
			for(i=0;i<3;i++) Filiais[i] = initFilial();
			running = 1;
		}
	}
	freeMemory(CatClients,CatProducts,Filiais,Fact);
	*/
	return 0;
	
}
