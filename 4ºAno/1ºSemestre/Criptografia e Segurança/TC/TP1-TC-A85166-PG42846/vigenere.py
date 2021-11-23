import collections
import re

class IC:
    def __init__(self, groupNº, IC, groupLetters):
        self.groupNº = groupNº+2
        self.IC = IC
        self.groupLetters = groupLetters

english_frequences = [0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015,
                      0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749,
                      0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758,
                      0.00978, 0.02360, 0.00150, 0.01974, 0.00074]

'''  0.0613, 0.0032, 0.0653, 0.0153, 0.0033, 0.001, 0.001 '''
alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V'
    , 'W', 'X', 'Y', 'Z']

''' ,', ';', '.', '-', '!', '(', ') '''

def getList(dict):
    return dict.keys()


def get_indexes_max_value(l):
    max_value = max(l)
    if l.count(max_value) > 1:
        return [i for i, x in enumerate(l) if x == max(l)]
    else:
        return l.index(max(l))


def get_length_key(cypherText):
    # key=HELLOOO
    # o que está cifrado= For cryptographers, a cryptographic is anything faster than a brute-force attack performing one trial decryption for each possible key in sequence see Cryptanalysis. A break can thus include results that are infeasible with current technology. Despite being impractical, theoretical breaks can sometimes provide insight into vulnerability patterns. The largest successful publicly known brute-force attack against a widely implemented block-cipher encryption algorithm was against a 64-bit The key space increases by a factor of 2 for each additional bit of key length, and if every possible value of the key is equiprobable, this translates into a doubling of the average brute-force key search time. This implies that the effort of a brute-force search increases exponentially with key length. Key length in itself does not imply security against attacks, since there are ciphers with very long keys that have been found to be vulnerable AES has a fairly simple algebraic framework.[15] In 2002 a theoretical attack, named the, was announced by
   # cypherText = "MSCNFMDASRCODVLVDLQFMWXZRFODOMNTGOBFXSTBUTHWEPFHVHRLMFIHLJZCQSOAXLNYDSYJZCAWBNSYPHFWHPOPQFMWXTZBTCYILNVDCZWTMZSYLCTYGSEBIYNSGSLGCJDHOUEWJGWGHFCPOYQHRESIGWUGWFRSFLWFWHGHOEELFSWUJPLGWPSIHTHVQBVCPBHHLGSYCZCNCOPGDWAIMPWBUPQACOQHPGLWHVSVVPEWQOSFCPOYGJEYDCASAMXPGDFVZTOSWBZMRSHWBASGFZBSYEMTZWHFTLEHSFUWESSZOYKPDHGIJGPDGTISTFMZWQSCVYCKBIVFESTCYGPLHHOJOLROWBZXLHWRSSCTXDZSTIYESRPSSNVQWDOICPBQFFTETCBOSKZCWHVTALDOUOPRDEOPWAXSPYSMZTLNSWBJVPLGSGICLQOQHVVZQTCFLENSORRPXTZBOZIMEZTYSFPPYUHVHROTTSJLVJACGGPFWPJOZBIZQHVSRIJTGSEBMACCPOIPPEVWGAVLYGZOAIDTBHCHHZFPZWUKZQHVSHZPCOUSIVFESTCYGPVSMGLECNVHWTIESWGWTTWTSGHOEEEVSSMJZCHCTHFCFHSTVVNPGSOYGSTBQFLEDPGSLWSYPBHWHPWJKWHOOPJZSBNXSVSMZLRREVWBPXDPZTRVIDYCHWTTWJGSQBVTEMOUHMYDHOHAENVGGWUGPEVSFLECPQWDOICDKWHOZPCMZCUKVPMGHOEESOJSIIPYTCIUHEZPSJBPYPFOPSILPGVOZEQLWFZFWTXDZSHPRPPFOPGQCOASDSCVWBOALPZFSHPGLWOHHHGVYOASKXSPKOGHRYZIBQLHMJ"
    maxGroup = 1
    group = 2
    newString = ""
    my_list = []
    listAux = []
    indexAux = 0
    indexIteration = 0


    # separar por grupos
    # group começa por criar 2 grupos, max group o numero de iterações, para poder adicionar ao group e assim fazer um grupo maior ex..(2,3,4)
    # o index interaction percorre o numero de grupos maximo para o contexto por exemplo se tiver que fazer o grupo= 3 conta as 3 iterações
    # indexAux escolhe em que letra começa a contar/adicionar
    while maxGroup < 8:
        newString = ""
        while indexIteration < group:
            newString = ""
            for index, item in enumerate(cypherText):
                # make groups
                if 0 == (index + (indexAux - 1)) % (maxGroup + 1):
                    newString += item

            listAux.insert(indexAux, newString)
            indexAux = indexAux + 1
            indexIteration = indexIteration + 1

        indexIteration = 0
        group = group + 1
        my_list.insert((maxGroup - 1), listAux)
        maxGroup += 1
        listAux = []

    # index of coincidence get lnght keyword

    ##count letters
    genericCountList = []
    letterCountList = []
    letterCountListDict = {}
    countMaxLetter = 0
    letterFrequency = None
    index2Aux = 0
    totalLetersGroups = []

    for index, item in enumerate(my_list):
        for index2, item2 in enumerate(item):
            letterCountListDict = {}
            letterFrequency = collections.Counter(item2)
            for itemL, letterCount in letterFrequency.items():
                letterCountListDict[itemL] = letterCount
            letterCountList.insert(index2Aux, letterCountListDict)
            index2Aux = index2Aux + 1
        index2Aux = 0

        genericCountList.insert(index, letterCountList)
        letterCountList = []

    ICList = []
    listGroupIC = []
    listGroupICTemp = []
    countLetter = 0
    letter = []
    icMultUp = 0
    icMultDown = 0
    icFinalG = 0
    indexGroup = 0
    groupAverage = 0

    # count letters in group
    for index, item in enumerate(genericCountList):
        for index2, item2 in enumerate(item):
            for key, val in item2.items():
                countLetter += val
                icMultUp += (val * (val - 1))
            # compute IC individualy
            icMultDown = countLetter * (countLetter - 1)
            icFinalG = icMultUp / icMultDown
            listGroupICTemp.insert(index2, icFinalG)
            icMultUp = 0
            icMultDown = 0
            countLetter = 0
            icFinalG = 0
        # average of the groups
        for item3 in listGroupICTemp:
            groupAverage += item3
        groupAverage = groupAverage / listGroupICTemp.__len__()
        listGroupIC.append(groupAverage)
        groupAverage = 0
        listGroupICTemp = []

    listGroupICAux = []
    # save results in class list
    for index, ic in enumerate(listGroupIC):
        listGroupICAux.append(IC(index, ic, my_list.__getitem__(index)))
    icSave = IC(0, 0, 0)
    for group in enumerate(listGroupICAux):
       # print(icSave.IC)
        if (icSave.IC < group.__getitem__(1).IC):
            icSave = IC(group.__getitem__(1).groupNº-2, group.__getitem__(1).IC, group.__getitem__(1).groupLetters)
       # print(icSave.groupNº)
    return icSave.groupNº


