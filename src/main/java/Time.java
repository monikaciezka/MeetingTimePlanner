
public class Time {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) throws Exception {
        if(hours > 23 || hours < 0 || minutes < 0 || minutes > 59){
            throw new Exception("Invalid values");
        } else {
            this.hours = hours;
            this.minutes = minutes;
        }
    }

    public Time(String time) {
        hours = Integer.parseInt(time.substring(0, 2));
        minutes =Integer.parseInt(time.substring(3, 5));
    }

    private Time(){}

    @Override
    public String toString() {
        String timeString;
        if(hours < 10){
            timeString = "0" + hours;
        } else {
            timeString = Integer.toString(hours);
        }
        timeString += ":";
        if(minutes < 10) {
            timeString += "0" + minutes;
        } else {
            timeString += Integer.toString(minutes);
        }
        return timeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hours == time.hours &&
                minutes == time.minutes;
    }

    public Time add(Time time) {
        Time result = new Time();
        result.hours = (hours + time.hours + (minutes + time.minutes)/60)%24;
        result.minutes = (minutes + time.minutes)%60;
        return result;
    }

    public boolean isEarlierEqual(Time time){
        if(hours < time.getHours()){
            return true;
        } else if( hours == time.getHours() && minutes <= time.getMinutes()){
            return true;
        } else {
            return false;
        }
    }

    public boolean isLaterEqual(Time time) {
        if( hours >  time.getHours()){
            return true;
        } else if ( hours == time.getHours() && minutes >= time.getMinutes()){
            return true;
        } else {
            return false;
        }
    }

    public boolean inTimeRange(Meeting meeting){
        if( this.isLaterEqual(meeting.getStart()) && this.isEarlierEqual(meeting.getEnd())){
            return true;
        } else {
            return false;
        }
    }

    public Time findEarlierTime(Time time){
         if(this.isEarlierEqual(time)){
             return this;
         } else {
             return time;
         }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
