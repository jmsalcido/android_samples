package org.otfusion.stupidrx.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PseudoClient {

    public List<String> getListOfHolidays() {
        Random random = new Random(System.currentTimeMillis());
        int randomNumber = random.nextInt(6 - 1 + 1) + 1;
        if (randomNumber > 4) {
            return getListOfHolidaysWithError();
        }
        try {
            Thread.sleep(randomNumber * 1000);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        return buildListOfHolidays();
    }

    private List<String> buildListOfHolidays() {
        return Arrays.asList("Work Day", "Independence Day", "Some other", "Another one",
                "Lets try with this one", "okay", "next", "St. Patric's Day", "Christmas",
                "Hanuka", "Kwanzaa", "Your Mom's Birthday");
    }

    public List<String> getListOfHolidaysWithError() {
        throw new AssertionError("ERROR BITCH!");
    }

}
