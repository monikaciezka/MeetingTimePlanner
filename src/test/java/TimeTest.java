import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TimeTest {

    @Test
    public void testIfEqual(){
        Time time1 = new Time("08:30");
        Time time2 = new Time("08:30");
        assertEquals(time1, time2);
    }

    @Test
    public void testAdding() {
        Time time1 = new Time("23:00");
        Time time2 = new Time("02:48");
        assertEquals(new Time("01:48"), time1.add(time2));
    }

    @Test
    public void testToString(){
        Time time = null;
        try {
            time = new Time(3, 45);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(time.toString(), "03:45");
    }

    @Test
    public void testIsEarlier(){
        Time time = new Time("10:30");
        Time earlierTime = new Time("07:45");
        assertEquals(earlierTime.isEarlierEqual(time), true);
    }

    @Test
    public void testIsLater(){
        Time time = new Time("13:25");
        Time laterTime = new Time("13:25");
        assertEquals(laterTime.isLaterEqual(time), true);
    }

    @Test
    public void testInTimeRange() {
        Time time = new Time("13:15");
        Meeting meeting = new Meeting("14:00", "14:30");
        assertEquals(time.inTimeRange(meeting), false);
    }

    @Test
    public void testGetEarlier(){
        Time time = new Time("13:25");
        Time laterTime = new Time("13:50");
        assertEquals(laterTime.findEarlierTime(time), time);
    }
}
