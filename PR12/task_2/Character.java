import java.io.*;

public class Character implements Serializable {
    private String name;
    private int energyLevel;
    private int hungerLevel;
    private transient String status;

    public Character(String name, int energyLevel, int hungerLevel) {
        this.name = name;
        this.energyLevel = energyLevel;
        this.hungerLevel = hungerLevel;
        updateStatus();
    }

    public void eat(String food) {
        switch (food.toLowerCase()) {
            case "apple":
                energyLevel = Math.min(energyLevel + 10, 100);
                hungerLevel = Math.max(hungerLevel - 5, 0);
                break;
            case "sandwich":
                energyLevel = Math.min(energyLevel + 25, 100);
                hungerLevel = Math.max(hungerLevel - 15, 0);
                break;
            default:
                System.out.println("Unknown food!");
        }
        updateStatus();
    }

    public void train(String exercise) {
        switch (exercise.toLowerCase()) {
            case "run":
                energyLevel = Math.max(energyLevel - 30, 0);
                hungerLevel = Math.min(hungerLevel + 20, 100);
                break;
            case "push-ups":
                energyLevel = Math.max(energyLevel - 15, 0);
                hungerLevel = Math.min(hungerLevel + 10, 100);
                break;
            default:
                System.out.println("Unknown exercise!");
        }
        updateStatus();
    }

    public void rest(int hours) {
        energyLevel = Math.min(energyLevel + (hours * 10), 100);
        updateStatus();
    }

    private void updateStatus() {
        if (energyLevel > 70) {
            status = "energetic";
        } else if (energyLevel < 30) {
            status = "tired";
        } else {
            status = "normal";
        }

        if (hungerLevel > 70) {
            status = "hungry";
        } else if (hungerLevel < 30) {
            status = "full";
        }
    }

    public void saveCharacter(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
            System.out.println("Character has been serialized to " + filePath);
        } catch (IOException e) {
            System.out.println("Error while serializing: " + e.getMessage());
        }
    }

    public static Character loadCharacter(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Character) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while deserializing: " + e.getMessage());
            return null;
        }
    }

    public String toString() {
        return "Name: " + name + ", Energy: " + energyLevel + ", Hunger: " + hungerLevel + ", Status: " + status;
    }

    public static void main(String[] args) {
        String filePath = "character.ser";
        Character character;

        File file = new File(filePath);
        if (file.exists()) {
            character = Character.loadCharacter(filePath);
            if (character != null) {
                System.out.println("Loaded character: " + character);

                character.rest(2);
                character.eat("apple");
                character.train("run");
                System.out.println("Character after actions: " + character);
            }
        } else {

            character = new Character("Hero", 80, 20);
            System.out.println("New character: " + character);

            character.eat("sandwich");
            character.train("push-ups");
            character.eat("apple");
            character.rest(3);
            character.train("run");
            System.out.println("Character after actions: " + character);
        }

        character.saveCharacter(filePath);
    }
}
