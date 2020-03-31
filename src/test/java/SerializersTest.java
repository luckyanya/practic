import org.junit.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class SerializersTest {
    @Test
    public void serializeObject() throws IOException, ClassNotFoundException {
        List<Animals> animalList = new ArrayList<Animals>();
        animalList.add(new Animals("Deer", AnimalTypes.WILD, 12,
                Arrays.asList(new Food("Grass", 4))));
        animalList.add(new Animals("Bug", AnimalTypes.DOMESTIC, 1,
                Arrays.asList(new Food("Leaf", 10))));
        animalList.add(new Animals("Wolf", AnimalTypes.WILD, 20,
                Arrays.asList(new Food("Deer", 9))));
        Serializers.serialize(animalList, Paths.get("object.bin1"));
        assertEquals(animalList, Serializers.deserialize(Paths.get("object.bin")));
    }
    @Test
    public void serializeObjectEmpty() throws IOException, ClassNotFoundException {
        Serializers.serialize(Collections.emptyList(), Paths.get("object.bin"));
        assertEquals(Collections.emptyList(), Serializers.deserialize(Paths.get("object.bin")));
    }
    @Test
    public void serializeObjectTwoLists() throws IOException, ClassNotFoundException {
        List<Animals> animalList1 = new ArrayList<Animals>();
        animalList1.add(new Animals("Deer", AnimalTypes.WILD, 12,
                Arrays.asList(new Food("Grass", 4))));
        animalList1.add(new Animals("Bug", AnimalTypes.DOMESTIC, 1,
                Arrays.asList(new Food("Leaf", 10))));
        animalList1.add(new Animals("Wolf", AnimalTypes.WILD, 20,
                Arrays.asList(new Food("Deer", 9))));
        List<Animals> animalList2 = new ArrayList<Animals>();
        animalList2.add(new Animals("Wolf", AnimalTypes.WILD, 21,
                Arrays.asList(new Food("Leaf", 4))));
        animalList2.add(new Animals("Deer", AnimalTypes.DOMESTIC, 1,
                Arrays.asList(new Food("Bug", 10))));
        animalList2.add(new Animals("Wolf", AnimalTypes.WILD, 20,
                Arrays.asList(new Food("Deer", 9))));
        Serializers.serialize(animalList1, Paths.get("object.animal1"));
        Serializers.serialize(animalList2, Paths.get("object.animal2"));
        assertNotEquals(animalList1, Serializers.deserialize(Paths.get("object.animal2")));
        assertNotEquals(animalList2, Serializers.deserialize(Paths.get("object.animal1")));
    }
    @Test
    public void serializeObjectError() throws ClassNotFoundException{
        try {
            Serializers.deserialize(Paths.get("emptytest"));
            fail();
        } catch (NoSuchFileException e) {
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void serializeObjectMyself() throws IOException, ClassNotFoundException {
        List<Animals> animalList = new ArrayList<Animals>();
        animalList.add(new Animals("Deer", AnimalTypes.WILD, 12,
                Arrays.asList(new Food("Grass", 4))));
        animalList.add(new Animals("Bug", AnimalTypes.DOMESTIC, 1,
                Arrays.asList(new Food("Leaf", 10))));
        animalList.add(new Animals("Wolf", AnimalTypes.WILD, 20,
                Arrays.asList(new Food("Deer", 9))));

        Serializers.serializeMyself(animalList, Paths.get("object.bin1"));

        assertEquals(animalList, Serializers.deserializeMyself(Paths.get("object.bin")));
    }

    @Test
    public void serializeObjectEmptyMyself() throws IOException, ClassNotFoundException {
        Serializers.serialize(Collections.emptyList(), Paths.get("object.bin"));
        assertEquals(Collections.emptyList(), Serializers.deserializeMyself(Paths.get("object.bin")));
    }

    @Test
    public void serializeObjectTwoListsMyself() throws IOException, ClassNotFoundException {
        List<Animals> animalList1 = new ArrayList<Animals>();
        animalList1.add(new Animals("Deer", AnimalTypes.WILD, 12,
                Arrays.asList(new Food("Grass", 4))));
        animalList1.add(new Animals("Bug", AnimalTypes.DOMESTIC, 1,
                Arrays.asList(new Food("Leaf", 10))));
        animalList1.add(new Animals("Wolf", AnimalTypes.WILD, 20,
                Arrays.asList(new Food("Deer", 9))));
        List<Animals> animalList2 = new ArrayList<Animals>();
        animalList2.add(new Animals("Wolf", AnimalTypes.WILD, 21,
                Arrays.asList(new Food("Leaf", 4))));
        animalList2.add(new Animals("Deer", AnimalTypes.DOMESTIC, 1,
                Arrays.asList(new Food("Bug", 10))));
        animalList2.add(new Animals("Wolf", AnimalTypes.WILD, 20,
                Arrays.asList(new Food("Deer", 9))));
        Serializers.serializeMyself(animalList1, Paths.get("animalMyself1"));
        Serializers.serializeMyself(animalList2, Paths.get("animalMyself2"));
        assertEquals(animalList1, Serializers.deserializeMyself(Paths.get("object.animalMyself1")));
        assertEquals(animalList2, Serializers.deserializeMyself(Paths.get("object.animalMyself2")));
        assertNotEquals(animalList1, Serializers.deserializeMyself(Paths.get("object.animalMyself2")));
        assertNotEquals(animalList2, Serializers.deserializeMyself(Paths.get("object.animalMyself1")));
    }

    @Test
    public void serializeObjectErrorMyself() throws ClassNotFoundException{
        try {
            Serializers.deserializeMyself(Paths.get("emptytest"));
            fail();
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            fail();
        }
    }
}