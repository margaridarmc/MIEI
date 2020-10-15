%{
  #include "trabalho.h"
  #include "string.h"
  #include <stdbool.h>
  #include <stdlib.h>
  #include "cJSON.h"
 int prox_simb;

%}

%%

"<li"[^>]*> return ID;
"<a href"[^>]*> return USER;
"<a class="[^<]*<\/a>   return DATA; 
"<p>"[^>]*<\/p> return TEXTCOMENTARIO;
"<ol"[^>]*> return STARTREPLIES;
"<\/ol>"      return ENDREPLIES;
. ; 
%%

extern int yylex();


int yywrap(){}


//Função que retira espaços a mais.
char *trim(char *str)
{
  char *end;
  while(isspace((unsigned char)*str)) str++;
  if(*str == 0) 
    return str;
    end = str + strlen(str) - 1;
  while(end > str && isspace((unsigned char)*end)) end--;
  end[1] = '\0';
  return str;
}



int main(){

LComents lFinal = startUP();

LComents p;
Coments comentarioGuarda;
  char* id;
  char* user;
  char* datatime;
  char* txtcomentario;
  bool hasreplies = false;
  char* endreplies;
  bool isreply = false;
  Coments com;
  int simbSeguinte=0;
  int count=0;
  FILE *fp;
  fp = fopen("Publico_extraction_portuguese_comments_4.txt","r"); 
  yyin = fp; 
  Coments c;
  int r=0;
  int countReplies=0;


 while(prox_simb = yylex()){

    char* token=" ";
    int len=0;
    hasreplies = false;
    switch(prox_simb){

        case ID: id = strdup(yytext);
                 len= strlen(id);
                 id[len-2] = '\0'; 
                 token=id;
                 id = token+37;
                 break;

        case USER: user=strdup(yytext);
                   len= strlen(user);
                   user[len-17] = '\0'; 
                   token=user;
                   user = token+28;
                   break;

        case DATA: datatime=strdup(yytext);
                   len = strlen(datatime);
                   datatime[len-4] = '\0';
                   token=datatime;
                   datatime = token+30;
                   break;

        case TEXTCOMENTARIO:
                txtcomentario=strdup(yytext);
                int len = strlen(txtcomentario);
                txtcomentario[len-4] = '\0';
                token = txtcomentario+3;
                txtcomentario=trim(token);
                break;

        case STARTREPLIES:
                //hasreplies = true;
                break;

        case ENDREPLIES:
                isreply = false;
                

              p = daReplies(comentarioGuarda);                     //p é a lista com todos as replies do comentarioGuarda(comentário principal)
              addReplies(&p,com);                                  // Adiciona a replie encontrada à lista de replies
              adicionaRep(comentarioGuarda,p,countReplies);        //Adiciona uma lista de replies ao comentário principal
              LComents aux=reverseL(daReplies(comentarioGuarda));  // faz o reverse da lista de comentários
              Coments final= alteraReplie(comentarioGuarda,aux);   // Adiciona a lista corretamente ordenada ao comentarioGuarda

                addReplies(&lFinal,final); //Adiciona o comentário com as respetivas replies à lista final de comentários
               countReplies=0;
                break;
      }

    simbSeguinte = prox_simb;

    if(count==86) break;

    //Quando estamos no comentário que é uma replie
    if(prox_simb == 1004 && isreply == true){  
              countReplies++;            
              com = new_Comentario(id,user,datatime,datatime,txtcomentario,false);
              p = daReplies(comentarioGuarda);
              addReplies(&p,com);
              adicionaRep(comentarioGuarda,p,countReplies);     
              count++;
    }

    //Quando estamos no comentário principal e o próximo comentário é uma replie
    else if(prox_simb==1004 && (simbSeguinte=yylex())==1005) {
              comentarioGuarda = new_Comentario(id,user,datatime,datatime,txtcomentario,true);
              isreply = true;
              count++;
    }

    //Quando o comentário não tem replies cai neste caso
    else if(prox_simb == 1004) {
              com = new_Comentario(id,user,datatime,datatime,txtcomentario,false);
              addReplies(&lFinal,com);
              count++;
    }
}

//print_Finalissimo(lFinal);
LComents new=reverseL(lFinal);
makeJSONFile(new);

return 0;

}