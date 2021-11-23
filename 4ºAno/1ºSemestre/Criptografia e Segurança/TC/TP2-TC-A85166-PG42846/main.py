'''classe para guardar uma lista com as subtrações modulares
   das letras dos criptogramas convertidas para valores
   numéricos e os respetivos ids. Zeros indica o número
   de zeros de cada subtração.
'''
class cripto:
    def __init__(self, lista, id1, id2, zeros):
        self.lista = lista
        self.id1 = id1
        self.id2 = id2
        self.zeros = zeros


''' Recebe os criptogramas e dá como resultado
    os criptogramas cuja subtração deu
    mais zeros
'''
def getCriptogramas():
    f = open("criptogramas", "r")
    lista_criptogramas = []
    index = 0
    string = ""
    ''' cópia de cada criptograma do ficheiro para uma lista '''
    for i, char in enumerate(f.read()):
        if char != '\n':
            string += char
        if char == '\n':
            lista_criptogramas.insert(index, string)
            index += 1
            string = ""
    lista_numeros_total = []
    ''' Conversão dos criptogramas para valores numéricos '''
    for l in lista_criptogramas:
        lista_numeros = []
        for char in l:
            lista_numeros.append(ord(char)-65)
        lista_numeros_total.append(lista_numeros)

    indice = 0
    lista_subtracoes = []
    ''' Subtrações de todos os criptogramas com todos menos com ele próprio '''
    for i in range(20):
        for j in range(20):
            if i != j:
                lista = subtract(lista_numeros_total[i], lista_numeros_total[j])
                lista_subtracoes.append(cripto(lista, i, j, -1))
                indice += 1

    i = 0
    ''' conta o número de zeros de cada criptograma'''
    for l in lista_subtracoes:
        for t in l.lista:
            if t == 0:
                i += 1
        l.zeros = i
        i = 0

    ''' Coloca os valores na classe e vê qual o que tem mais zeros'''
    maior = cripto([], -1, -1, -1)
    for indice in enumerate(lista_subtracoes):
        if (maior.zeros < indice.__getitem__(1).zeros):
            maior = cripto(indice.__getitem__(1).lista, indice.__getitem__(1).id1, indice.__getitem__(1).id2,
                           indice.__getitem__(1).zeros)
    return maior


''' Subtração modular de duas listas '''
def subtract(list1, list2):
    lista = []
    for i in range(len(list1)):
        lista.append((list1[i]-list2[i]) % 26)
    return lista


if __name__ == '__main__':
    maior = getCriptogramas()
    print(maior.id1 + 1, maior.id2 + 1) # somamos + 1 porque o índice da lista começa em 0