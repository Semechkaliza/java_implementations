import lists.myListImpl.MyArrayList;
import org.junit.Assert;

import java.util.*;

public class MyArrayListTest {

    @org.junit.Test
    public void constructor() throws Exception {

        List<Object> expect = new ArrayList<>();
        expect.add(new Integer(1));
        expect.add(new Integer(2));
        expect.add(new Integer(3));
        List actual = new MyArrayList(expect);

        for (int i = 0; i < actual.size();i++) {
            Assert.assertEquals(expect.get(i),actual.get(i));
        }
    }

    @org.junit.Test
    public void size() throws Exception {
        int expect = 2;
        List list =  new MyArrayList();
        Object object = new Object();
        list.add(object);
        list.add(new Object());
        int actual = list.size();
        Assert.assertEquals(expect,actual);

    }

    @org.junit.Test
    public void isEmpty() throws Exception {
        Assert.assertTrue(new MyArrayList().isEmpty());
    }

    @org.junit.Test
    public void iterator() throws Exception {

        List actualList = new MyArrayList();
        List expectedList = new ArrayList();
        Object object = new Object();
        actualList.add(object);
        expectedList.add(object);
        object = new Object();
        actualList.add(object);
        expectedList.add(object);
        object = new Object();
        actualList.add(object);
        expectedList.add(object);
        Iterator actualIterator = actualList.iterator();
        Iterator expectedIterator = expectedList.iterator();
        while (actualIterator.hasNext()||expectedIterator.hasNext()) {
            Assert.assertEquals(expectedIterator.next(), actualIterator.next());
        }
    }

    @org.junit.Test
    public void add() throws Exception {

        List list =  new MyArrayList();
        Object object = new Object();
        list.add(new Object());
        list.add(object);
        Assert.assertTrue(list.contains(object));
    }

    @org.junit.Test
    public void remove() throws Exception {

        List list =  new MyArrayList();
        Object object = new Object();
        list.add(object);
        list.remove(object);
        int expected = -1;
        Assert.assertEquals(expected,list.indexOf(object));
    }

    @org.junit.Test
    public void clear() throws Exception {

        List list =  new MyArrayList();
        Object object = new Object();
        list.add(object);
        list.add(new Object());
        list.clear();
        Assert.assertTrue(list.isEmpty());
    }

    @org.junit.Test
    public void get() throws Exception {

        List list =  new MyArrayList();
        Object object = new Object();
        list.add(new Object());
        list.add(object);

        int objectIndex = 1;
        Assert.assertEquals(object,list.get(objectIndex));
    }

    @org.junit.Test
    public void set() throws Exception {

        List list =  new MyArrayList();
        Object object = new Object();
        list.add(new Object());
        list.add(object);
        Object object2 = new Object();
        int objectIndex = 1;
        Assert.assertEquals(object, list.set(objectIndex,object2));
    }

    @org.junit.Test
    public void indexOf() throws Exception {

        List list =  new MyArrayList();
        Object object = new Object();
        list.add(new Object());
        list.add(object);
        list.add(new Object());
        int expected = 1;
        Assert.assertEquals(expected,list.indexOf(object));
    }

    @org.junit.Test
    public void lastIndexOf() throws Exception {
        List list = new MyArrayList();
        Object object = new Object();
        list.add(object);
        list.add(new Object());
        list.add(object);
        list.add(new Object());
        list.add(object);
        list.add(new Object());
        int objectLastIndex = 4;
        Assert.assertEquals(objectLastIndex, list.lastIndexOf(object));
    }

    @org.junit.Test
    public void listIterator() throws Exception {
        List expect = new ArrayList();
        expect.add(new Object());
        expect.add(new Object());
        List actual = new MyArrayList(expect);
        ListIterator expectIterator = expect.listIterator();
        ListIterator actualIterator = actual.listIterator();
        while (expectIterator.hasNext() || actualIterator.hasNext()){
            Assert.assertEquals(expectIterator.next(), actualIterator.next());
        }
    }
}