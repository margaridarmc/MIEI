import collections


def getList(dict):
    return dict.keys()


# Aplica o Algoritmo de Euclides
def modInverse(a, m):
    a = a % m
    for x in range(1, m):
        if (a * x) % m == 1:
            return x
    return 1


# devolve o segundo maior elemento da lista
def get_second_max(list1):
    mx = max(list1[0], list1[1])
    secondmax = min(list1[0], list1[1])
    n = len(list1)
    u = 0
    for i in range(2, n):
        if list1[i] > mx:
            secondmax = mx
            mx = list1[i]
        elif secondmax < list1[i] != mx:
            secondmax = list1[i]
    return secondmax


def getCircular(i, size):
    ret = i % size
    return ret


alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z']

lista_pontuacao = [" ", "!", ",", ".", ";", "?", "\"", "\'", ":"]

english_frequences = [8.12, 1.49, 2.71, 4.32, 12.0, 2.30, 2.03, 5.92, 7.31, 0.10, 0.69,
                      3.98, 2.61, 6.95, 7.68, 1.82, 0.11, 6.02, 6.28, 9.10, 2.88, 1.11,
                      2.09, 0.11, 2.11, 0.07]


def analisar_frequencias(ciphertext):
    list = []
    letter_count_list_dict = {}
    string = ""
    # ciclo que permite andar pelos índices corretos
    for index, item in enumerate(ciphertext):
        if 0 == index % 1:
            string += item
    letter_frequency = collections.Counter(string)
    # conta e coloca no dicionário o número de letras da string
    for itemL, letterCount in letter_frequency.items():
        letter_count_list_dict[itemL] = letterCount

    lista = getList(letter_count_list_dict)
    list = []
    index = 0
    # insere o número de vezes que uma letra aparece numa lista de 26 elementos em que o índice 0 corresponde ao A, 1 ao B...
    for u in alphabet:
        if lista.__contains__(u):
            list.insert(index, letter_count_list_dict.get(u))
            index += 1
        else:
            list.insert(index, 0)
            index += 1

    list_freqs = []
    k = 0
    # calcula a frequência com que cada letra aparece no criptograma
    while k < 26:
        if list[k] > 0:
            list_freqs.insert(k, list[k] / sum(list))
            k += 1
        else:
            list_freqs.insert(k, 0)
            k += 1
    first_letter = list_freqs.index(max(list_freqs)) # letra que aparece mais no texto cifrado
    second_letter = list_freqs.index(get_second_max(list_freqs)) # segunda letra que aparece mais no texto cifrado
    letra = english_frequences.index(max(english_frequences)) # letra com maior frequência alfabeto inglês
    english_frequences.insert(english_frequences.index(max(english_frequences)), 0)
    english_frequences.remove(max(english_frequences))
    segunda_letra = english_frequences.index(max(english_frequences)) # segunda letra com maior frequência alfabeto inglês
    #resolução do sistema de equações:
    x = (segunda_letra - letra) % 26
    y = (first_letter - second_letter) % 26
    alpha = (y * modInverse(x, 26)) % 26
    beta = (first_letter - segunda_letra * alpha) % 26
    key = (alpha, beta)
    return key


def number_letter(letter):
    i = 0
    if alphabet.__contains__(letter):
        i = alphabet.index(letter)
    return i


# percorre o texto cifrado e atavés da chave e da fórmula, para cada letra calcula o número da letra do texto-limpo
def find_plaintext(alpha, beta, ciphertext):
    string = ""
    for c in ciphertext:
        if lista_pontuacao.__contains__(c): #coloca a pontuação e os espaços nos sítios certos
            string += c
        else:
            x = modInverse(alpha, 26) * (number_letter(c) - beta)
            modulo = x % 26
            string += alphabet[modulo] #vai ao alfabeto buscar a letra pelo índice
    return  string


if __name__ == '__main__':
    f = open("cript2", "r")
    alpha, beta = analisar_frequencias(f.read())
    f = open("cript2", "r")
    string = find_plaintext(alpha, beta, f.read())
    file = open("PlaintextAffine.txt", "w")
    file.write(string)
    file.close()
