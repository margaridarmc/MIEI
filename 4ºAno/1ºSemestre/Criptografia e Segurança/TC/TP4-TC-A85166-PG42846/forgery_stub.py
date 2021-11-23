#!/usr/bin/python

from cryptography.hazmat.primitives.ciphers import Cipher, modes
from cryptography.hazmat.primitives.ciphers import algorithms
import os

AES_BLOCK_LENGTH = 16  # bytes
AES_KEY_LENGTH = 32  # bytes


# Insecure CBCMAC.
def cbcmac(key, msg):
    # CBCMAC with a random IV
    if not _validate_key_and_msg(key, msg):
        return False
    else:
        iv = os.urandom(16)
        cipher = Cipher(algorithms.AES(key), modes.CBC(iv))  # combina algoritmo AES com modo CBC
        encryptor = cipher.encryptor()
        ct = encryptor.update(msg) + encryptor.finalize()
        tag = iv + ct[16:]  # concatenação do iv com o último bloco do criptograma (tag)
        return tag


def verify(key, msg, tag):
    if not _validate_key_and_msg(key, msg):
        return False
    else:
        iv = tag[:16]  # retirar da tag o IV
        t = tag[16:]  # retirar da tag os bytes respetivos à mesma
        # recalcular a tag
        cipher = Cipher(algorithms.AES(key), modes.CBC(iv))  # combina algoritmo AES com modo CBC
        encryptor = cipher.encryptor()
        ct = encryptor.update(msg) + encryptor.finalize()
        tag2 = ct[16:]
        if t == tag2:  # comparação entre a tag enviada e a nova tag
            return True
        else:
            return False


# função utilizada para a operação XOR
def byte_xor(ba1, ba2):
    return bytes([_a ^ _b for _a, _b in zip(ba1, ba2)])


# Receives a pair consisting of a message, and a valid tag.
# Outputs a forged pair (message, tag), where message must be different from the
# received message (msg).
# ---> Note that the key CANNOT be used here! <---
def produce_forgery(msg, tag):
    iv = tag[:16] # retirar da tag o IV
    iv2 = os.urandom(16) # criação do novo IV
    m1 = byte_xor(byte_xor(msg[:16], iv), iv2) # criação da primeira parte da nova mensagem: m1 = P1 xor iv xor iv'
    new_msg = m1 + msg[16:] #criação da mensagem total com m1 e a segunda parte da mensagem original
    new_tag = iv2 + tag[16:] # criação da nova tag a partir da concatenação do novo IV com a tag anterior
    return (new_msg, new_tag)


def check_forgery(key, new_msg, new_tag, original_msg):
    if new_msg == original_msg:
        print("Having the \"forged\" message equal to the original " +
              "one is not allowed...")
        return False

    if verify(key, new_msg, new_tag) == True:
        print("MAC successfully forged!")
        return True
    else:
        print("MAC forgery attempt failed!")
        return False


def _validate_key_and_msg(key, msg):
    if type(key) is not bytes:
        print("Key must be array of bytes!")
        return False
    elif len(key) != AES_KEY_LENGTH:
        print("Key must be have %d bytes!" % AES_KEY_LENGTH)
        return False
    if type(msg) is not bytes:
        print("Msg must be array of bytes!")
        return False
    elif len(msg) != 2 * AES_BLOCK_LENGTH:
        print("Msg must be have %d bytes!" % (2 * AES_BLOCK_LENGTH))
        return False
    return True


key = os.urandom(32)


def main():
    msg = os.urandom(32)

    tag = cbcmac(key, msg)

    # Should print "True".
    print(verify(key, msg, tag))

    (mprime, tprime) = produce_forgery(msg, tag)

    # GOAL: produce a (message, tag) that fools the verifier.
    check_forgery(key, mprime, tprime, msg)


if __name__ == '__main__':
    main()
