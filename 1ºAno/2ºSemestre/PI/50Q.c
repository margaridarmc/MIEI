//1
int maior()
{
    int read,maior;
    scanf("%d",&read);
    maior = read;
    while (read != 0)
    {
        if (read > maior)
            maior = read;
        scanf("%d",&read);
    }
    printf("%d\n",maior);
    return 0;
}

//2
int media()
{
    int read,soma,n;
    float media;
    scanf("%d",&read);
    soma = 0;
    n = 0;
    while (read != 0)
    {
        soma += read;
        n++;
        scanf("%d",&read);
    }
    media = (float) soma/n;
    printf("%f\n",media);
    return 0;
}

//3
int sndM()
{
    int read,fst,snd;
    scanf("%d",&read);
    fst = snd = read;
    while (read != 0)
    {
        if(read > fst)
        {
            snd = fst;
            fst = read;
        }

        else if (read > snd || snd == fst)
            snd = read;

        scanf("%d",&read);
    }
    printf("%d\n",snd);
    return 0;
}


//4
int bitsUm (int x){
    int r=0;
    while (x>0){
        if (x%2 == 1) {
            r++;
            x/=2;
        }
        else (x/=2);
    }
    return r;
}

//5
int trailingZ (unsigned int n) {
    int r=0;
    while(n>0){
        if(n%2==0){
            r++;
            n/=2;
        }
        else{ break;}
    }
    return r;
}

//6
int qDig (int n) {
    int r = 0;
    while (n>0){
        r++;
        n/=10;
    }
    return r;
}

//7
char *mystrcat(char s1[], char s2[]) {
    int i=0;
    int j=0;
    while (s1[i] != '\0') i++;
    while (s2[j] != '\0'){
        s1[i]=s2[j];
        j++;
        i++;
        
    }
    s1[i] = '\0';
    return s1;
}

//ou
char *mystrcat(char s1[], char s2[]) {
    int i,j;
    for(i=0;s1[i]!='\0';i++);
    for(j=0;s2[j]!='\0';j++){
        s1[i]=s2[j];
        i++;
    }
    s1[i]='\0';
    return s1;
}

//8 
char *mystrcpy(char * dest,char source[]) {
    int i;
    for(i=0;source[i]!='\0';i++){
    dest[i]=source[i];
    }
    dest[i]='\0';
    return dest;
}

//9
int *strcmp (char s1[], char s2[]){
    while(*s1 == *s2 && *s1!='\0'){
        s1++;
        s2++;
    }
    return (*s1-*s2);
}

//10
char *strstr (char s1[],char s2[]){
    int i,j=0;
    for(i=0;s1[i]!='\0' && s2[j]!='\0';i++)
        if(s1[i]==s2[j]) j++; else j=0;
        if(s2[j]=='\0') return s1+(i-j);
        else return NULL;
}

//11
void strev (char s[]){
  int i=0,j,tmp;
  j= strlen(s)-1;
  while(i<j){
    tmp=s[i];
    s[i]=s[j];
    s[j]=tmp;
    i++;j--;
  }

}

//12
void strnoV (char t[]){
    int i,w=0;
    for(i=0;t[i]!='\0';i++){
        if(!(t[i]=='A' || t[i]=='E' || t[i]=='I'||t[i]=='O'||t[i]=='U'||t[i]=='a'||t[i]=='e'||t[i]=='i'||t[i]=='o'||t[i]=='u')){
            t[w++]=t[i];
        }
    }
    t[w]='\0';
}

//13
void trunc (char t[],int n){
    int r,w=0,c=0;
    for(r=0;t[r]!='\0';r++){
        if(t[r]==' ') c=0;
        else c++;
        if(c<=n){
            t[w]=t[r];
            w++;
        }
    }
    t[w]='\0';
}

//14
int conta (char s[],char x){
    int i,r=0;
    for(i=0;s[i]!='\0';i++){
        if(s[i]==x) r++;
    }
    return r;
}

char charMaisfreq (char s[]){
    int i,x,f=0;
    char r;
    for(i=0;s[i]!='\0';i++){
        x = conta (s,s[i]);
        if(x>f){
            r = s[i];
            f = x;
        }

    }
    return r;
}

