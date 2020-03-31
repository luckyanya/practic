import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class Serializers {
    public static void serialize(List<Animals> listOfAnimals, Path path) throws IOException {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(listOfAnimals);
        }
    }
    public static List<Animals> deserialize(Path path) throws IOException, ClassNotFoundException {
        List<Animals> newAnimalList;
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))) {
            newAnimalList = (List<Animals>) inputStream.readObject();
        }
        return newAnimalList;
    }

    public static void serializeMyself(List<Animals> listOfAnimals, Path path) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(path))) {
            dataOutputStream.writeInt(listOfAnimals.size());
            for (Animals currentAnimal : listOfAnimals) {
                dataOutputStream.writeUTF(currentAnimal.getName());
                dataOutputStream.writeUTF(currentAnimal.getType().name());
                dataOutputStream.writeInt(currentAnimal.getAge());
                dataOutputStream.writeInt(currentAnimal.getArrayOfFood().size());
                for (Food currentFood : currentAnimal.getArrayOfFood()) {
                    dataOutputStream.writeUTF(currentFood.getName());
                    dataOutputStream.writeInt(currentFood.getCount());
                }
            }
        }
    }

    public static List<Animals> deserializeMyself(Path path) throws IOException {
        List<Animals> newAnimalList = new ArrayList<>();
        String nameAnimal;
        AnimalTypes type;
        int age;
        String nameFood;
        int countFood;
        try (DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(path))) {
            int countOfAnimals = dataInputStream.readInt();
            for (int i = 0; i < countOfAnimals; i++) {
                nameAnimal = dataInputStream.readUTF();
                type = AnimalTypes.valueOf(dataInputStream.readUTF());
                age = dataInputStream.readInt();
                List<Food> arrayOfFood = new ArrayList<>();
                for (int j = 0; j < countOfAnimals; i++) {
                    nameFood = dataInputStream.readUTF();
                    countFood = dataInputStream.readInt();
                    arrayOfFood.add(new Food(nameFood, countFood));
                }
                newAnimalList.add(new Animals(nameAnimal, type, age, arrayOfFood));
            }
        }
        return newAnimalList;
    }
}