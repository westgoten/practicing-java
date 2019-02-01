import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void main(String[] args) {
        Class<SomeAnnotatedClass> obj = SomeAnnotatedClass.class;
        for (Method method : obj.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Author.class)) {
                Annotation annotation = method.getAnnotation(Author.class);
                Author author = (Author) annotation;

                System.out.println("Method name: " + method.getName());
                System.out.println("Author name: " + author.name());
                System.out.println("Date: " + author.date());
                System.out.println();
            }
        }
    }
}
