#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "interface.h"

//#define TAMP 20
#define MAXBUFPROD 10
#define MAXBUFCLI 10
#define MAXBUFVEN 100



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

struct catCli{
	MY_AVL CatalogoC[TAM];
};

struct catalogoProdutos {
	MY_AVL catProdutos[ABECEDARIO];
};	

struct catVenda{
    MY_AVL  catvendas;
    };

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

struct Filial{
	MY_AVL fil[F];
};

struct faturacao{
	MY_AVL fatMES [MES];
};


void menu(){

	//printTop(0);
	printf("\e[2J\e[H");
	printf(" |===============================================================================================|\n");
	printf(" |»                                                                                             «|\n");
	printf(" |«      01. LEITURA FICHEIROS                                                                  »|\n");     
	printf(" |»      02. PROCURA ALFABETICA DE PRODUTOS NO CATÁLOGO PRODUTO                                 «|\n");
	printf(" |«      03. PROCURA DE VENDA POR PRODUTO E MÊS                                                 »|\n");
	printf(" |»      04. LISTA E NUMERO DE PRODUTOS NÃO COMPRADOS                                           «|\n");
	printf(" |«      05. LISTA DE CLIENTES CONSUMIDORES NAS 3 FILIAS.                                       »|\n");
	printf(" |»      06. LISTA DE CLIENTES QUE NÃO EFETUARAM COMPRAS                                        «|\n");
	printf(" |«      07. TABELA COM NÚMERO DE PRODUTOS QUE DADO CLIENTE COMPROU POR MÊS E FILIAL            »|\n");
	printf(" |»      08. NUMERO DE VENDAS E RESPETIVA FATURAÇÃO                                             «|\n");
	printf(" |«      09. LISTA DE CLIENTES QUE COMPRARAM UM DADO PRODUTO                                    »|\n");
	printf(" |»      10. LISTA ORDENADA DOS PRODUTOS MAIS VENDIDOS PARA UM CLIENTE                          «|\n");
	printf(" |«      11. PRODUTOS MAIS VENDIDOS                                                             »|\n");
	printf(" |»      12. 3 PRODUTOS ONDE O CLIENTE GASTOU MAIS DINHEIRO                          0. Sair.   «|\n");
        printf(" |»                                                                                             «|\n");
printf(" |===============================================================================================|\n\n\n");
}

void interface(CATCLI catc,CATALOGO_PRODUTOS catp,CATVENDA vendas,FATURACAO fact,FILIAL filial){
system("clear");
menu();
//char*vendas[1000000];
int cv=0,pv=0,vv=0;

int nQuerie;
printf("	Introduza o número do comando desejado: ");
scanf("%i",&nQuerie);
switch(nQuerie){

		case 1:
		querie1(catc,catp,vendas,fact,filial,cv,pv,vv);
			break;
		case 2: QUERIE2(catc,catp,vendas,fact,filial);
			break;

		case 3: querie3(catc,catp,vendas,fact,filial);
			break;

		case 4: QUERIE4(catc,catp,vendas,fact,filial);
			break;

		case 5: querie5(catc,catp,vendas,fact,filial);
			break;

		case 6: querie6(catc,catp,vendas,fact,filial);
			break;

		case 7: querie7(catc,catp,vendas,fact,filial);
			break;

		case 8: querie8(catc,catp,vendas,fact,filial);
			break;

		case 9: querie9(catc,catp,vendas,fact,filial);
			break;

		case 10: querie10(catc,catp,vendas,fact,filial);
			break;

		case 11:
			break;

		case 12: querie12(catc,catp,vendas,fact,filial);
			break;
			
		default:
			printf(" ESTA QUERIE NÃO EXISTE\n");
			break;
	}
	interface(catc,catp,vendas,fact,filial);

}

