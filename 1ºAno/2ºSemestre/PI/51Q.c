typedef struct  lligada {
	int valor;
	struct lligada *prox;
} *LInt;

//1
int length (LInt l){
	int i;
	for(i=0;l!=NULL;i++)
		l=l->prox;
	return i;
}

//2
void freeL (LInt l){
	LInt ant;
	while(l!=NULL){
		ant = l;
		l=l->prox;
		free(ant);
	}
}

//3
void imprimeL (LInt l){
	while(l!=NULL){
		printf("%d\n",l->valor);
		l=l->prox;
	}
}

//4
LInt reverseL (LInt l){
	LInt ant=NULL,aux=NULL;
	while(l!=NULL){
		aux = l;
		l=l->prox;
		aux->prox=ant;
		ant=aux;
	}
	return aux;
}

//5
void insertOrd (LInt *l,int x){
	LInt new;
	while((*l)!=NULL && (*l)->valor<x)
		l=&((*l)->prox);
		new = (LInt) malloc(sizeof(struct lligada));
		new->valor=x;
		new->prox = *l;
	*l=new;
}

//6
int removeOneOrd (LInt *l,int x){
	int r=1;
	LInt p;
	while((*l)!=NULL){
		if((*l)->valor==x){
			p=(*l)->prox;
			free(*l);
			*l=p;
			r=0;
		}
		else l=&((*l)->prox);
	}
	return r;
}

//7
void merge(LInt *r,LInt a,LInt b){
	while(a || b){
		if((!a) || b && a->valor>b->valor){
			*r=b;
			r=&(b->prox);
			b=b->prox;
		}
		else{
		    *r=a;
			r=&(a->prox);
			a=a->prox;
			
		}
	}
}

//8
void splitQS (LInt l,int x,LInt *mx,LInt *Mx){
    while(l!=NULL){
	if(l->valor<x){
	  *mx=l;
	  mx=&((*mx)->prox);
	} 
	else{
	    *Mx=l;
	    Mx=&((*Mx)->prox);
	}
	l=l->prox;
    *mx=0;
    *Mx=0;
    }
}

//9
LInt parteAmeio (LInt *l){
	LInt y=*l, *p=l;
	int n=0,j;
		n=(length *l)/2;
		for(j=0;j<n;j++)
			p=&((*l)->prox);
			*l=*p;
			*p=NULL;
			if(n==0){
				*l=y;
				return NULL;
		    }
		    else return y;

}

//10
int removeAll (LInt *l, int x){
	int r=0;
	LInt aux;
	while(*l!=NULL){
		if((*l)->valor == x){
			r++;
			aux=((*l)->prox);
			free(*l);
			(*l)=aux;
		}
		else l=&((*l)->prox);
	}
	return r;
}

//11
int removeDups (LInt *l){
	int i=0;
	while(*l!=NULL){
		i=+removeAll(&((*l)->prox),(*l)->valor);
		l=&((*l)->prox);
	}
	return i;
}

//12
int removeMaiorL(LInt *l){
	int maior;
	LInt *p,t;
	for(p=l;*p;p=&((*p)->prox))
		if((*l)->valor<(*p)->valor)
			l=p;
		maior=(*l)->valor;
		t=(*l)->prox;
		free(*l);
		*l=t;
		return maior;
}

//13
void init (LInt *l){
	LInt aux;
	while(*l){
		if((*l)->prox==NULL){
			aux=NULL;
			free(*l);
			(*l)=aux;

		}
		else l=&((*l)->prox);
	}
}

//ou
void init (LInt *l){
    while((*l)!=NULL){
        if((*l)->prox==NULL){
            free(*l);
            *l=NULL;
        }
        else l=&((*l)->prox);
    }
}

//14
void appendL (LInt *l, int x){
    LInt new,pt;
    	new=malloc(sizeof(struct lligada));
		new->valor=x;
		new->prox=NULL;
	if((*l)== NULL){
		*l=new;
	}
	else {
		for(pt=*l;pt->prox!=NULL;pt=pt->prox);
	    pt->prox=new;
    }
}

//15
void concatL (LInt *a,LInt b){
	while(*a!=NULL)
		a=(&(*a)->prox);
	*a=b;
}

//16
LInt cloneL (LInt l){
	LInt new;
	if(l==NULL) new=NULL;
	while(l!=NULL){
	new = malloc(sizeof(struct lligada));
	new->valor = l->valor;
	new->prox = cloneL(l->prox);
    }
    return new;
}

