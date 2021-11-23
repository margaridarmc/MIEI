alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ']

# extended euclidean algorithm
def euclides(a, b):
    r = a
    r1 = b
    u = 1
    v = 0
    u1 = 0
    v1 = 1
    while r1 != 0:
        q = int(r / r1)
        rs = r
        us = u
        vs = v
        r = r1
        u = u1
        v = v1
        r1 = rs - q * r1
        u1 = us - q * u
        v1 = vs - q * v1
    return u


# fatoriza um número e dá como resultado os fatores
# serve nomeadamente para fatorizar n
def factor(n):
    if n == 0:
        return None
    elif n == 1:
        return 1
    else:
        theFactors = []
        for i in range(2, n + 1):
            while n % i == 0:
                n = n / i
                theFactors.append(i)
        return theFactors


# calcula a chaves a partir da fórmula d = e^(-1) mod ((p-1)(q-1))
# onde p e q são os dois números resultantes da fatorização de n
# é utilizado o algoritmo de euclides extendido para calcular a inversa
def getChaves(e, n):
    factors = factor(n)
    p = factors[0]
    q = factors[1]
    n = (p - 1) * (q - 1)
    d = euclides(e, n)
    return d


# função que imprime num ficheiro o resultado da decifragem do criptograma
def func(n, d, criptograma):
    f = open("texto_limpo.txt", "w")
    h = ''
    texto_limpo = ''
    for numero in criptograma:
        if numero != ',':
            h += numero
        else:
            # para cada número decifra (m ≡ c ** d (mod n))
            decifrado = (int(h) ** d) % n
            # para cada número decifrado é utilizado o polinómio 27L1²+ 27L2 + L1
            # de modo a encontrar as 3 Letras (ou espaços) que correspondem ao número decifrado
            # para tal são utilizados os índices do array que possui o alfabeto onde A=0,...Z= 25 e espaço = 26
            for l1 in range(0, len(alphabet), 1):
                for l2 in range(0, len(alphabet), 1):
                    for l3 in range(0, len(alphabet), 1):
                        x = (27 ** 2) * l1 + 27 * l2 + l3
                        if x == decifrado:
                            texto_limpo += alphabet[l1] + alphabet[l2] + alphabet[l3]
                            h = ' '
    f.write(texto_limpo)


if __name__ == '__main__':
    # e=17, n=213271
    d = getChaves(17, 213271)
    f = open("criptograma", "r")
    func(213271, d, f.read())

