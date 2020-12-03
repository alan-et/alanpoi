package com.alanpoi.common.util;

import java.util.Random;

public class AObject extends Object {

    @Override
    public String toString() {
        Random random = new Random();
        return "com.$EtActivity.$proxy" + random.nextInt(1000);
    }
}
