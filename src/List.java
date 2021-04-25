import java.util.Arrays;
import java.util.NoSuchElementException;

public class List<E> {
    private static final int DEFAULT_CAPACITY = 1;

    private Object[] elements;
    private int size = 0;

    public List(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E e){
        if(size == elements.length){
            ensureCapacity();
        }
        elements[size++] = e;
    }

    @SuppressWarnings("unchecked")
    public E get(int i){
        if(i >= size || i < 0){
            throw new IndexOutOfBoundsException("Index " + i + ", Size " + i);
        }
        return (E) elements[i];
    }

    @SuppressWarnings("unchecked")
    public E remove(int i){
        if(i >= size || i < 0){
            throw new IndexOutOfBoundsException("Index " + i + ", Size " + i);
        }
        Object item = elements[i];
        int numElts = elements.length-(i+1);
        System.arraycopy(elements, i + 1, elements , i , numElts);
        size--;
        return (E) item;
    }

    @SuppressWarnings("unchecked")
    public E remove(E e){
//        if(i >= size || i < 0){
//            throw new IndexOutOfBoundsException("Index " + i + ", Size " + i);
//        }
        boolean itemPassed = false;
        for(int i = 0 ; i < elements.length -1 ; i++ ){
            if(elements[i].equals(e)){
                Object item = elements[i];

                int numElts = elements.length - (i + 1);
                System.arraycopy(elements, i + 1, elements, i, numElts);
                size--;
                return (E) item;
            }

        }
        throw new NoSuchElementException("Element does not exist");
    }

    public int size(){
        return size;
    }

    private void ensureCapacity() {
        int newSize = elements.length + 1;
        elements = Arrays.copyOf(elements, newSize);
    }

}
