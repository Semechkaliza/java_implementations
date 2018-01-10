package lists.myListImpl;

import lists.MyList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class MyAbstractList implements MyList {

    protected boolean inBorder(int i){
        if((i >= 0) && (i < size())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {

            private int counter = 0;

            @Override
            public boolean hasNext() {
                if(counter < size()){
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public Object next() {
                Object temp = get(counter);
                counter++;
                return temp;
            }
        };
    }

    @Override
    public boolean addAll(Collection collection) {
        if (!collection.isEmpty()) {
            for (Object obj : collection) {
                add(obj);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        if(inBorder(i)) {
            int temp = i;
            for (Object object : collection) {
                add(temp, object);
                temp++;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override
    public List subList(int i, int i1) {
        if (inBorder(i) && inBorder(i1) && (i <= i1)) {
            List list = new MyArrayList();
            for (int j = i; j < i1; j++) {
                list.add(get(j));
            }
            return list;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        if(!isEmpty() && !collection.isEmpty()){
            boolean removed = false;
            for(int i = 0; i < size(); i++){
                if(!collection.contains(get(i))){
                    remove(i);
                    removed = true;
                }
            }
            return removed;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean removed = false;
        int index;
        for (Object obj: collection) {
            index = indexOf(obj);
            if(index != -1){
                remove(index);
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object obj: collection) {
            if(indexOf(obj) == -1){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if(!isEmpty()&&(objects.length > 0)){
            Object [] temp;
            if(objects.length >= size()){
                temp = objects;
            } else {
                temp = new Object[size()];
            }
            for(int i = 0 ; i < size(); i++){
                temp[i] = get(i);
            }
            if(objects.length > size()){
                temp[size()] = null;
            }
            return temp;
        } else {
            objects[0] = null;
            return objects;
        }
    }
}
