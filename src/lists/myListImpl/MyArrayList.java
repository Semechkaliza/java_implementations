package lists.myListImpl;

import java.util.Collection;
import java.util.ListIterator;

public class MyArrayList extends MyAbstractList {

    private final static int BASIC_MEMORY = 16;
    private final static double MEMOTY_BORDER = 0.75;
    private final static double MEMORY_RESIZE = 1.2;
    private int realMemory;
    private Object [] arr;
    private int number;

    private boolean checkMemory(){
        return !(number >= realMemory*0.75);
    }

    private void updateMemory(){
        int realMemoryTemp = (int)(realMemory * MEMORY_RESIZE);
        Object[] temp = new Object[realMemoryTemp];
        for (int i = 0; i < number; i++){
            temp[i] = arr[i];
        }
    }

    public MyArrayList(){
        realMemory = BASIC_MEMORY;
        arr = new Object[realMemory];
        number = 0;
    }

    public MyArrayList(Collection collection){
        if(!collection.isEmpty()){
            if(collection.size() > (BASIC_MEMORY * MEMOTY_BORDER)){
                realMemory = (int)(collection.size()*MEMORY_RESIZE);
            } else {
                realMemory = BASIC_MEMORY;
            }
            arr = new Object[realMemory];
            for (Object obj : collection) {
                add(obj);
            }
        } else {
            realMemory = BASIC_MEMORY;
            arr = new Object[realMemory];
            number = 0;
        }
    }

    @Override
    public int size() {
        return number;
    }

    @Override
    public boolean isEmpty() {
        return !(number > 0);
    }

    @Override
    public Object[] toArray() {
        Object [] temp = new Object[number];
        for(int i = 0; i < number; i++){
            temp[i] = arr[i];
        }
        return temp;
    }

    @Override
    public boolean add(Object o) {
        if(!checkMemory()){
            updateMemory();
        }
        arr[number] = o;
        number++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!isEmpty()) {
            int i = indexOf(o);
            if(i != -1){
                remove(i);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        for(int i = 0; i< number; i++){
            arr[i] = null;
        }
        number = 0;
    }

    @Override
    public Object get(int i) {
        if(inBorder(i)){
            return arr[i];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object set(int i, Object o) {
        if(inBorder(i)){
            Object temp = arr[i];
            arr[i] = o;
            return temp;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int i, Object o) {
        if(!checkMemory()){
            updateMemory();
        }
        for(int j = number; j > i; j--){
            arr[j] = arr[j - 1];
        }
        arr[i] = o;
        number++;
    }

    @Override
    public Object remove(int i) {
        if (inBorder(i)) {
            Object temp = arr[i];
            for (int j = i; j < number; j++) {
                arr[j] = arr[j + 1];
            }
            number--;
            return temp;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(Object o) {
        if (!isEmpty()) {
            for(int i = 0; i < number; i++){
                if(arr[i].equals(o)){
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        if (!isEmpty()) {
            for(int i = number -1; i >= 0; i--){
                if(arr[i].equals(o)){
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    @Override
    public ListIterator listIterator(final int i) {
        return new ListIterator() {

            private int counter = i;

            @Override
            public boolean hasNext() {
                return (counter < number);
            }

            @Override
            public Object next() {
                return get(counter++);
            }

            @Override
            public boolean hasPrevious() {
                return (counter > 0);
            }

            @Override
            public Object previous() {
                return get(--counter);
            }

            @Override
            public int nextIndex() {
                return counter;
            }

            @Override
            public int previousIndex() {
                return counter - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(counter);
            }

            @Override
            public void set(Object o) {
                MyArrayList.this.set(counter,o);
            }

            @Override
            public void add(Object o) {
                add(o);
            }
        };
    }
}
