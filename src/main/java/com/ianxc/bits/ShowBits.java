package com.ianxc.bits;

public class ShowBits {
    static byte abs(byte x) {
        if (x == -128)
            return -128;
        return (byte) (x < 0 ? -x : x);
    }

    static byte signMagnitude(int x) {
        var y = twosComplement(x);
        if (y < 0) {
            return (byte) ~(y - 1);
        }
        return y;
    }

    static byte onesComplement(int x) {
        var y = twosComplement(x);
        if (y < 0) {
            return (byte) (twosComplement(y) - 1);
        }
        return y;
    }

    static byte twosComplement(int x) {
        return (byte) x;
    }

    // Leetcode parlance
    // (starting with the positive version)
    // original = sign-magnitude (flip MSB to 1)
    // inverse = one's complement (flip all bits)
    // complement = two's complement (flip all bits, then add 1)

    static void showBitsBasic(int x) {
        System.out.printf("%4d %8s %2x %3o\n",
                x, Integer.toBinaryString(x & 0xFF), x & 0xFF, x & 0xFF);
    }
}
