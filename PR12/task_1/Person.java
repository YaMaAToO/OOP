import java.io.*;

public class Person implements Serializable {
    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public void savePerson(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
            System.out.println("Person has been serialized to " + filePath);
        } catch (IOException e) {
            System.out.println("Error while serializing: " + e.getMessage());
        }
    }

    public static Person loadPerson(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Person) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while deserializing: " + e.getMessage());
            return null;
        }
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age + ", City: " + city;
    }

    public static void main(String[] args) {
        String filePath = "person.ser";
        Person person = new Person("Hero", 18, "Cherkasy");
        person.savePerson(filePath);

        Person loadedPerson = Person.loadPerson(filePath);
        if (loadedPerson != null) {
            System.out.println("Deserialized Person: " + loadedPerson);
        }
    }
}
