#!/usr/bin/python

import os, sys
import base64
from cryptography.hazmat.primitives import hashes, hmac
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes


# Gerar dois arrays (diferentes!!) de bytes de tamanhos adequados, a utilizar
# como chave para a cifra e para o mac. Estes valores deverão estar hardcoded em
# ambos ficheiros enc.py e dec.py.
key = b'\x81G\xbd.\x98\xfc\xf8U \xb8.&\xc5k\xc9|\xf2\xee\xb6o\x07\x9c\x00p\x03A|"\xf3\x05\x9c|'
hmackey = b'~\x99E\xa4\x87^6\xa3F\xa6\xb1\xa6\xb1\xd4\x82o\x19}\r\xd8\xd8\xbe\x8b\xde\xbd\xc9l\xbdQ\xac\xdc\x02'


def rff(nomeficheiro):
  with open(nomeficheiro, 'rb') as f:
    return f.read()


def etm():
  data = rff("dados-etm.dat")
  nonce = data[(len(data) - 16):]  # guardar os últimos 16 bytes na variável nonce
  data = data[:(len(data) - 16)]  # retirar os 16 bytes da data
  # decifra os dados da data
  algorithm = algorithms.ChaCha20(key, nonce)
  cipher = Cipher(algorithm, mode=None)
  decryptor = cipher.decryptor()
  tag = data[:32]
  data = data[32:]  # retirar os bytes da tag
  decifrado = decryptor.update(data)
  print(decifrado.decode("utf8"))
  # verificação antes da decifragem
  h = hmac.HMAC(hmackey, hashes.SHA256())
  h.update(data)
  h.verify(tag)



def eam():
  data = rff("dados-eam.dat")
  nonce = data[(len(data)-16):]  #guardar os últimos 16 bytes na variável nonce
  data = data[:(len(data)-16)]   #retirar os 16 bytes da data
  # decifra os dados da data
  algorithm = algorithms.ChaCha20(key, nonce)
  cipher = Cipher(algorithm, mode=None)
  decryptor = cipher.decryptor()
  tag = data[:32]
  data = data[32:] # retirar os bytes da tag
  decifrado = decryptor.update(data)
  print(decifrado.decode("utf8"))
  # verificação depois da cifragem
  h = hmac.HMAC(hmackey, hashes.SHA256())
  h.update(decifrado)
  h.verify(tag)


def mte():
  data = rff("dados-mte.dat")
  nonce = data[(len(data) - 16):]  # guardar os últimos 16 bytes na variável nonce
  data = data[:(len(data) - 16)]  # retirar os 16 bytes da data
  # decifra os dados da data
  algorithm = algorithms.ChaCha20(key, nonce)
  cipher = Cipher(algorithm, mode=None)
  decryptor = cipher.decryptor()
  decifrado = decryptor.update(data)
  tag = decifrado[:32]
  decifrado = decifrado[32:]  # retirar os bytes da tag
  print(decifrado.decode("utf8"))
  # verificação depois da cifragem
  h = hmac.HMAC(hmackey, hashes.SHA256())
  h.update(decifrado)
  h.verify(tag)


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
