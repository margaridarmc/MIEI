#include <stdbool.h>
#ifndef _TRABALHO
#define _TRABALHO

#define ID 1000
#define USER 1001
#define DATA 1002
#define TIME 1003
#define TEXTCOMENTARIO 1004
#define STARTREPLIES 1005
#define NUMBERREPLIES 1006
#define ENDREPLIES 1007
#define ERRO -1

#endif

typedef struct comentario *Coments;
typedef struct lligada  Cel, *LComents;
LComents startUP();
Coments new_Comentario(char* i,char* a,char* b,char* c, char* d,bool has);
void print_Comentario(Coments h);
LComents daReplies(Coments a);
void adicionaRep(Coments c, LComents s,int n);
void addReplies(LComents *l, Coments c);
void print_Replies(Coments h);
void print_Finalissimo(LComents a);
void makeJSONFile (LComents a);
Coments alteraReplie(Coments c,LComents rep);
LComents reverseL (LComents l);