//QUERIE1 
void querie1 (CATCLI catCli,CATALOGO_PRODUTOS catProd,CATVENDA vendas,FATURACAO fact,FILIAL filial, int cv,int pv,int vv){

printf("\e[2J\e[H");


char* cF=malloc(sizeof(char*));
char* pF=malloc(sizeof(char*));
char* vF=malloc(sizeof(char*)); 

printf("							0.Voltar\n");
printf("\n\n");

printf("USAR FICHEIROS INICIAIS?     1/0\n");
int r;
scanf("%d",&r);

switch(r){
	case(1): 					cF="Clientes.txt";
								pF="Produtos.txt";
								vF="Vendas_1M.txt";break;
    case(0) :   			    printf("	Ficheiro de Clientes: ");
								scanf("%s",cF);
								printf("	Ficheiro de Produtos: ");
								scanf("%s",pF);
								printf("	Ficheiro de Vendas: ");
								scanf("%s",vF);break;
	default: 
			(printf ("Insira resposta válida"));break;}

			fazCatalogos(catCli,cF,catProd,pF,vendas,vF,fact,filial,cv,pv,vv);

}


void fazCatalogos(CATCLI cliente,char* clientsFile,CATALOGO_PRODUTOS produto,char* prodFile,CATVENDA vendas,char* vendasFile,FATURACAO fact,FILIAL filial,int cv,int pv,int vv){

FILE *cFile;
FILE *vFile;
FILE *pFile;
cFile= fopen(clientsFile,"r");
vFile= fopen(vendasFile,"r");
pFile= fopen(prodFile,"r");	


time_t timeClientesB;
time_t timeProdutosB;
time_t timeVendasB;
time_t timeClientesE;
time_t timeProdutosE;
time_t timeVendasE;
double difTempoCli = 0;
double difTempoProd = 0;
double difTempoVend = 0;


time(&timeClientesB);
char cli[10];
char *cliAux;
int cliTOTAL=0;
if (cFile!=NULL){
 while ((fgets(cli,MAXBUFPROD, cFile)))
 	   { cliAux= strtok(cli, "\n\r");

 	     if(validaClientes(cliAux)==1){
 	     cliente= insereCli(cliente,cliAux);
 	     cv++;
 		   }

 	     cliTOTAL++;
      }
  }
time(&timeClientesE);
difTempoCli = difftime(timeClientesE,timeClientesB);


time(&timeProdutosB);
char prod[10];
char *prodAux;
int prodTOTAL=0;
if (pFile!=NULL){
 while ((fgets(prod,MAXBUFPROD, pFile)))
 	   { prodAux= strtok(prod, "\n\r");

 	     if(validaProdutos(prodAux)==1){
 	     produto= insereProduto(produto,prodAux);
 	     pv++;
 		   }

 	     prodTOTAL++;
      }
  }
 time(&timeProdutosE);
 difTempoProd = difftime(timeProdutosE,timeProdutosB);


time(&timeVendasB);
char venda[100];
char *vendaAux;
char* t = malloc(sizeof(char*));
char* tt = malloc(sizeof(char*));
char* ttt = malloc(sizeof(char*));
int vendaTOTAL=0;
if (vFile!=NULL){
 while ((fgets(venda,MAXBUFVEN, vFile)))
 	   { vendaAux= strtok(venda, "\n\r");
 	strcpy(t,vendaAux);
 	    if(validaVendas(cliente,produto,t)==1){
 	    vendas = insereVEND(vendas,vendaAux);
 	    strcpy(tt,vendaAux);
 	    insereFaturacao(fact,vendaAux,tt);
 	    strcpy(ttt,vendaAux);
 	    insereFilial(filial,vendaAux,ttt);
 	     vv++;
 		 }
 	     vendaTOTAL++;
      }
  }
  time(&timeVendasE);
  difTempoVend = difftime(timeVendasE,timeVendasB);


  	if(cFile!=NULL){
		printf("	 _______________________________________\n");
		printf("	| 		CLIENTES 		|\n");
		printf("	| Nome do ficheiro: %18s	|\n",clientsFile);
		printf("	| Foram lidas %7d linhas.		|\n",cliTOTAL);
		printf("	| Foram validadas %7d linhas.	|\n",cv);
		printf("	| Não foram validadas %7d linhas.	|\n",cliTOTAL-cv);
		printf("	| Tempo de leitura: %9f s.	|\n",difTempoCli);
		printf("	|_______________________________________|\n");
	}
	if(pFile!=NULL){
		printf("	 _______________________________________\n");
		printf("	| 		PRODUTOS 		|\n");
		printf("	| Nome do ficheiro: %18s	|\n",prodFile);
		printf("	| Foram lidas %7d linhas.		|\n",prodTOTAL);
		printf("	| Foram validadas %7d linhas.	|\n",pv);
		printf("	| Não foram validadas %7d linhas.	|\n",prodTOTAL-pv);
		printf("	| Tempo de leitura: %9f s.	|\n",difTempoProd);
		printf("	|_______________________________________|\n");
    }

    if(vFile!=NULL){
		printf("	 _______________________________________\n");
		printf("	| 		VENDAS  		|\n");
		printf("	| Nome do ficheiro: %18s	|\n",vendasFile);
		printf("	| Foram lidas %7d linhas.		|\n",vendaTOTAL);
		printf("	| Foram validadas %7d linhas.	|\n",vv);
		printf("	| Não foram validadas %7d linhas.	|\n",vendaTOTAL-vv);
		printf("	| Tempo de leitura: %9f s.	|\n",difTempoVend);
		printf("	|_______________________________________|\n");
    }

    printf("Carregue qualquer numero para sair.");
    int u;
    scanf("%d",&u);
    if(u==0) interface(cliente, produto,vendas,fact,filial);
    else (interface(cliente,produto,vendas,fact,filial));
}


