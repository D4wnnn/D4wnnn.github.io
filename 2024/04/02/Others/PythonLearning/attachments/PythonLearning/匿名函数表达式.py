def add(i, j):
    return i + j


a = 1

# 注意这里可以调用变量a
b = lambda k: add(a, k)
print(b(3))

