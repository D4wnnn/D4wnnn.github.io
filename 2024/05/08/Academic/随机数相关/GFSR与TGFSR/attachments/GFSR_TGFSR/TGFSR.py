def tgfsr():
    p = 5
    q = 2
    iv = [
        [1, 1, 0, 1, 0],
        [1, 0, 0, 0, 1],
        [1, 1, 0, 1, 1],
        [1, 1, 1, 0, 0],
        [1, 0, 0, 1, 1]
    ]
    l = 0
    for i in range(45):
        print(i, iv[l])
        tmp = iv[l]
        tmp.pop()
        tmp.insert(0, 0)
        a = [0, 0, 1, 0, 1]
        if tmp[-1] == 1:
            for i in range(5):
                tmp[i] ^= a[i]
        for m in range(5):
            iv[l][m] = iv[(l + q) % p][m] ^ tmp[m]
        l = (l + 1) % p


if __name__ == '__main__':
    tgfsr()
