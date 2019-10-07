package com.accell.fixer.util;

import com.aaaccell.fixer.util.LocalDateHelper;
import com.aaaccell.fixer.util.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;

class LocalDateHelperTest {

    @Test
    void splitDateRangeTest() {
        Assertions.assertEquals(
                Arrays.asList(
                        new Tuple<>(D("2012-05-01"), D("2013-05-01")),
                        new Tuple<>(D("2013-05-02"), D("2014-05-02")),
                        new Tuple<>(D("2014-05-03"), D("2015-05-03")),
                        new Tuple<>(D("2015-05-04"), D("2016-05-03")), // Leap year 2016
                        new Tuple<>(D("2016-05-04"), D("2017-05-04")),
                        new Tuple<>(D("2017-05-05"), D("2018-05-05"))
                ),
                LocalDateHelper.splitDateRange(
                        D("2012-05-01"),
                        D("2018-05-05"),
                        Duration.ofDays(365)
                )
        );
    }

    private LocalDate D(String s) {
        return LocalDate.parse(s);
    }

}
