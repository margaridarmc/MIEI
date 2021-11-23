from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
import base64
import os


def ECB():

    key = os.urandom(32) #geração de uma key aleatório com tamanho 32 bytes
    cipher = Cipher(algorithms.AES(key), modes.ECB())  #combina algoritmo AES com modo ECB
    encryptor = cipher.encryptor()
    #passagem da imagem para bytes:
    with open("ubuntu.bmp", "rb") as imageFile:
        datas = base64.b64encode(imageFile.read())
        imageFile.close()
    #adicionados bytes para que os bytes da imagem sejam múltiplos do bloco
    #neste caso os bytes adicionados foram os 8 primeiros bytes da imagem
    ct = encryptor.update(datas+datas[:8]) + encryptor.finalize()
    #codificação em utf8
    newdatas = ct.hex().encode('utf8')
    m = base64.b64decode(newdatas)
    #escrever a imagem codificada em 3.bmp
    fh = open("ecb.bmp", "wb")
    fh.write(m)
    fh.close()
    #adcionar o 54 bytes ao header
    os.system("dd if=ubuntu.bmp of=ecb.bmp bs=1 count=54 conv=notrunc")


def CBC():

    key = os.urandom(32) #geração de uma key aleatório com tamanho 32 bytes
    iv = os.urandom(16)  #geração de um vetor de incialização aleatório com tamnho 16 bytes
    cipher = Cipher(algorithms.AES(key), modes.CBC(iv)) #combina algoritmo AES com modo CBC
    encryptor = cipher.encryptor()
    # passagem da imagem para bytes:
    with open("ubuntu.bmp", "rb") as imageFile:
        datas = base64.b64encode(imageFile.read())
        imageFile.close()
    # adicionados bytes para que os bytes da imagem sejam múltiplos do bloco
    # neste caso os bytes adicionados foram os 8 primeiros bytes da imagem
    ct = encryptor.update(datas+datas[:8]) + encryptor.finalize()
    newdatas = ct.hex().encode('utf8')
    m = base64.b64decode(newdatas)
    fh = open("cbc.bmp", "wb")
    fh.write(m)
    fh.close()
    # adcionar o 54 bytes ao header
    os.system("dd if=ubuntu.bmp of=cbc.bmp bs=1 count=54 conv=notrunc")


if __name__ == '__main__':
    ECB()
    CBC()
