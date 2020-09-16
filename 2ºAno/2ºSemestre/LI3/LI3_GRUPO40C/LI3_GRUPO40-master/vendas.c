#include "vendas.h"

/* Estrutura onde é guardada toda a informação de cada linha do ficheiro Vendas. */
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
    
struct catalogoProdutos {
  MY_AVL catProdutos[ABECEDARIO];
};

struct catCli{
  MY_AVL  CatalogoC[TAM];};


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


/* Inicializa uma estrutura Venda. */
VENDA criaVenda(){
  VENDA nova = malloc(sizeof(struct venda));
  nova->cliente=NULL;
  nova->produto=NULL;
  nova->tipo=NULL;
  nova->vendaCompleta=NULL;
  nova->preco=0;
  nova->quant=0;
  nova->filial=0;
  nova->mes=0;

  return nova;
}

CATVENDA inicializaVendaDiv(){
  CATVENDA vendD= malloc(sizeof(struct catVenda));
  vendD->catvendas=initMyAvl();
  return vendD;
}

void freeVenda(VENDA nada){
  if(nada){
    ClienteFree(nada->cliente);
    ProdutoFree(nada->produto);
    free(nada);
  } 
}

char** tokenizeLinhaVendaDyn(char* vendaRaw) {
    int index = 0;
    char* venda = vendaRaw;
    char** campos = malloc(CAMPOSVENDA * sizeof(char*)); // dimensão vertical
    char* token = strtok(venda," ");  
    while(!(token == NULL) && index<7) {
        campos[index] = strdup(token);
        // printf(" %s\n", token);
        token = strtok(NULL," ");
        index++;
    }
    return campos;
}


VENDA mkVendaStruct(char* linhaVenda) {
    char** campos;
    VENDA vendaAux = criaVenda();
      char* save=linhaVenda;
      char* s=linhaVenda;
  


    campos = tokenizeLinhaVendaDyn(s);
    
    
    vendaAux -> produto = strdup(campos[0]);
    vendaAux -> preco = atof(campos[1]);
    vendaAux -> quant = atoi(campos[2]);
    vendaAux -> tipo = (campos[3]);
    vendaAux -> cliente = strdup(campos[4]);
    vendaAux -> mes = atoi(campos[5]);
    vendaAux ->filial = atoi(campos[6]); 
    vendaAux -> vendaCompleta = (save);
    return vendaAux;   
}

CATVENDA insereVEND (CATVENDA catV, char* linha){
  catV->catvendas = insertMyAvl(catV->catvendas,linha,NULL,1);
  return catV;
}



int validaVendas( CATCLI clientes,CATALOGO_PRODUTOS produtos, char* vendida) {
  char*vend=vendida;
   VENDA venda = mkVendaStruct(vend);
   return existeProduto(produtos,venda->produto) && jaExisteC(clientes,venda->cliente) && 
            venda->mes >= 1 && venda->mes <= 12 && venda->quant> 0 && venda->quant <= 200 && venda->preco >= 0 && 
            venda->preco <= 999.99 && venda->filial > 0 && venda->filial < 4 && (strcmp(venda->tipo,"N")==0 || strcmp(venda->tipo,"P")==0);
}

/* Actualiza uma estrutura SALES com os parâmetros corrtipoondentes. */
VENDA atualizaVendas(VENDA venda,char* cliente,char* produto,int mes,int filial,int quant,double preco,char* tipo){
  venda->cliente = cliente;
  venda->produto = produto;
  venda->mes = mes;
  venda->filial = filial;
  venda->quant = quant;
  venda->preco = preco;
  venda->tipo = tipo;
  return venda; 
}


//Querie 4 : vê se um produto nao existe,conta os que não existem e mete os num array
char* procuraProduto(CATALOGO_PRODUTOS catProd,VENDA v,int tam){
  int count=0,i;
  char* vend[tam];
  for(i=0;i<26;i++){
    if(existMyAvl(catProd->catProdutos[i],v->produto)==0){
      vend[count]=v->produto;
      count++;
     }
  }
  return *vend;
}



int transformaEmvenda(int a,CATVENDA ardeu,Avl v){
  int conta=0;
  if(v!=NULL){
    if(v->left!=NULL) transformaEmvenda(a,ardeu,v->left);
    char* transforma = v->code;
    if(transforma!=NULL && strlen(transforma)>10){
          VENDA vend = criaVenda();
          vend=mkVendaStruct(transforma);
          ardeu=insereVEND(ardeu,vend->produto);

    if(v->right!=NULL) transformaEmvenda(a+conta,ardeu,v->right);
  }
}
 return conta;
}


int transformaEmvendaCLI(int a,CATVENDA ardeu,Avl v){
  int conta=0;
  if(v!=NULL){
    if(v->left!=NULL) transformaEmvendaCLI(a,ardeu,v->left);
    char* transforma = v->code;
    if(transforma!=NULL && strlen(transforma)>10){
          VENDA vend = criaVenda();
          vend=mkVendaStruct(transforma);
          ardeu=insereVEND(ardeu,vend->cliente);

    if(v->right!=NULL) transformaEmvendaCLI(a+conta,ardeu,v->right);
  }
}
 return conta;
}



int  travessia (MY_AVL nod,Avl v){
  int count=0;
  if(v!=NULL){
     if(v->left!=NULL) count+=travessia(nod,v->left);
     if(v->right!=NULL) count+=travessia(nod, v->right);
          if(existMyAvl(nod,v->code)==0) count=1;
  }
  return count;
}

int travessiaAux (MY_AVL i,MY_AVL v){
  int p=0;
  p=travessia(i,v->avl);
  return p;
}


int treeTraversal(int a, Avl nod, char* arr[]) {
    int count = 0;

    if (nod!=NULL) {
        count += treeTraversal(a, nod->left, arr);
        arr[a + count++] = nod->code;
        count += treeTraversal(a + count, nod->right, arr);
    }

    return count;
}

int firstTransversal(MY_AVL e,char* array[]){
  int i=0;int conta=0;
  conta=treeTraversal(i,e->avl,array);
  return conta;
  
}

int travessiaArrayAux(int u,Avl e,Avl nod, char* arr[]){
    int count = 0;
    if (nod!=NULL && e!=NULL  ) {

        if(e->left!=NULL) count += travessiaArrayAux(u,e->left, nod, arr);
        if(existAvl(nod,e->code)==0) {arr[u + count++] = e->code;}  
        if(e->right!=NULL)count += travessiaArrayAux(u + count,e->right, nod, arr);
    }

    return count;
}