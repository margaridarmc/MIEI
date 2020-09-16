CFLAGS =-g -Wall -o2
PROJECT=mainP

$(PROJECT): main.o CatProd.o CatCli.o avl.o faturacao.o interface.o vendas.o paginacao.o Filial.o
	gcc -o $(PROJECT) main.o CatProd.o CatCli.o avl.o faturacao.o interface.o vendas.o paginacao.o Filial.o

clean:
	rm -rf *.o $(PROJECT)

main.o: main.c main.h CatCli.h CatProd.h avl.h faturacao.h interface.h vendas.h paginacao.h Filial.h
CatProd.o: CatProd.c CatProd.h main.h avl.h CatCli.h faturacao.h interface.h vendas.h paginacao.h Filial.h
CatCli.o: CatCli.c CatCli.h main.h CatProd.h avl.h faturacao.h interface.h vendas.h paginacao.h Filial.h
avl.o: avl.c avl.h CatProd.h main.h CatCli.h faturacao.h interface.h vendas.h paginacao.h Filial.h
faturacao.o: faturacao.c faturacao.h avl.h CatProd.h main.h CatCli.h interface.h vendas.h paginacao.h Filial.h
interface.o: interface.c interface.h faturacao.h avl.h CatProd.h main.h CatCli.h vendas.h paginacao.h Filial.h
vendas.o: vendas.c vendas.h interface.h faturacao.h avl.h CatProd.h main.h CatCli.h paginacao.h Filial.h
paginacao.o:paginacao.c paginacao.h vendas.h interface.h faturacao.h avl.h CatProd.h main.h CatCli.h Filial.h
Filial.o:Filial.c paginacao.h vendas.h interface.h faturacao.h avl.h CatProd.h main.h CatCli.h Filial.h