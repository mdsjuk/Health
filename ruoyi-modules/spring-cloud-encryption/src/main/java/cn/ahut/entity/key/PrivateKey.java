package cn.ahut.entity.key;

import java.math.BigInteger;


public class PrivateKey {
    private BigInteger p;

    public PrivateKey(BigInteger p) {
        this.p = p;
    }

    public BigInteger getP() {
        return p;
    }
}