//QUERIE2
int QUERIE2 (CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial){
	printf("\e[2J\e[H");
	char l;int u;
	printf("Digite a primeira letra do produto: ");
	scanf("%c",&l);
	if(l=='0') {interface(c,catProdV,vendas,fact,filial);return 0;}
	if(l>='A' && l<='Z'){
			int indice = l - 'A';
		char** res; 
    	int n = catProdV->catProdutos[indice]->total;
		res = malloc (n * sizeof (char*));
		u=firstTransversal(catProdV->catProdutos[indice],res);
		res[u]=NULL;
		imprimeColunas(res,1,c,catProdV,vendas,fact,filial);
		return 0;
	}
	else {printf("Por favor insira uma letra de A a Z.\n"); QUERIE2(c,catProdV,vendas,fact,filial);return 0;}
}

//QUERIE3
int querie3(CATCLI catCli,CATALOGO_PRODUTOS catProd,CATVENDA vendas,FATURACAO fact,FILIAL filial){
	printf("\e[2J\e[H");
	int iMes=0;
	printf("	Introduza o mês (1-12): ");
	scanf("%d",&iMes);

	char* stringProd=malloc(sizeof(char*));
	printf("	Introduza o produto: ");
	scanf("%s",stringProd);
	
	int dim=fact->fatMES[iMes]->total;
	char** arr=malloc(dim*sizeof(char*)); 
	int i=0;
	AddToArray(fact->fatMES[iMes]->avl,arr, &i);
	//int cr=0;
	//for(cr=0;cr<dim;cr++) {printf("%s\n",arr[cr] );}


	int nVN=0,nVP=0;
	double fVN=0,fVP=0;
	if(validaProdutos(stringProd)) { 
					int i;
					VENDA venda=criaVenda();
					for(i=0;i<dim;i++){
					venda=mkVendaStruct(arr[i]);
					if(strcmp(venda->tipo,"N")==0) { nVN+=1;fVN+=venda->preco;}
					else { nVP++;fVP+=venda->preco;}
					}
	
									
									printf("	 _______________________________________\n");
		                            printf("	| 		FATURACAO 		|\n");
		                            printf("	| Faturaçao total: %18f	|\n",fVN+fVP);
		                            printf("	| Numero de vendas: %7d		|\n",nVN+nVP);
		                            printf("	| Faturaçao s/promoção: %18f|\n",fVN);
		                            printf("	| Faturaçao c/promoção: %18f|\n",fVP);
		                            printf("	| Numero de vendas s/promoção: %7d	|\n",nVN);
		                            printf("	| Numero de vendas c/promoção: %7d	|\n",nVP);
		                            printf("	|_______________________________________|\n");
		                            free(arr);
		                        }
	
	else printf("Os argumentos inseridos são inválidos");

    printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(catCli, catProd ,vendas,fact,filial);
    else (interface(catCli,catProd,vendas,fact,filial));

	return 0;
		
}