//15
int iguaisConsecutivos (char s[]){
    int i=0,sum=0,sumA=1;
    while(s[i]!='\0'){
        if(s[i]==s[i+1]){
            sumA++;
            i++;
        }else{
            if(sumA>sum)
                sum=sumA;
                sumA=1;
                i++;
        }
    }
    return sum;
}

//16 Só dá 1 teste correto!! Está mal!!
int difConsecutivos (char s[]){
    int i=0,sum=0,sumA=1;
    while(s[i]!='\0'){
        if(s[i]!=s[i+1]){
            sumA++;
            i++;
        }else{
            if(sumA>sum)
                sum=sumA;
                sumA=1;
                i++;
        }
    }
    return sum;
}

//17
int maiorPrefixo (char s1[],char s2[]){
    int i=0,j=0,r=0;
    while(s1[i]==s2[i]){
        r++;
        i++;
        j++;
    }
    return r;
}

//18
int maiorSufixo (char s1[], char s2[])
{
    int i,j,r=-1;
    for (i=0; s1[i]!='\0'; i++);
    for (j=0; s2[j]!='\0';j++);
    while (s1[i--] == s2 [j--])
        r++;
    return r;
}

//19
int sufPref (char s1[],char s2[]){
    int i,j=0,r=0;
    for(i=0;s1[i]!='\0';i++){
        if(s1[i]==s2[j]){
            r++;
            j++;
        }
        else r=j=0;
    }
    return r;
}

//20
int contaPal (char s[]) {
    int i,conta=0;
    char ultimo = ' ';
    for(i=0;s[i]!='\0';i++){
        if(!isspace (s[i]) && isspace(ultimo)) conta++;
        ultimo = s[i];
    }
    return conta;
}

//21
int contaVogais (char s[]){
    int i,r=0;
    for (i=0;s[i]!='\0';i++){
        if (s[i]=='a'|| s[i]=='e'|| s[i]=='i'|| s[i]=='o'|| s[i]=='u'|| s[i]=='A' || s[i]=='E' || s[i]=='I' || s[i]=='O' || s[i]=='U') r++;
    }
    return r;
}

//22
int contida (char a[], char b[]) {
 int i,j,c=0;
 for(i=0;a[i] && c;i++) c=0;
 for(j=0;b[j] && !c;j++)
    if(a[i]==b[j]) c=1;
return c;
}

//23
int palindroma (char s[]) {
    int i,j,r=1;
    for(i=0;s[i]!='\0';i++);
      for(j=0;s[j]!='\0' && r;j++)
        if(s[j]==s[i-1]){
          r=1;
          i--;
        }
        else r=0;
        return r;
      }

//ou
int palindroma (char s[]) {
    int i,m=1;
    int len = strlen(s)-1;
    for(i=0;s[i]!='\0' && m==1;i++){
        if(s[i]==s[len]) len--;
        else m=0;
    }
    return m;
}

//24
int remRep (char texto []) {
    int r,w=1;
    for(r=0;texto[r]!='\0';r++){
        if(texto[r]!=texto[r+1]){
            texto[w]=texto[r+1];
            w++;
        }
    }
    return (w-1);
}

//25
int limpaEspacos(char t[])
{
    int r, w;
    w = 0;
    for (r = 0; t[r]; r++)
        if (!isspace(t[r]))
            t[w++] = t[r];

        else if (isspace(t[r]) && !isspace(t[r + 1]))
            t[w++] = t[r];

        else if (!isspace(t[r]) && isspace(t[r + 1]))
            t[w++] = t[r];

    t[w] = '\0';
    return w;
}

//26
void insere (int v[],int N,int x){
    int i,g;
    for(i=0;i<N;i++){
        if(v[i]>x){
            g=v[i];
            v[i]=x;
            x=g;
        }
    }
    v[i]=x;
}

//27
void merge (int r [], int a[], int b[], int na, int nb){
       int ia=0,ib=0,ir=0;
       while(ia<na && ib<nb){
           if(a[ia]<b[ib]){
               r[ir]=a[ia];
               ia++;
               ir++;
           }
           else{
               r[ir]=b[ib];
               ib++;
               ir++;
           }
       }
           if(ia==na){
               while(ib<nb){
               r[ir]=b[ib];
               ir++;
               ib++;
           }
           }
           else{
               while(ia<na){
               r[ir]=a[ia];
               ia++;
               ir++;
           }
           }
   }