def get_key(ciphertext, keylength):
    list = []
    j = 0
    x = 0
    indice = 0
    for counter in range(keylength, 0, -1):
        letter_count_list_dict = {}
        string = ""
        # cipher é a substring do ciphertext
        cipher = ciphertext[x:len(ciphertext)]
        # ciclo que permite andar pelos índices corretos
        for index, item in enumerate(cipher):
            if 0 == index % keylength:
                string += item
        letter_frequency = collections.Counter(string)
        # conta e coloca no dicionário o número de letras da string
        for itemL, letterCount in letter_frequency.items():
            letter_count_list_dict[itemL] = letterCount
        lista = getList(letter_count_list_dict)
        # coloca na lista y quantas vezes uma certa letra aparece (indice 0 corresponde à letra A)
        y = []
        for u in alphabet:
            if lista.__contains__(u):
                y.insert(index, letter_count_list_dict.get(u))
                indice += 1
            else:
                y.insert(index, 0)
                indice += 1
        list.insert(j, y)  #concatenação das listas y [[3,4,0,0...],[7,0,8,...]] na primeira lista 3A 4B, segunda lista 7A, 8C
        j += 1
        x += 1
    lista_freqs_total = []
    p = 0
    # percorre a lista de listas e calcula a frequência de cada letra no seu grupo
    for l in list:
        list_freqs = []
        k = 0
        while k < 26:
            if l[k] > 0:
                list_freqs.insert(k, l[k]/sum(l))
                k += 1
            else:
                list_freqs.insert(k, 0)
                k += 1

        lista_freqs_total.insert(p, list_freqs)
        p += 1
    list_shifts_total = []

    # multiplica a lista das frequências de grupo pela lista de frequências inglesas  e realiza a soma de todas as multiplicações
    j =0
    for l in lista_freqs_total:
        shift=0
        list_shifts = []
        while shift < 26:
            soma = 0
            i = 0
            while i < 26:
                soma += l[getCircular(i+shift,26)]*english_frequences[i]
                i += 1
            list_shifts.insert(shift, soma)
            shift+=1
        #print(list_shifts)
        list_shifts_total.insert(j,list_shifts)
        j+=1

    # calcula os índices que possuem os elementos maiores da lista
    lista_max_shifts = []
    i = 0
    for l in list_shifts_total:
        shift = get_indexes_max_value(l)
        lista_max_shifts.insert(i, shift)
        i += 1

    # através da lista dos índices calcula a letra da chave (a lista dos índices tem o número de shifts)
    key = ""
    for i in lista_max_shifts:
        key += alphabet[i]
    return key


def getCircular(i,size):
    ret = (i)%size
    return ret



def vigenere_decrypt(encrypted_vigener, keyword):
    keyword_length = len(keyword)
    keyword_as_int = [ord(i) for i in keyword]
    encrypted_vigener_int = [ord(i) for i in encrypted_vigener]
    plaintext = ''
    for i in range(len(encrypted_vigener_int)):
        if encrypted_vigener[i].isalpha():
            value = (encrypted_vigener_int[i] - keyword_as_int[i % keyword_length]) % 26
            plaintext += chr(value + 65)
        else:
            plaintext += encrypted_vigener[i]
    return plaintext



if __name__ == '__main__':
  f = open("cript1", "r")
  length = get_length_key(f.read())
  f = open("cript1", "r")
  key = get_key(f.read(),length)
  f = open("cript1", "r")
  string = vigenere_decrypt(f.read(),key)
  file = open("PlaintexVigenere.txt", "w")
  file.write(string)
  file.close()