//QUERIE4
int QUERIE4 (CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact, FILIAL filial){
	printf("\e[2J\e[H");
    int u=0;
	int indice;
	char** res;
	int guarda=0;	
	int n = 0;
	n=vendas->catvendas->total;
	res = malloc (n * sizeof (char*));

	CATVENDA ardeu = inicializaVendaDiv();
    int y=0;
    transformaEmvenda(y,ardeu,vendas->catvendas->avl);

	for(indice=0;indice<26 && guarda<n;indice++){
		u=travessiaArrayAux(guarda,catProdV->catProdutos[indice]->avl,ardeu->catvendas->avl,res);
		guarda=guarda+u;
		u=0;

	
    }
	res[guarda]=NULL;
    imprimeColunas(res,1,c,catProdV,vendas,fact,filial);
    printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(c, catProdV,vendas,fact,filial);
    else (interface(c,catProdV,vendas,fact,filial));
	
    return 0;
}

//QUERIE5
int querie5(CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial){

	printf("\e[2J\e[H");
    int u=0;
	char** res;
	int guarda=0;	
	int n = 0;    int y=0;
	n=vendas->catvendas->total;
	res = malloc (n * sizeof (char*));


	CATVENDA filial1 =inicializaVendaDiv();
	CATVENDA filial2 =inicializaVendaDiv();
	CATVENDA filial3 =inicializaVendaDiv();


    transformaEmvendaCLI(y,filial1,filial->fil[1]->avl);
    transformaEmvendaCLI(y,filial2,filial->fil[2]->avl);
    transformaEmvendaCLI(y,filial3,filial->fil[3]->avl);


	u=travessiaArrayFILIAL(guarda,filial1->catvendas->avl,filial2->catvendas->avl,filial3->catvendas->avl,res);

 

	res[u]=NULL;

    imprimeColunas(res,1,c,catProdV,vendas,fact,filial);

	
	return 0;
}

//QUERIE6
int querie6(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial){
	printf("\e[2J\e[H");
    int u=0;
	int indice;
	char** res;
	char** clili;
	int guarda=0;	
	int n = 0;    int y=0;
	n=vendas->catvendas->total;
	res = malloc (n * sizeof (char*));
	clili = malloc (n * sizeof (char*));

	CATVENDA pois = inicializaVendaDiv();
    transformaEmvenda(y,pois,vendas->catvendas->avl);

	for(indice=0;indice<26 && guarda<n;indice++){
		u=travessiaArrayAux(guarda,prod->catProdutos[indice]->avl,pois->catvendas->avl,res);
		guarda=guarda+u;
		u=0;

	
  }
	res[guarda]=NULL;

	printf("Numero de Produtos que nao foram comprados: %d\n",guarda );
	free(res);
	free(pois);
	CATVENDA ardeu = inicializaVendaDiv();
    transformaEmvendaCLI(y,ardeu,vendas->catvendas->avl);


	for(indice=0,guarda =0,u=0;indice<26 && guarda<n;indice++){
	    u=travessiaArrayAux(guarda,cli->CatalogoC[indice]->avl,ardeu->catvendas->avl,clili);
	    guarda=guarda+u;
	    u=0;

	
    }
	clili[guarda]=NULL;
	printf("Numero de cliente que nao efeturam nunhuma compra: %d\n",guarda );

	free(clili);
	free(ardeu);
    printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(cli, prod,vendas,fact,filial);
    else (interface(cli,prod,vendas,fact,filial));

	return 0;
}



