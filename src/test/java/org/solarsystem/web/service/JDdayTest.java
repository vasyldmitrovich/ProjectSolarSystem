package org.solarsystem.web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDdayTest {

    @Test
    void getTime() {
        double result = JDday.getTime("2000-01-01");
        Assertions.assertEquals(2451545.0,result);
    }
}