//28
int crescente (int a[], int i, int j){
       int r=1;
       while(i<j){
           if(a[i]>a[i+1]) r=0;
           i++;
       }
       return r;
   }

   //ou

   int crescente (int a[], int i, int j){
       int r=1;
       for(;i<j;i++){
           if(a[i]>a[i+1]) r=0;
       }
       return r;
   }

//29
int retiraNeg (int v[], int N){
       int i,w=0;
       for(i=0;i<N;i++){
           if(v[i]>=0){
               v[w]=v[i];
               w++;
           }
       }
       return w;
   }
//30
int conta(int v[],int N,int x){
    int i,r=0;
    for(i=0;i<N;i++){
        if(v[i]==x) r++;
    }
    return r;
}
    
int menosFreq (int v[], int N){
    int i,x,fr=N+1,r=0;
    for(i=0;i<N;i++){
      x=conta(v,N,v[i]);
        if(fr>x){
            fr=x;
            r=v[i];
        }
    }
    return r;
}

//31
int conta (int v[],int N,int x){
    int i,r=0;
    for(i=0;i<N;i++){
        if(v[i]==x) r++;
    }
    return r;
}


int maisFreq (int v[], int N){
    int i,x,f=0,r=0;
    for(i=0;i<N;i++){
        x=conta(v,N,v[i]);
        if(x>f){
            r=v[i];
            f=x;
        }
    }
    return r;
}

//32 
int maxCresc(int v[], int n){
    int r=0,tmp=1,i;
    for(i=0;i<n;i++){
        if(v[i]<=v[i+1]){
            tmp++;
        }else{
            if(tmp>r){
                r=tmp;
                tmp=1;
            }
            else tmp=1;
        }
    }
    return r;
}

//33
int aux(int v[],int N,int x){
       int i;
       for(i=0;i<N;i++){
           if(v[i]==x) return 1;
       }
       return 0;
   }
   
   int elimRep (int v[], int N) {
       int i,w=0;
       for(i=0;i<N;i++){
           if(!(aux(v,i,v[i]))) v[w++]=v[i];
       }
       return w;
   }

   //ou
    int elimRep (int v[], int N) {
       int l,e=1,j;
       if(N==0) return 0;
       else{
           for(l=1;l<N;l++){
               for(j=0;j<l && v[j]!=v[l];j++);
               if(j==l) v[e++]=v[l];       }
       }
       return e;
   }

//34
int elimRepOrd (int v[], int N) {
    int r,w=1;
    for(r=1;r<N;r++){
        if(v[r-1]!=v[r]){
            v[w]=v[r];
            w++;
        }
    }
    return w;
}

//ou

int elimRepOrd (int v[], int N) {
    int r,w=1;
    if(N==0) w=0;
    else
    for(r=1;r<N;r++){
        if(v[r]!=v[r-1]) v[w++]=v[r];
    }
    return w;
   }
   

//35
int comunsOrd (int a[], int na, int b[], int nb){
       int i=0,j=0,r=0;
       while(i<na && j<nb){
           if(a[i]==b[j]){
               r++;
               i++;
               j++;
           }
           else if(a[i]>b[j]) j++;
               else i++;
           }
       return r;
   }

//36
int comuns (int a[], int na, int b[], int nb){
      int i,j,r=0,l;
      for(i=0;i<na;i++){
          l=0;
          for(j=0;j<nb && l==0;j++){
              if(a[i]==b[j]){
              r++;
              l=1;
          }
      }
      }
      return r;
   }

//ou
int comuns (int a[], int na, int b[], int nb){
      int i,j,r=0;
      for(i=0;i<na;i++){
          for(j=0;j<nb;j++){
              if(a[i]==b[j]){
              r++;
              break;
          }
      }
      }
      return r;
   }

//37
int minInd (int v[], int n) {
   int i,j=0;
   for(i<n;v[i]<v[j];j++)
   return i;
}
//ou
int minInd (int v[], int n) {
   int r = 0,i=0;
   int w=v[0];
   for(r=1;r<n;r++){
       if(w>v[r]){
           w=v[r];
           i=r;
       }
   }
   return i;
}

//38
void somasAc (int v[], int Ac [], int N){
    int i,soma=0;
    for(i=0;i<N;i++){
        soma=soma+v[i];
        Ac[i]=soma;
    }
   
}