//QUERIE7
int querie7(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial){
	char* stringC=malloc(sizeof(char*));
	printf("	Introduza o cliente: ");
	scanf("%s",stringC);

	int dim1=fact->fatMES[1]->total;
	int nVendas1=0;
	nVendas1=prodporMes(fact->fatMES[1]->avl,stringC,dim1);
	int dim2=fact->fatMES[2]->total;
	int nVendas2=0;
	nVendas2=prodporMes(fact->fatMES[2]->avl,stringC,dim2);
	int dim3=fact->fatMES[3]->total;
	int nVendas3=0;
	nVendas3=prodporMes(fact->fatMES[3]->avl,stringC,dim3);
	int dim4=fact->fatMES[4]->total;
	int nVendas4=0;
	nVendas4=prodporMes(fact->fatMES[4]->avl,stringC,dim4);
	int dim5=fact->fatMES[5]->total;
	int nVendas5=0;
	nVendas5=prodporMes(fact->fatMES[5]->avl,stringC,dim5);
	int dim6=fact->fatMES[6]->total;
	int nVendas6=0;
	nVendas6=prodporMes(fact->fatMES[6]->avl,stringC,dim6);
	int dim7=fact->fatMES[7]->total;
	int nVendas7=0;
	nVendas7=prodporMes(fact->fatMES[7]->avl,stringC,dim7);
	int dim8=fact->fatMES[8]->total;
	int nVendas8=0;
	nVendas8=prodporMes(fact->fatMES[8]->avl,stringC,dim8);
	int dim9=fact->fatMES[9]->total;
	int nVendas9=0;
	nVendas9=prodporMes(fact->fatMES[9]->avl,stringC,dim9);
	int dim10=fact->fatMES[10]->total;
	int nVendas10=0;
	nVendas10=prodporMes(fact->fatMES[10]->avl,stringC,dim10);
	int dim11=fact->fatMES[11]->total;
	int nVendas11=0;
	nVendas11=prodporMes(fact->fatMES[11]->avl,stringC,dim11);
	int dim12=fact->fatMES[12]->total;
	int nVendas12=0;
	nVendas12=prodporMes(fact->fatMES[12]->avl,stringC,dim12);

	    printf("	 ____________________\n");
		printf("	| Número de Vendas  |\n");
		printf("	| MES 1:  %d	    |\n",nVendas1);
		printf("	| MES 2:  %d	    |\n",nVendas2);
		printf("	| MES 3:  %d	    |\n",nVendas3);
		printf("	| MES 4:  %d         |\n",nVendas4);
		printf("	| MES 5:  %d	    |\n",nVendas5);
		printf("	| MES 6:  %d         |\n",nVendas6);
		printf("	| MES 7:  %d	    |\n",nVendas7);
		printf("	| MES 8:  %d	    |\n",nVendas8);
		printf("	| MES 9:  %d	    |\n",nVendas9);
		printf("	| MES 10: %d	    |\n",nVendas10);
		printf("	| MES 11: %d	    |\n",nVendas11);
		printf("	| MES 12: %d	    |\n",nVendas12);
		printf("	|___________________|\n");

    printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(cli, prod,vendas,fact,filial);
    else (interface(cli,prod,vendas,fact,filial));
	return 0;

}


int prodporMes(Avl arv, char*stringC,int dim){
	char** arr=malloc(dim*sizeof(char*));
	int i=0,f=0;
	AddToArray(arv,arr,&f);
	int numeroV=0;
	VENDA venda=criaVenda();
	for(i=0;i<dim;i++){
	venda=mkVendaStruct(arr[i]);
	//printf("%d\n",x);

	if(strcmp(stringC,venda->cliente)==0) {numeroV++;}}
	return numeroV;
}

