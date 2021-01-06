package cn.ahut.untils.BGNOp;

import it.unisa.dia.gas.jpbc.Element;

public class DoAdd {
    public static Element add(Element c1, Element c2) {
        return c1.mul(c2).getImmutable();
    }
}