//39
int triSup (int N, int m [N][N]) {
    int l,c,r=1;
    for(l=0;l<N;l++){
        for(c=0;c<l;c++){
            if(m[l][c]!=0)
            r = 0;
        }
    }
    return r;
}

//40
void transposta (int N, float m [N][N]) {
    int c,l,temp;
    for(l=0;l<N;l++){
        for(c=0;c<l;c++){
            temp=m[l][c];
            m[l][c]=m[c][l];
            m[c][l]=temp;
        }
    }
}

//41
void addTo(int N, int M, int a [N][M], int b[N][M]) {
    int l,c;
    for(l=0;l<N;l++){
        for(c=0;c<M;c++){
            a[l][c]+=b[l][c];
        }
    }
}

//42
int unionSet (int N, int v1[N], int v2[N], int r[N]){
int i;
    for(i=0;i<N;i++){
        if(v1[i]==1 || v2[i]==1){
            r[i]=1;
        }else{
            r[i]=0;
        }
    }
    return 0;
}

//43
int intersectSet (int N, int v1[N], int v2[N], int r[N]){
       int i;
    for(i=0;i<N;i++){
        if(v1[i]==1 && v2[i]==1){
            r[i]=1;
        }else{
            r[i]=0;
        }
    }
    return 0;
}

//44
int intersectMSet (int N, int v1[N], int v2[N], int r[N]){
       int i;
    for(i=0;i<N;i++){
        r[i]=(v1[i]<v2[i]?v1[i]:v2[i]);
    }
    return 0;
}

//45
int max(int i,int j){
       int r;
       if(i>j) r=i;
       else r=j;
       return r;
   }
   
   int unionMSet (int N, int v1[N], int v2[N], int r[N]){
     int i;
     for(i=0;i<N;i++){
         r[i] = max(v1[i],v2[i]);
     }
     return 0;
   }

//ou
   int unionMSet (int N, int v1[N], int v2[N], int r[N]){
     int i;
     for(i=0;i<N;i++){
        r[i]=(v1[i]>v2[i]?v1[i]:v2[i]);
     }
     return 0;
   }
   

//46
int cardinalMSet (int N, int v[N]){
      int i,r;
    r=0;
    for(i=0;i<N;i++){
        r+=v[i];
    }
    return r;
}

//47
Posicao posFinal (Posicao inicial, Movimento mov[], int N){
    int i;
    for(i=0;i<N;i++){
        if(mov[i]==Norte) inicial.y++;
        else if(mov[i]==Sul) inicial.y--;
        else if(mov[i]==Este) inicial.x++;
        else inicial.x--;
    }
    return inicial;
}

//48
int caminho (Posicao inicial, Posicao final, Movimento mov[], int N){
    int i=0;
    while(inicial.x!=final.x || inicial.y!=final.y){
        if(i==N) return -1;
        if(inicial.x>final.x){
            mov[i++]=Oeste;
            inicial.x--;
        }
        else if(inicial.x<final.x){
            mov[i++]=Este;
            inicial.x++;
        }
        else if(inicial.y>final.y){
            mov[i++]=Sul;
            inicial.y--;
        }
        else if(inicial.y<final.y){
            mov[i++]=Norte;
            inicial.y++;
        }
    }
    return i;
}

//49
int maiscentral(Posicao pos[], int N)
{
    int i, ind;
    float dist;
    dist = sqrt(pos[0].x * pos[0].x + pos[0].y * pos[0].y);
    ind = 0;
    for (i = 1; i < N; i++){
        if (dist > sqrt(pos[i].x * pos[i].x + pos[i].y * pos[i].y))
        {
            dist = sqrt(pos[i].x * pos[i].x + pos[i].y * pos[i].y);
            ind = i;
        }
      }
    return ind;
}

//50
int vizinhos (Posicao p, Posicao pos[], int N) {
    int i,conta=0;
    for(i=0;i<N;i++){
        if(p.x == pos[i].x){
            if(p.y == pos[i].y + 1 || p.y == pos[i].y - 1) conta++;
        }
        else if (p.y==pos[i].y){
            if(p.x==pos[i].x + 1 || p.x == pos[i].x - 1) conta++;
        }
    }
    return conta;
}