//QUERIE8
int querie8(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial){
	int iMes=0;
	printf("	Introduza o mês inicial (1-12): ");
	scanf("%d",&iMes);

	int fMes=0;
	printf("	Introduza o mês final (1-12): ");
	scanf("%d",&fMes);

	if(fMes<iMes && fMes>12 && fMes<1 && iMes>12 && iMes<1 ) printf("Argumentos inválidos\n");
	else{
		int roller=iMes;
		int nvCounter=0;
		float fvCounter=0;

		while(roller<=fMes){

		int nV=0;
		double fV=0;
		auxquerie8(fact,roller,&nV,&fV);
		nvCounter+=nV;
		fvCounter+=fV;
		roller++;
	}
		printf("	 _______________________________________\n");
		printf("	| 		FATURACAO 		|\n");
		printf("	| Faturaçao total: %18f	|\n",fvCounter);
		printf("	| Numero de vendas: %7d		|\n",nvCounter);
		printf("	|_______________________________________|\n");
	}

	printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(cli, prod,vendas,fact,filial);
    else (interface(cli,prod,vendas,fact,filial));
	return 0;
}


int auxquerie8(FATURACAO fact,int iMes,int *nV,double *fV){
	
		VENDA venda=criaVenda();
		int dim=fact->fatMES[iMes]->total;
		char** arr=malloc(dim*sizeof(char*)); 

			int i=0;
			AddToArray(fact->fatMES[iMes]->avl,arr, &i);
			for(i=0;i<dim;i++){
				venda=mkVendaStruct(arr[i]);
				*nV+=1;*fV+=venda->preco;}
			free(arr);
			arr=NULL;
			freeVenda(venda);
			printf("%d %f\n",*nV,*fV );


	return 0;

}




//QUERIE9

int querie9(CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial){
	printf("\e[2J\e[H");
	char* produto= malloc(sizeof(char*));
	int f;
	int x;
	printf("Introduza o produto: \n");
	scanf("%s",produto);
	printf("Introduza a filial: \n");
	scanf("%d",&f);

	int dim=filial->fil[f]->total;
	char** arr=malloc(dim*sizeof(char*));
	char** arr2=malloc(dim*sizeof(char*));
	char** arr3=malloc(dim*sizeof(char*));
	int i=0;
	AddToArray(filial->fil[f]->avl,arr,&i);

	int numCliN=0;
	int numCliP=0;

	if(validaProdutos(produto)){
		int j;
		VENDA venda = criaVenda();
		for(j=0;j<dim;j++){
			venda=mkVendaStruct(arr[j]);
			if(strcmp(venda->tipo,"N")==0){ 
				arr2[numCliN]=venda->cliente;
				numCliN++;
			}
			else{
				arr3[numCliP]=venda->cliente;
				numCliP++;

			}
		}

		printf("Escolha se quer os clientes que compraram em promoção ou em época normal (1 ou 2 respetivamente): \n");
		scanf("%d",&x);
		if(x==1){
		printf("Clientes que compraram em promoção: %d\n",numCliP);
		arr2[numCliP]=NULL;
		imprimeColunas(arr2,1,c,catProdV,vendas,fact,filial);
	}
		else if(x==2){
		printf("Clientes que compraram em época normal: %d\n",numCliN);
		arr3[numCliN]=NULL;
		imprimeColunas(arr3,1,c,catProdV,vendas,fact,filial);
	}
	else printf("Escolha inválida\n");


}
	else printf("Produto inválido.\n");

	 printf("Carregue qualquer numero para sair.");
    int u;
    scanf("%d",&u);
    if(u==0) interface(c, catProdV,vendas,fact,filial);
    else (interface(c,catProdV,vendas,fact,filial));

	return 0;
}