//17
LInt cloneRev (LInt l){
	LInt new, ant=NULL;
	if(l==NULL) new = NULL;
	while(l!=NULL){
	new = malloc(sizeof(struct lligada));
	new->valor = l->valor;
	new->prox = ant;
	ant = new;
	l=l->prox;
    }
    return new;
}

//18
int maximo(LInt l){
	int maximo;
	for(maximo=l->valor;l!=NULL;l=l->prox)
		if(l->valor>maximo) maximo = l->valor;
	return maximo;
}

//19

int take (int n,LInt *l){
	int r;
	LInt p;
	for(r=0;*l && r<n;r++){
	l=&((*l)->prox);}
	while(*l){
	    p=(*l)->prox;
	    free(*l);
	    *l=p;
	}
	return r;
}

//20
int drop (int n,LInt *l){
	int r;
	LInt p;
		for(r=0; *l && r<n;r++){
			p=(*l)->prox;
			free(*l);
			*l=p;
		}
	return r;
}

//21
LInt NForward (LInt l, int N){
    LInt new = NULL;
    
    int r;
    for(r=0;r<N && l;r++)
    l=l->prox;
    
    return (l);
}

//22
int listToArray (LInt l,int v[],int N){
	int i;
		for(i=0;i<N && l!=NULL ;i++){
			v[i]=l->valor;
			l=l->prox;
	}
	return i;
}

//23
LInt arrayToList (int v[],int N){
	LInt new;
	int i;
	if(N>0){
		new=malloc(sizeof(struct lligada));
		new->valor = v[0];
		new->prox = arrayToList(v+1,N-1);
	}
	else new=NULL;
	return new;
}

//24
LInt somasAcL (LInt l){
	LInt r, *new=&r;
	int i=0;
	while(l!=NULL){
		*new=malloc(sizeof(struct lligada));
		i+=l->valor;
		(*new)->valor =i;
		l=l->prox; 
		new=&((*new)->prox);
	}
	*new=NULL;
	return r;
}


//25
void remreps (LInt l){
	LInt p;
	while(l && l->prox){
		p=l->prox;
		if(l->valor==p->valor){
			l->prox=p->prox;
			free(p);
			p=l->prox;
		}
		else l=l->prox;
	}
}

//28
int altura (ABin a){
    int r=0;
    if(a==NULL) r=0;
    else{
        r=1+max(altura(a->esq),altura(a->dir));
    }
	return r;
}

int max (int a,int b){
    if(a>b) return a;
    else return b;
}

//29
ABin cloneAB(ABin a){
	ABin new;
	if(a==NULL) new=NULL;
	else{
		new=malloc(sizeof(struct nodo));
		new->valor = a->valor;
		new->dir=cloneAB(a->dir);
		new->esq=cloneAB(a->esq);
	}
	return new;
}

//ou

ABin cloneAB (ABin a) {
    ABin new;
    if(a==NULL) new = NULL;
    else{
        new = malloc(sizeof(struct nodo));
        new->valor = a->valor;
        new->dir = a->dir;
        new->esq = a->esq;
    }
}

//30
void mirror (ABin *a) {
    ABin p;
    if(*a!=NULL){
        p = (*a)->dir;
        (*a)->dir = (*a)->esq;
        (*a)->esq = p;
        
        mirror(&((*a)->esq));
        mirror(&((*a)->dir));
    }
}

//31
void inorder (ABin a,LInt *l){
	LInt p;
	if(a){
		inorder(a->dir,l);
		p=*l;
		*l=malloc(sizeof(struct lligada));
		(*l)->valor = a->valor;
		(*l)->prox=p;
        inorder(a->esq,l);
	}
}

// ou
void inorder (ABin a, LInt * l) {
    *l = NULL;

    if (a) {
        inorder(a->esq, l);
        while (*l) l = &(*l)->prox;

        *l = malloc(sizeof(struct lligada));
        (*l)->valor = a->valor;
        (*l)->prox = NULL;

        inorder (a->dir, &(*l)->prox);
    }
}

//32
void preorder (ABin a, LInt * l) {
    *l = NULL;

    if (a) {
        *l = malloc(sizeof(struct lligada));
        (*l)->valor = a->valor;
        (*l)->prox = NULL;

        preorder(a->esq, &(*l)->prox);
        while (*l) l = &(*l)->prox;
        preorder (a->dir, l);
    }
}

