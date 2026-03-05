class MT19937x64:
    w, n, m, r = 64, 312, 156, 31

    a = 0xb5026f5aa96619e9
    u, d = 29, 0x5555555555555555
    s, b = 17, 0x71d67fffeda60000
    t, c = 37, 0xfff7eee000000000
    l = 43
    f = 6364136223846793005
    upperMask = 0xFFFFFFFF80000000
    lowerMask = 0x7FFFFFFF

    def __init__(self):
        seed = 0
        self.state = 0
        # 初始化，生成n行w列的向量
        self.iv = [0] * self.n
        self.iv[0] = seed
        for i in range(1, self.n):
            prev = self.iv[i - 1]
            temp = self.f * (prev ^ (prev >> (self.w - 2))) + i
            self.iv[i] = temp & 0xffffffffffffffff  # 转换为int64

    def twister(self):
        # 梅森旋转
        for i in range(self.n):
            y = (self.iv[i] & 0x80000000) + (self.iv[(i + 1) % self.n] & 0x7fffffff)
            if y % 2:
                y >>= 1
                y ^= self.a
            else:
                y >>= 1
            self.iv[i] = self.iv[(i + self.m) % self.n] ^ y

    def generate(self):
        if self.state == 0:
            self.twister()

        y = self.iv[self.state]
        y = y ^ (y >> self.u) & self.d
        y = y ^ (y << self.s) & self.b
        y = y ^ (y << self.t) & self.c
        y = y ^ (y >> self.l)

        self.state = (self.state + 1) % self.n

        return y

    def __call__(self):
        return self.generate()


if __name__ == "__main__":
    mt = MT19937x64()
    tank = set()
    kLen = 100000
    odd = 0
    for _ in range(kLen):
        t = mt()
        odd += 1 if t % 2 else 0
        tank.add(t)
        print(t)
    print(odd / kLen)
    print(f"set: {len(tank)}")
    print(f"kLen: {kLen}")
