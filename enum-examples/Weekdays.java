
public class Weekdays {
    private Day day;

    public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY,
        SUNDAY}

    public Weekdays(Day day) {
        this.day = day;
    }

    public void myOpinionAboutThisOne() {
        switch (this.day) {
            case MONDAY:
                System.out.println("MONDAY is great!");
                break;
            case TUESDAY:
                System.out.println("TUESDAY is good.");
                break;
            case WEDNESDAY:
                System.out.println("WEDNESDAY is normal.");
                break;
            case THURSDAY:
                System.out.println("THURSDAY is pretty nice.");
                break;
            case FRIDAY:
                System.out.println("FRIDAY is really good!");
                break;
            default:
                System.out.println("Weekends are awesome!");
        }
    }

    public static void main(String[] args) {
        for (Day d : Day.values()) {
            new Weekdays(d).myOpinionAboutThisOne();
        }
    }
}