//QUERIE10
int querie10(CATCLI cli,CATALOGO_PRODUTOS prod, CATVENDA vendas,FATURACAO fact,FILIAL filial){
	printf("\e[2J\e[H");
	int iMes=0;
	printf("	Introduza o mês (1-12): ");
	scanf("%d",&iMes);

	char* stringCli=malloc(sizeof(char*));
	printf("	Introduza o cliente: ");
	scanf("%s",stringCli);

	int i=0;
	int dim=fact->fatMES[iMes]->total;
	char** arr=malloc(dim*sizeof(char*)); 
	AddToArray(fact->fatMES[iMes]->avl,arr, &i);
	char**arr2=malloc(dim*sizeof(char*));
	int*arr3=malloc(dim*sizeof(char*));

	if(validaClientes(stringCli) && iMes>0 && iMes<13) { 
					


					int uu=0,j=0;
					VENDA venda=criaVenda();

					while(uu<dim){
					venda=mkVendaStruct(arr[uu]);

					if(strcmp(venda->cliente,stringCli)==0) {
						arr3[j]=venda->quant;
						arr2[j]=venda->produto;
						j++;
						}
					uu++;}

					uu=0;
					ordenaArrays(arr3,arr2,j);
					
				while(uu<j) {printf("%s\n",arr2[uu]);uu++;}
	
							
		                            free(arr);
		                            free(arr2);
		              }     
	else printf("Os argumentos inseridos são inválidos");
	printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(cli, prod,vendas,fact,filial);
    else (interface(cli,prod,vendas,fact,filial));
	return 0;

}



//QUERIE12

int querie12(CATCLI c,CATALOGO_PRODUTOS catProdV,CATVENDA vendas,FATURACAO fact,FILIAL filial){
	char* cliente = malloc(sizeof(char*));
	printf("Introduza o código do cliente: ");
	scanf("%s",cliente);

	int dim=vendas->catvendas->total;
	char** arr=malloc(dim*sizeof(char*)); 
	int i=0;
	AddToArray(vendas->catvendas->avl,arr, &i);

    char** arr2=malloc(dim*sizeof(char*)); 

	int ii=0;
	int j=0;
	char* charr=malloc(sizeof(char*));
	if(validaClientes(cliente)){
		VENDA vendaa=criaVenda();
		while(ii<dim){
			strcpy(charr,arr[ii]);
			vendaa=mkVendaStruct(charr);
			//printf("Ateaqui2\n");
		if(strcmp(cliente,vendaa->cliente)){
			//printf("Ateaqui3\n");
			
			arr2[j]=arr[ii];
			j++;
		}
        ii++;
		}


		char** prood=malloc(3*sizeof(char*));
		float* precoos= malloc(3*sizeof(float));
		ii=0;
		char* ccc=malloc(sizeof(char*));
		while(ii<dim){
			strcpy(ccc,arr2[ii]);
			vendaa=mkVendaStruct(ccc);
			if(vendaa->preco>precoos[0]){   if (vendaa->preco<precoos[1]) {precoos[0]=vendaa->preco;
												  				  	    prood[0]=vendaa->produto;}
										   else if (vendaa->preco>precoos[1] && vendaa->preco>precoos[2]) {precoos[1]=vendaa->preco;
												  				  	 								   prood[1]=vendaa->produto;}
										   else {precoos[2]=vendaa->preco;
												  prood[2]=vendaa->produto;}

		}

		

	}
	printf("Produtos mais caros (ordem descrescente): %s %s %s\n", prood[2],prood[1],prood[0]);
}

	else printf("O cliente inserido é inválido.\n");
	printf("Carregue qualquer numero para sair.");
    int k;
    scanf("%d",&k);
    if(k==0) interface(c,catProdV ,vendas,fact,filial);
    else (interface(c,catProdV,vendas,fact,filial));
	return 0;
}

int ordenaArrays(int*arr3,char**arr2,int j){
	int contador,i;
	int aux;
	char* aux2=malloc(sizeof(char*));
for (contador = 1; contador < j; contador++) {
   for (i = 0; i < j- 1; i++) {
     if (arr3[i] > arr3[i + 1]) {
       aux = arr3[i];
       aux2=arr2[i];

       arr3[i] = arr3[i + 1];
       arr2[i]=arr2[i+1];
       arr3[i + 1] = aux;
       arr2[i+1]=aux2;
     }
   }
 }

return 0;

}