# 旋转一次，生成一次随机数的测试
class MT19937:
    # w: word 大小
    # n: 参与梅森旋转的随机数个数
    # m: 中间词, 在[1,n)
    # r: [0,w-1)
    w, n, m, r = 32, 624, 397, 31

    a = 0x9908b0df
    u, d = 11, 0xffffffff
    s, b = 7, 0x9d2c5680
    t, c = 15, 0xefc60000
    l = 18
    f = 1812433253

    def __init__(self):
        seed = 0
        self.state = 0
        # 初始化，生成n行w列的向量
        self.iv = [0] * self.n
        self.iv[0] = seed
        for i in range(1, self.n):
            prev = self.iv[i - 1]
            temp = self.f * (prev ^ (prev >> (self.w - 2))) + i
            self.iv[i] = temp & 0xffffffff  # 转换为int32

    def twister(self):
        # 梅森旋转
        i = self.state
        y = (self.iv[i] & 0x80000000) + (self.iv[(i + 1) % self.n] & 0x7fffffff)
        if y % 2:
            y >>= 1
            y ^= self.a
        else:
            y >>= 1
        self.iv[i] = self.iv[(i + self.m) % self.n] ^ y

    def generate(self):
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
    mt = MT19937()
    tank = set()
    kLen = 1000000
    odd = 0
    for _ in range(kLen):
        t = mt()
        odd += 1 if t % 2 else 0
        tank.add(t)
        # print(t)
    print(odd / kLen)
    print(f"set: {len(tank)}")
    print(f"kLen: {kLen}")
