import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Animals implements Serializable {
    private String name;
    private AnimalTypes type;
    private int age;
    private List<Food> arrayOfFood;
    public Animals(String name, AnimalTypes type, int age, List<Food> arrayOfFood) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.arrayOfFood = arrayOfFood;
    }
    String getName() {
        return name;
    }
    AnimalTypes getType() {
        return type;
    }
    int getAge() {
        return age;
    }
    List<Food> getArrayOfFood() {
        return arrayOfFood;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animal = (Animals) o;
        return age == animal.age &&
                Objects.equals(name, animal.name) &&
                type == animal.type &&
                Objects.equals(arrayOfFood, animal.arrayOfFood);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, type, age, arrayOfFood);
    }
    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", age=" + age +
                ", arrayOfFood=" + arrayOfFood +
                '}';
    }
}