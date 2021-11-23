import numpy
from math import gcd


# extended euclidean algorithm
def euclides(a, b, c):
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
    result = (u * c) % b
    return result


# função que resolve sistemas de congruências modulares
# c x ≡ a mod n
# o c pode ser None ou não dependendo do sistema
def resolve(a, n, c):
    if c is not None: #verifica se o c não é None, caso não seja é chamada a função do algoritmo de euclides
        l = []
        for ctr in range(len(a)):
            e = euclides(c[ctr], n[ctr], a[ctr])
            l.append(e)
        a = l
    # verifica se o gdc de todos os elementos do array n é igual a 1:
    # (isto comprova que eles são todos relativamente primos)
    gc = gcd(n[0], n[1])
    for i in range(2, len(n)):
        gc = gcd(gc, n[i])
    # se i gdc for 1 o sistema é resolvido
    if gc == 1:
        N = numpy.prod(n) # multiplica os valores que estão em n para no final se poder fazer (mod N)
        # multiplica os valores do array n para formar os diferentes Ni
        # se tiver tamanho de 2 apenas se trocam os valores
        Ni = []
        if len(n) > 2:
            for i in range(len(n)):
                for j in range(len(n)):
                    if i != j:
                        Ni.append(n[i]*n[j])
            Ni = sorted(set(Ni))
        else:
            for i in range(2):
                Ni.append(n[i])
        Ni.reverse()
        v = []
        # coloca no array v o resultado de Ni mod n[i]
        for i in range(len(n)):
            v.append(Ni[i] % n[i])
        o = 1
        x = []
        j = 0
        # enquanto o mod for diferente de 1 vai encontrar um valor i que o faça ser
        # coloca no array x esses valores
        while j <= len(n)-1:
            while ((v[j] * o) % n[j]) != 1:
                o += 1
            x.append(o)
            j += 1
            o = 1
        aNx = []
        # multiplica ai*Ni*xi e soma tudo de modo a encontrar depois o valor de x
        for indice in range(len(a)):
            aNx.append(a[indice] * Ni[indice] * x[indice])
        aNx = sum(aNx)
        # efetua o mod para encontrar o valor de x
        result = aNx % N
        return result
    # caso o gdc não seja 1 é retornado um erro
    else:
        print("Impossível de Resolver")


'''
Sistemas a resolver:

x ≡ 48 (mod 13)             19x ≡ 21 (mod 16)
x ≡ 57 (mod 23)             37x ≡ 100 (mod 15)
x ≡ 39 (mod 27)
'''
if __name__ == '__main__':
    x = resolve([48, 57, 39], [13, 23, 27], None)
    print(x)
    x2 = resolve([21, 100], [16, 15], [19, 37])
    print(x2)