// 33
void posorder (ABin a, LInt * l) {
    *l = NULL;
    if (a) {
        posorder(a->esq, l);
        while (*l) l = &(*l)->prox;
        posorder (a->dir, l);
        while (*l) l = &(*l)->prox;
        *l = malloc(sizeof(struct lligada));
        (*l)->valor = a->valor;
        (*l)->prox = NULL;
    }
}

//34
int depth (ABin a, int x) {
    int m=-1,a1,a2;
    if(a){
        if(a->valor==x) m=1;
        else{
            a1=depth(a->dir,x);
            a2=depth(a->esq,x);
            if(a1>0 && a2>0) m=1+(a1>a2?a2:a1);
            else if(a1>0 && a2<0) m=1+a1;
            else if(a1<0 && a2>0) m=1+a2;
        }
    }
    return m;
}

//35
int freeAB (ABin a){
	int r=0;
	if(a==NULL) r=0;
	else{
		r=1+freeAB(a->dir)+freeAB(a->esq);
		free(a);
	}
	return r;
}

//36
 int pruneAB (ABin *a, int l) {
     int r=0;
     if(*a){
         r+=pruneAB(&(*a)->esq,l-1);
         r+=pruneAB(&(*a)->dir,l-1);
         if(l<=0){
             free(*a);
             *a=0;
             r++;
         }
     }
     return r;
 }

 //37
 int iguaisAB (ABin a, ABin b) {
    int res = 1;

    if (a && b)
        res = a->valor == b->valor && iguaisAB(a->esq, b->esq) && iguaisAB(a->dir, b->dir);
    else res = !a && !b;
    return res;
}

//38
LInt nivelL (ABin a, int n) {
    LInt l,aux;
    if(a==NULL) l=NULL;
    else{
        if(n==1){
            l=malloc(sizeof(struct lligada));
            l->valor=a->valor;
            l->prox=NULL;
        }
        else{
            aux =l=nivelL(a->esq,n-1);
            while(aux && aux->prox) aux = aux->prox;
            if(aux) aux->prox=nivelL(a->dir,n-1);
            else l =nivelL(a->dir,n-1);
        }
    }
    return l;
}

//39
int nivelV(ABin a,int n,int v[]){
	int r=0;
	if(a){
		if(n!=1){
			r+=nivelV(a->esq,n-1,v);
			r+=nivelV(a->dir,n-1,v+r);
		}
		else v[r++]=a->valor;
	}
	return r;
}

//40
int dumpAbin(ABin a,int v[],int N){
	int r=0;
	if(a){
		if(N>0){
			dumpAbin(a->esq,v,N-1);
			v[r++]=a->valor;
			dumpAbin(a->dir,v,N-r);

		}
	}
	return r;
}

//41
ABin somasAcA (ABin a){
	ABin new = NULL;
	if(a){
		new = malloc(sizeof(struct nodo));
		new->valor = a->valor;
		new->esq = somasAcA(a->esq);
		new->dir = somasAcA(a->dir);
		if(new->esq) new->valor = new->esq->valor;
		if(new->dir) new->valor = new->dir->valor;
	}
	return new;
}

//42
int contaFolhas (ABin a) {
    int r=0;
    if(a){
        if(!a->esq && !a->dir) r=1;
        else r = contaFolhas(a->dir)+contaFolhas(a->esq);
    }
    return r;
}

//43
ABin cloneMirror (ABin a) {
    ABin new;
    if(a==NULL) new =NULL;
    else{
        new = malloc(sizeof(struct nodo));
        new->valor = a->valor;
        new->dir = cloneMirror(a->esq);
        new->esq = cloneMirror(a->dir);
    }
    return new;
}

//47
int maiorAB (ABin a) {
        while(a->dir) a = a->dir;
        return a->valor;
}

//48
void removeMaiorA (ABin *a) {
    ABin l;
    if(a){
    while((*a)->dir) 
    a = &((*a)->dir);
    l=(*a)->esq;
    free(*a);
    *a=l;
    }
}

//49
int quantosMaiores (ABin a, int x) {
    int r = 0;

    if (a)
        if (a->valor > x)
            r+= 1 + quantosMaiores(a->esq, x) + quantosMaiores(a->dir, x);
        else r += quantosMaiores(a->dir,x);

    return r;
}