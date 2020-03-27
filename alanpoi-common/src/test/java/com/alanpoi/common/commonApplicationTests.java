package com.alanpoi.common;

import com.alanpoi.common.util.AlanList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CommonApplication.class)
class commonApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void alanListTest() {
        AlanList<Integer> alanList = new AlanList();
        AlanList<Integer> alanList2 = new AlanList();
        alanList2.add(1);
        alanList2.add(99);
        int i = 0;
        do {
            alanList.add(i++);
        } while (i < 1000);

        System.out.println(alanList.contains(3));
        System.out.println(alanList.containsAll(alanList));

        System.out.println(alanList.size());
        System.out.println(alanList.get(1));
        System.out.println(alanList.removeAll(alanList2));
        System.out.println(alanList);
    }

}
