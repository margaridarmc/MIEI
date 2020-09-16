#include "paginacao.h"
#include "interface.h"






//partindo do limite que decidimos colocar em cada pagina 30 elemntos com 3 colunas (10 por coluna)


//dependendo do numero de elementos, define quantas paginas vao ser nacessarias

int quantasPaginas(char* arrayDeAlgo[]){
	int i;int conta=0;
	for(i=0;arrayDeAlgo[i];i++){
		if(i%40==0) conta++;
	}
	return conta;
}


//muda de pagina para a pagina que o utilizador desejar e faz as colunas necessarias, e conta os elementos totais da tabela

int imprimeColunas(char* arrayElem[],int pagina , CATCLI c,CATALOGO_PRODUTOS p,CATVENDA v,FATURACAO fact,FILIAL fi){
		printf("\e[2J\e[H");
	if ((arrayElem)==NULL ) {
		printf("Não existe tal caso");
		interface(c,p,v,fact,fi);return 0;
	}
	int paginas = quantasPaginas(arrayElem);

	int elementos =0;
	while(arrayElem[elementos]) elementos++;
	printf("\e[2J\e[H");
	printf("|===============================================================================================|\n");
	printf("|»                                                                                             «|\n");
	int i=40*(pagina-1);int j;
	for(j=0;arrayElem[i]&&j<20;i++,j++){
		if(arrayElem[i+1]){
			printf ("|« %40s     %40s       »|   \n", arrayElem[i], arrayElem[i+1]);
			i++;
		}
		else{ printf ("|« %40s     %40s       »|   \n", arrayElem[i]," ");}


	}
	printf(" ================================================================================================\n");

	printf("                                                                                PÁGINA %d/%d\n",pagina,paginas);
	printf("        NUMERO TOTAL DE ELEMENTOS:  %d                                  ESCOLHA PAGINA:",elementos);
	printf("        0.sair                                                                            \n");
	int utilizador;
	scanf("%d",&utilizador);
	if(utilizador == 0) {interface(c,p,v,fact,fi); return 0;}
	if(utilizador >0 && utilizador<=paginas) imprimeColunas(arrayElem,utilizador,c,p,v,fact,fi);
	else {printf("\n\nA PÁGINA ESCOLHIDA NÃO ESTÁ DISPONÍVEL, POR FAVOR INSIRA UMA EXISTENTE OU '0' PARA VOLTAR AO MENÚ\n");imprimeColunas(arrayElem,1,c,p,v,fact,fi);}
return 0;
	}
