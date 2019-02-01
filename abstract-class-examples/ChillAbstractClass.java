abstract public class ChillAbstractClass {
    private int howMuchChill;

    public ChillAbstractClass(int howMuchChill) {
        this.howMuchChill = howMuchChill;
    }

    abstract public void poseTime();

    public static void stayCool(int howMuchChill) {
        System.out.printf("Hey, man. I'm %d%% chill!\n", howMuchChill);
    }

    public int getHowMuchChill() { return howMuchChill; }
}
