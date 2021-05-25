package DynamicMemory;
import java.util.Arrays;
import java.util.Iterator;

public class List<E> implements Iterable<E>{
    private static final int DEFAULT_CAPACITY = 1;

    private Object[] elements;
    private int size = 0;

    public Iterator<E> iterator(){
        return new MyIterator();
    }

    class MyIterator implements Iterator<E>{
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public E next() {
            return get(index++);
        }
    }

    public List(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E e){
        if(size == elements.length){
            ensureCapacity();
        }
        elements[size++] = e;
    }

    public void add(E e, int i){
        if(i == size){
            ensureCapacity();
            size++;
        }else if(i > elements.length){
            throw new IndexOutOfBoundsException(i + "is out of bounds");
        }
        elements[i] = e;
    }

    public void changeOrder(int i, int j){
        if(i >= size || i < 0){
            throw new IndexOutOfBoundsException("Index " + i + ", Size " + size);
        }
        else if(j >= size || j < 0){
            throw new IndexOutOfBoundsException("Index " + j + ", Size " + size);
        }

        E tempElement = (E) elements[i];
        elements[i] = elements[j];
        elements[j] = tempElement;

    }

    public int getIndex(E e){
        for(int i = 0 ; i < elements.length ; i++){
            if(e == elements[i]){
                return i;
            }
        }
        return -1;
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
            throw new IndexOutOfBoundsException("Index " + i + ", Size " + size);
        }
        Object item = elements[i];
        int numElts = elements.length-(i+1);
        System.arraycopy(elements, i + 1, elements , i , numElts);
        size--;
        return (E) item;
    }

    public void empty(){
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void remove(E e){
        for(int i = 0 ; i < elements.length ; i++ ){
            if(elements[i].equals(e)){
                Object[] buffer = new Object[size - 1];
                for(int j = 0 ; j < i ; j++){
                    buffer[j] = elements[j];
                }
                for(int j = i ; j < size - 1; j++){
                    buffer[j] = elements[j+1];
                }
                elements = buffer;
                size--;
                i--;
            }
        }
        //throw new NoSuchElementException("Element does not exist");
    }

    public int size(){
        return size;
    }

    private void ensureCapacity() {
        int newSize = elements.length + 1;
        elements = Arrays.copyOf(elements, newSize);
    }

}
