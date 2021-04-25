import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = { "meetingFreeList" })
public class WorkDay {

    private Time start;
    private Time end;
    private List<Meeting> meetingList;
    private List<Meeting> meetingFreeList;

    public WorkDay() {
        meetingList = new ArrayList<Meeting>();
        meetingFreeList = new ArrayList<Meeting>();
        findMeetingFreeList();
    }

    public WorkDay(Time start, Time end) {
        this.start = start;
        this.end = end;
        meetingList = new ArrayList<Meeting>();
        meetingFreeList = new ArrayList<Meeting>();
    }

    public WorkDay(String start, String end) {
        this.start = new Time(start);
        this.end = new Time(end);
        meetingList = new ArrayList<Meeting>();
        meetingFreeList = new ArrayList<Meeting>();
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "start=" + start +
                ", end=" + end +
                ", meetingList=" + meetingList +
                ", meetingFreeList=" + meetingFreeList +
                '}';
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public void addMeeting(Meeting meeting){
        meetingList.add(meeting);
        findMeetingFreeList();
    }

    public void findMeetingFreeList(){
        meetingFreeList = new ArrayList<>();
        if(!meetingList.isEmpty()){
            int iterator;
            if(!start.equals(meetingList.get(0).getStart())) {
                meetingFreeList.add(new Meeting(start, meetingList.get(0).getStart()));
            }
            for (iterator = 0; iterator < meetingList.size() - 1; iterator++) {
                if(!meetingList.get(iterator).getEnd().equals(meetingList.get(iterator+1).getStart())) {
                    meetingFreeList.add(new Meeting(meetingList.get(iterator).getEnd(), meetingList.get(iterator + 1).getStart()));
                }
            }
            if(!end.equals(meetingList.get(meetingList.size()-1))){
                meetingFreeList.add(new Meeting(meetingList.get(meetingList.size()-1).getEnd(), end));
            }

        }
    }

    public void printMeetingList(List<Meeting> list){
        for (Meeting meeting: list) {
            System.out.println(meeting);

        }
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public List<Meeting> getMeetingFreeList() {
       return meetingFreeList;
    }


    public List<Meeting> proposeMeetingTimes(WorkDay workDay, Time duration){
        List<Meeting> proposeMeetingList = new ArrayList<>();
        for (Meeting meetingFree : workDay.meetingFreeList) {
            for(Meeting meetingFreeCaller : meetingFreeList){
                if(meetingFree.getStart().inTimeRange(meetingFreeCaller)){
                    if(meetingFree.getStart().add(duration).inTimeRange(meetingFreeCaller)){
                        proposeMeetingList.add(new Meeting(meetingFree.getStart(), meetingFree.getEnd().findEarlierTime(meetingFreeCaller.getEnd())));
                    }
                }else if (meetingFreeCaller.getStart().inTimeRange(meetingFree)){
                    if(meetingFreeCaller.getStart().add(duration).inTimeRange(meetingFree)){
                        proposeMeetingList.add(new Meeting(meetingFreeCaller.getStart(), meetingFreeCaller.getEnd().findEarlierTime(meetingFree.getEnd()) ));
                    }
                }
            }
        }
        return proposeMeetingList;

    }
}
