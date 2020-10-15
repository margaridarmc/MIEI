trab.exe : trabalho.o cJSON.o lex.yy.o
	gcc -o trab.exe trabalho.o cJSON.o lex.yy.o 

cJSON.o: cJSON.c cJSON.h
	gcc -c cJSON.c

trabalho.o: trabalho.c trabalho.h 
	gcc -c trabalho.c

lex.yy.o: lex.yy.c trabalho.h cJSON.h
	gcc -c lex.yy.c

lex.yy.c: comments.l
	flex comments.l
