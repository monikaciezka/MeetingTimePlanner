public class Meeting {

    private Time start;
    private Time end;

    public Meeting(Time start, Time end) {
        this.start = start;
        this.end = end;
    }
    public Meeting(String start, String end){
        this.start = new Time(start);
        this.end = new Time(end);
    }

    public Meeting() {
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Meeting{ " +
                "start = " + start +
                ", end = " + end +
                " }";
    }
}
