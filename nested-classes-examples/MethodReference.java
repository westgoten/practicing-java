import java.util.Comparator;
import java.util.Arrays;

public class MethodReference {

    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public int getAge() {
            return this.age;
        }

        public static int compareByName(Person a, Person b) {
            return a.getName().compareTo(b.getName());
        }

        public static int compareByAge(Person a, Person b) {
            int ageA = a.getAge();
            int ageB = b.getAge();
            if (ageA > ageB)
                return -1;
            else if (ageA < ageB)
                return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Person[] personList = new Person[4];
        personList[2] = new Person("Jose", 34);
        personList[1] = new Person("Gabriel", 32);
        personList[0] = new Person("Diego", 27);
        personList[3] = new Person("Ana", 23);

        Arrays.sort(personList, Person::compareByName);
        for (int i = 0; i < 4; i++)
            System.out.println(personList[i].getName() + " " + personList[i].getAge());
    }
}
