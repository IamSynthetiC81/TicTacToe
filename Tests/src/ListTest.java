import DynamicMemory.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest{
    List<Integer> UUT;

    @BeforeEach
    void setUp() {
        UUT = new List<>();
        UUT.add(1);
        UUT.add(0);
    }

    @Test
    @DisplayName("Test adding elements to the list")
    void testAdd() {
        UUT.add(1);
        assertAll(()->{
            assertEquals(1,  UUT.get(0));
            assertEquals(0,  UUT.get(1));
        });
    }

    @Test
    @DisplayName("Test adding elements to location")
    void testAddIntoPosition() {
        UUT.add(1,1);
        assertEquals(UUT.get(1),1,"Element should be added at the position 1 of the List");
    }

    @Test
    @DisplayName("Test changing order of elements")
    void testChangeOrder() {
        UUT.changeOrder(0,1);
        assertAll(()->{
            assertEquals(UUT.get(0),0);
            assertEquals(UUT.get(1),1);
        });
    }

    @Test
    @DisplayName("Test removal of elements by index")
    void testRemove() {
        int priorSize = UUT.size();
        int elementToRemoveIndex = 0;

        Integer elementToRemove = UUT.get(elementToRemoveIndex);

        UUT.remove(elementToRemoveIndex);

        for(int i = 0 ; i < UUT.size() ; i++ ){
            if(UUT.get(i).equals(elementToRemove)){
                fail("element is still present");
            }
        }
        assertTrue(true,"Element is removed");
    }

    @Test
    @DisplayName("Test removal of elements")
    void testRemoveElement() {

        UUT.add(1);
        UUT.add(1);
        UUT.add(0);
        UUT.add(1);
        UUT.add(2);
        UUT.add(1);
        UUT.add(3);

        Integer elementToRemove = 1;

        UUT.remove(elementToRemove);

        for(int i = 0 ; i < UUT.size() ; i++){
            if(UUT.get(i).equals(elementToRemove)){
                fail("Elements are not removed");
            }
        }
        assertTrue(true,"Elements are removed");
    }

    @Test
    @DisplayName("Test the emptying of the list")
    void empty() {
        UUT.add(1);
        UUT.add(1);
        UUT.add(0);
        UUT.add(1);
        UUT.add(2);
        UUT.add(1);
        UUT.add(3);

        int priorSize = UUT.size();

        UUT.empty();

        assertAll(()->{
            assertEquals(0, UUT.size());
            for(int i = 0 ; i < priorSize ; i++){
                int finalI = i;
                assertThrows(IndexOutOfBoundsException.class, ()-> UUT.get(finalI));
            }

        });

    }

}