public class CoolClass extends ChillAbstractClass {

    public CoolClass(int howMuchChill) {
        super(howMuchChill);
    }

    @Override
    public void poseTime() {
        System.out.println(";)");
    }

    public static void main(String[] args) {
        CoolClass imCool = new CoolClass(99);
        imCool.poseTime();
        stayCool(imCool.getHowMuchChill());
    }
}
