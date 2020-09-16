#include "CatProd.h"

struct catalogoProdutos {
	MY_AVL catProdutos[ABECEDARIO];
};					


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

/* Cria o Catálogo de Produtos. */
CATALOGO_PRODUTOS iniciaProdutos(){
	
	int i=0;
	CATALOGO_PRODUTOS catalogo = malloc (sizeof(struct catalogoProdutos));
	while(i<ABECEDARIO) {
		catalogo->catProdutos[i] = initMyAvl();
		i++;
	}
	return catalogo;
}

void ProdutoFree(char produto[]){
	free(produto);
}

/*
void freeCatalogoProds(CATALOGO_PRODUTOS catalogo){
	int i=0;
	if(catalogo){
		while(i<ABECEDARIO) {
			removeArv(catalogo->catProdutos[i]);
			catalogo->catProdutos[i] = NULL;
			i++;
		}
		free(catalogo);
	}
}
*/


/* Insere um produto no respectivo catálogo. */
CATALOGO_PRODUTOS insereProduto(CATALOGO_PRODUTOS catalogo, char* prod){

	int indice = prod[0]-'A';
	catalogo->catProdutos[indice] = insertMyAvl(catalogo->catProdutos[indice],prod,NULL,1);
	return catalogo;
}



/* Verifica se um produto existe no catálogo.*/
int existeProduto(CATALOGO_PRODUTOS catalogo, char prod[]){
	int res=0,indice;
	if(prod[0] >= 'A' && prod[0] <= 'Z' ){
		indice = prod[0]-'A';
		 if(existMyAvl(catalogo->catProdutos[indice],prod)!=0) res=1;
	} 
	return res;
}



/* Conta quantos produtos existem começados por uma determinada letra no catálogo.*/
int contaProdutosL(CATALOGO_PRODUTOS catalogo,char letra){
	int res=0;
	int indice = letra - 'A';
	 res=totalElements(catalogo->catProdutos[indice]);
	return res;
} 




/* Conta o total de produtos no catálogo. */
int numeroProdutos(CATALOGO_PRODUTOS catalogo){
	char tim='A'; int res=0;
	while( tim <= 'Z'){
		res+=contaProdutosL(catalogo,tim);
		tim++;
	}
	return res;
}



/* Testa se um produto tem a estrutura correta.*/
int validaProdutos (char* produto){
  int i;
  int r=1;
  if(strlen(produto)!=6) r=0; //são 7 caracteres
  for(i=0; i<2; i++){
      if( !(produto[i]>='A') || !(produto[i]<='Z') )   r=0; 
    } 
  for(i=2;i<6;i++){ 
      if( !(isdigit(produto[i])) || !( produto[2]>='1' && produto[2]<='9') ) r=0;
  }
  return r;
}