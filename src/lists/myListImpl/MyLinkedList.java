package lists.myListImpl;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList extends MyAbstractList {

    private class Node{

        private Object object;
        private Node next;
        private Node previous;

        public Node(Object object, Node next, Node previous) {
            this.object = object;
            this.next = next;
            this.previous = previous;
        }

        @Override
        public boolean equals(Object o) {
            if (this.object == o) return true;
            if (Object.class != o.getClass()) return false;

            return object.equals(o);
        }

        @Override
        public int hashCode() {
            return object.hashCode();
        }
    }

    private Node first;
    private Node last;
    private int number;

    public MyLinkedList(){
        first = null;
        last = null;
        number = 0;
    }

    public MyLinkedList(Collection collection){
        for (Object object : collection) {
            add(object);
        }
    }

    private Node getNode(int i) {
        if(inBorder(i)){
            Node temp = first;
            for (int j = 0; j < i;j++){
                temp = temp.next;
            }
            return temp;
        } else {
            throw new IndexOutOfBoundsException();
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
        Object [] objects = new Object[size()];
        Iterator iterator = iterator();
        for (int i = 0 ; iterator.hasNext(); i++) {
            objects[i] = iterator.next();
        }
        return objects;
    }

    @Override
    public boolean add(Object o) {
        Node temp = new Node(o, null, last);
        if(last != null) {
            last.next = temp;
            last = temp;
        } else {
            first = temp;
            last = temp;
        }
        number++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(isEmpty()){
            return false;
        } else {

            int index = indexOf(o);

            if (index == -1) {
                return false;
            } else {
                Node temp = getNode(index);
                Node previous = temp.previous;
                Node next = temp.next;

                if(temp == first){
                    first = next;
                }

                if(temp == last){
                    last = previous;
                }

                if(previous != null) {
                    previous.next = next;
                }

                if(next != null) {
                    next.previous = previous;
                }

                temp.previous = null;
                temp.next = null;

                number--;
                return true;
            }
        }
    }

    @Override
    public void clear() {
        if(!isEmpty()) {
            Node temp = first;
            while (temp != null){
                remove(first.object);
                temp = first;
            }

        }
    }

    @Override
    public Object get(int i) {
        if (inBorder(i)) {
            return getNode(i).object;
        } else {
            return null;
        }
    }


    @Override
    public Object set(int i, Object o) {
        if(inBorder(i)) {
            Node temp = getNode(i);
            Object object = temp.object;
            temp.object = o;
            return object;
        } else {
            return null;
        }
    }

    @Override
    public void add(int i, Object o) {
        if(inBorder(i)){
            Node temp = getNode(i);
            Node previous = temp.previous;

            Node insert = new Node(o,temp,previous);

            temp.previous = insert;
            previous.next = insert;

            number++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object remove(int i) {
        if(inBorder(i)){
            Node temp = getNode(i);
            Node previous = temp.previous;
            Node next = temp.next;

            if(temp == first){
                first = next;
            }

            if(temp == last){
                last = previous;
            }

            if(previous != null) {
                previous.next = next;
            }

            if(next != null) {
                next.previous = previous;
            }

            temp.previous = null;
            temp.next = null;

            number--;
            return temp;
        } else {
            return null;
        }
    }

    @Override
    public int indexOf(Object o) {
        if (!isEmpty()) {
            Node temp = first;
            for (int counter = 0; temp != null; counter++) {
                if(temp.equals(o)){
                    return counter;
                }else {
                    temp = temp.next;
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
            Node temp = last;
            for (int counter = number - 1; temp != null; counter--) {
                if(temp.equals(o)){
                    return counter;
                }else {
                    temp = temp.previous;
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
                Object temp = get(counter++);
                return temp;
            }

            @Override
            public boolean hasPrevious() {
                return (counter > 0);
            }

            @Override
            public Object previous() {
                Object temp = get(--counter);
                return temp;
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
                MyLinkedList.this.remove(counter);
            }

            @Override
            public void set(Object o) {
                MyLinkedList.this.set(counter,o);
            }

            @Override
            public void add(Object o) {
                add(o);
            }
        };
    }

}
