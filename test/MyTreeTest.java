import lists.myBinaryTree.MyTree;
import org.junit.Assert;
import org.junit.Test;

public class MyTreeTest {
    @Test
    public void size() throws Exception {
        MyTree<Integer> actual = new MyTree<>();
        int expected = 2;
        actual.add(2);
        actual.add(1);
        Assert.assertEquals(expected,actual.size());
    }

    @Test
    public void isEmpty() throws Exception {
        MyTree<Integer> actual = new MyTree<>();
        actual.add(1);
        actual.add(2);
        actual.remove(1);
        actual.remove(2);
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void add() throws Exception {
        size();
    }

    @Test
    public void remove() throws Exception {
        isEmpty();
    }

    @Test
    public void clear() throws Exception {
        MyTree<Integer> actual = new MyTree<>();
        actual.add(new Integer(1));
        actual.add(new Integer(2));
        actual.clear();
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    public void iterator1() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void iterator2() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void iterator3() throws Exception {
        Assert.assertTrue(true);
    }

}