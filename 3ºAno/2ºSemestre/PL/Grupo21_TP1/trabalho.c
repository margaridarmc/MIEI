#include "trabalho.h"
#include "cJSON.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "string.h"

//Struct que armazena todas as informações necessárias de um comentário.
typedef struct comentario {
    char* id;   
    char* user;
    char* date;
    char* timestamp;
    char* commentText;
    bool hasReplies;
    int numberOfReplies;
    LComents replies;           
}*Coments;

//Struct que armazena comentários.
typedef struct lligada{
    Coments com;
    struct lligada *prox;
}Cel,*LComents;

//inicialização de LComents
int count=1;
LComents startUP(){
    LComents res = (LComents)malloc(sizeof (struct lligada));
    return res;
}

//Função que cria um novo comentário.
Coments new_Comentario(char* i,char* a,char* b,char*d ,char* c,bool has) {

   
    char* data=b;
    int len= strlen(data);
    data[len-6] = '\0'; 

    
    Coments h = (Coments)malloc(sizeof(struct comentario));
    h -> id = i;
    h -> user = a;
    h -> date = b;
    h->timestamp = d+11;
    h -> commentText = c;
    h -> hasReplies = has;
    h -> numberOfReplies = 0;
    h -> replies = (LComents)calloc((h -> numberOfReplies),sizeof(struct lligada));

    return h;
}

//Função que retorna as replies de um dado comentário.
LComents daReplies(Coments a){
    return a->replies;
}

//Função que adiciona replies a um dado comentário.
void adicionaRep(Coments c, LComents s,int n){
    c->replies = s;
    c->numberOfReplies=n;
}

//Função que adiciona um comentário (replie) à lista de replies.
Coments alteraReplie(Coments c,LComents rep){
    Coments new=new_Comentario(c->id,c->user,c->date,c->timestamp,c->commentText,c->hasReplies);
    adicionaRep(new,rep,c->numberOfReplies);
   return new;
}

//Função que adiciona replies alocando memória.
void addReplies(LComents *s, Coments c){
    Cel *new;
    new = (LComents)malloc(sizeof (struct lligada));
    new->com = c;
    new->prox = *s;
    *s=new;
}

//Função que inverte uma lista de comentários.
LComents reverseL (LComents l){
    LComents r, a=NULL;
    Coments c=NULL;
    while(l->prox!=NULL){
      r = (LComents) malloc (sizeof (struct lligada));
      r->com=l->com;
      r->prox=a;
      l = l->prox;
      a=r;
    }


    return r;
}

/*
  Funções auxiliares
*/

//Função que imprime um comentário
void print_Comentario(Coments h) {
    printf("Coméntario nº: %d \n",count);
    printf("ID : %s\n",h->id );
    printf("USER : %s\n",h->user );
    printf("DATE : %s\n",h->date );
    printf("TIMESTAMP : %s\n",h->timestamp );
    printf("COMENTARIO : %s\n",h->commentText );
    printf("TEM REPLIES : %d\n",h->hasReplies );
    printf("NUMERO DE REPLIES : %d\n",h->numberOfReplies );
    count++;
}

//Função que imprime replies de um determinado comentário
void print_Replies(Coments h){
    LComents l= h->replies;

    while(l->prox!=NULL){
        printf("REPLY:\n");
        print_Comentario(l->com);
        printf("\n\n\n");
        l=l->prox;}
}

//Função que imprime todos os comentários e as respetivas replies
void print_Finalissimo(LComents a){
    LComents b = a;
    while(b->prox!=NULL){
        printf("\n\n\n\n");        
        print_Replies(b->com);
        printf("\n\n\n");
        print_Comentario(b->com);
        b=b->prox;
    }
}

/*
  Funções para a conversão das structs em JSON
*/


cJSON* makeReplies (Coments c){

    LComents l= c->replies;
    cJSON *replies,*comReplie;
    Coments c2;
    replies= cJSON_CreateArray();
     while(l->prox!=NULL){
      cJSON_AddItemToArray(replies, comReplie = cJSON_CreateObject());

       c2= l->com;
       cJSON_AddItemToObject(comReplie, "id ", cJSON_CreateString(c2->id));
       cJSON_AddItemToObject(comReplie, "user ", cJSON_CreateString(c2->user));
       cJSON_AddItemToObject(comReplie, "date ", cJSON_CreateString(c2->date));
       cJSON_AddItemToObject(comReplie, "timestamp", cJSON_CreateString(c2->timestamp));
       cJSON_AddItemToObject(comReplie, "commentText ", cJSON_CreateString(c2->commentText));
       cJSON_AddItemToObject(comReplie, "hasReplies", cJSON_CreateBool(c2->hasReplies));
       cJSON_AddItemToObject(comReplie, "numberOfReplies", cJSON_CreateNumber(c2->numberOfReplies));
        l=l->prox;
    }
    return replies;

}

  void makeJSONFile (LComents a){

    FILE * fPtr;
    fPtr = fopen("data.txt", "w");
    char *out;

   Coments c;
   
   cJSON *root, *commentThread , *comment,*comReplie,*replies;

   root = cJSON_CreateObject();
   commentThread  = cJSON_CreateArray();
   

   cJSON_AddItemToObject(root, "commentThread", commentThread);
   
   LComents b = a;

    while(b->prox!=NULL){
       cJSON_AddItemToArray(commentThread, comment = cJSON_CreateObject());
       c= b->com;
       cJSON_AddItemToObject(comment, "id ", cJSON_CreateString(c->id));
       cJSON_AddItemToObject(comment, "user ", cJSON_CreateString(c->user));
       cJSON_AddItemToObject(comment, "date ", cJSON_CreateString(c->date));
       cJSON_AddItemToObject(comment, "timestamp", cJSON_CreateString(c->timestamp));
       cJSON_AddItemToObject(comment, "commentText ", cJSON_CreateString(c->commentText));
       cJSON_AddItemToObject(comment, "hasReplies", cJSON_CreateBool(c->hasReplies));
       cJSON_AddItemToObject(comment, "numberOfReplies", cJSON_CreateNumber(c->numberOfReplies));
       cJSON_AddItemToObject(comment, "replies", makeReplies(c));
        
    
    
   

        b=b->prox;
    }

   out = cJSON_Print(root);
 
   fputs(out, fPtr);
   fclose(fPtr); 
   free(out);
   cJSON_Delete(root);
   }



