def gfsr1():
    iv = [
        [1, 1, 0, 1, 0],
        [1, 0, 0, 0, 1],
        [1, 1, 0, 1, 1],
        [1, 1, 1, 0, 0],
        [1, 0, 0, 1, 1]
    ]
    for i in range(5):
        print(i, iv[i])
    for i in range(5, 32):
        x = []
        for j in range(5):
            x.append(iv[i - 3][j] ^ iv[i - 5][j])
        print(i, x)
        iv.append(x)


def gfsr2():
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
    for i in range(31):
        print(i, iv[l])
        for m in range(5):
            iv[l][m] = iv[(l + q) % p][m] ^ iv[l][m]
        l = (l + 1) % p


if __name__ == '__main__':
    gfsr2()
