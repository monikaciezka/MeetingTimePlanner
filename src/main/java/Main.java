import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter path to first file");
        String calendar1 = in.nextLine();
        System.out.println("enter path to second file");
        String calendar2 = in.nextLine();
        System.out.println("enter meeting time in format 00:30");
        String duration = in.nextLine();
        ObjectMapper objectMapper = new ObjectMapper();
        WorkDay workDay1;
        WorkDay workDay2;
        Time timeDuration = new Time(duration);
        try {
            workDay1 = objectMapper.readValue(new File(calendar1), WorkDay.class);
            workDay2 = objectMapper.readValue(new File(calendar2), WorkDay.class);
            workDay1.findMeetingFreeList();
            workDay2.findMeetingFreeList();
            workDay1.printMeetingList(workDay1.proposeMeetingTimes(workDay2, timeDuration));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
