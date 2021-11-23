#!/usr/bin/python
import base64
import os, sys
from cryptography.hazmat.primitives import hashes, hmac
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes

# Gerar dois arrays (diferentes!!) de bytes de tamanhos adequados, a utilizar
# como chave para a cifra e para o mac. Estes valores deverão estar hardcoded em
# ambos ficheiros enc.py e dec.py.
key = b'\x81G\xbd.\x98\xfc\xf8U \xb8.&\xc5k\xc9|\xf2\xee\xb6o\x07\x9c\x00p\x03A|"\xf3\x05\x9c|'
hmackey = b'~\x99E\xa4\x87^6\xa3F\xa6\xb1\xa6\xb1\xd4\x82o\x19}\r\xd8\xd8\xbe\x8b\xde\xbd\xc9l\xbdQ\xac\xdc\x02'

msg = "Isto é uma mensagem não muito secreta!"


def etm():
    msg_bytes = msg.encode("utf8")
    # encripta a mensagem
    nonce = os.urandom(16)
    algorithm = algorithms.ChaCha20(key, nonce)
    cipher = Cipher(algorithm, mode=None)
    encryptor = cipher.encryptor()
    encryptedmessage = encryptor.update(msg_bytes)
    # calcula o valor do mac no texto-cifrado
    h = hmac.HMAC(hmackey, hashes.SHA256())
    h.update(encryptedmessage)
    tagMac = h.finalize()
    # faz a junção da tag com o criptograma e com o nonce
    # o nonce foi adicionado para depois decifrar
    finalmessage = tagMac + encryptedmessage + nonce
    w2f("dados-etm.dat", finalmessage)

def eam():
    msg_bytes = msg.encode("utf8")
    # encripta a mensagem
    nonce = os.urandom(16)
    algorithm = algorithms.ChaCha20(key, nonce)
    cipher = Cipher(algorithm, mode=None)
    encryptor = cipher.encryptor()
    encryptedmessage = encryptor.update(msg_bytes)
    # calcula o valor do mac no texto-limpo
    h = hmac.HMAC(hmackey, hashes.SHA256())
    h.update(msg_bytes)
    tagMac = h.finalize()
    # faz a junção da tag com o criptograma e com o nonce
    # o nonce foi adicionado para depois decifrar
    finalmessage = tagMac + encryptedmessage + nonce
    w2f("dados-eam.dat", finalmessage)



def mte():
    msg_bytes = msg.encode("utf8")
    # calcula o valor do mac no texto-limpo
    h = hmac.HMAC(hmackey, hashes.SHA256())
    h.update(msg_bytes)
    tagMac = h.finalize()
    # adiciona a tag à mensagem
    mensagem = tagMac + msg_bytes
    # encripta a mensagem total (tag e mensagem)
    nonce = os.urandom(16)
    algorithm = algorithms.ChaCha20(key, nonce)
    cipher = Cipher(algorithm, mode=None)
    encryptor = cipher.encryptor()
    encryptedmessage = encryptor.update(mensagem)
    # faz a junção do criptograma com o nonce
    # o nonce foi adicionado para depois decifrar
    finalmessage = encryptedmessage + nonce
    w2f("dados-mte.dat", finalmessage)


def w2f(nomeficheiro, data):
    with open(nomeficheiro, 'wb') as f:
        f.write(data)


def main():
  if len(sys.argv) != 2:
    print("Please provide one of: eam, etm, mte")
  elif sys.argv[1] == "eam":
    eam()
  elif sys.argv[1] == "etm":
    etm()
  elif sys.argv[1] == "mte":
    mte()
  else:
    print("Please provide one of: eam, etm, mte")


if __name__ == '__main__':
    main()
