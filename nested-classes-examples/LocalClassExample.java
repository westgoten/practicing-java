public class LocalClassExample {
    private static String regularExpression = "[^0-9]";

    public static void validatePhoneNumbers(String phoneNumber1,
    String phoneNumber2) {
        final int numberLength = 10;

        class PhoneNumber {
            private String formattedPhoneNumber = null;

            public PhoneNumber(String phoneNumber) {
                String currentNumber = phoneNumber.replaceAll(regularExpression,
                "");
                if (currentNumber.length() == numberLength)
                    formattedPhoneNumber = currentNumber;
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }

            public void printOriginalNumbers() {
                System.out.println("Original numbers are " + phoneNumber1 +
                " and " + phoneNumber2);
            }
        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        myNumber1.printOriginalNumbers();

        if (myNumber1.getNumber() == null)
            System.out.println("First number is invalid.");
        else
            System.out.println("First number is " + myNumber1.getNumber());

        if (myNumber2.getNumber() == null)
            System.out.println("Second number is invalid.");
        else
            System.out.println("Second number is " + myNumber2.getNumber());
    }

    public static void main(String[] args) {
        validatePhoneNumbers("123-456-7890", "456-7890");
    }
}
