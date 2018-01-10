package lists.myBinaryTree;

import java.util.Iterator;

public class MyTree <T extends Comparable>{

    private class Leaf <T extends Comparable>{
        private T object;
        private Leaf root;
        private Leaf left;
        private Leaf right;

        public Leaf() {
        }

        public Leaf(T object, Leaf root) {
            this.object = object;
            this.root = root;
            this.left = null;
            this.right = null;
        }

        public Leaf(T object, Leaf root, Leaf left, Leaf right) {
            this.object = object;
            this.root = root;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Leaf{" +
                    "object=" + object +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Leaf<T> root;
    private int numbers;

    private Leaf<T> find(T o){
        if(isPresent(o)) {
            Leaf<T> x = root;
            while (x != null) {
                int compareIndex = o.compareTo(x.object);
                if (compareIndex == 0) {
                    break;
                } else {
                    x=compareIndex<0 ? x.left:x.right;
                }
            }
            return x;
        } else {
            return null;
        }
    }

    public MyTree(){
        root = null;
        numbers = 0;
    }

    public int size() {
        return numbers;
    }

    public boolean isEmpty() {
        return !(numbers > 0);
    }

    public boolean add(T o) {
        if (!isPresent(o)) {
            Leaf<T> temp = root, place = null;
            while (temp != null) {
                int compareIndex = o.compareTo(temp.object);
                place = temp;
                temp=compareIndex<0 ? temp.left:temp.right;
            }
            Leaf<T> newLeaf = new Leaf<T>(o, place);
            if (place == null) {
                root = newLeaf;
            } else {
                if (o.compareTo(place.object) < 0) {
                    place.left = newLeaf;
                } else {
                    place.right = newLeaf;
                }
            }
            numbers++;
            return true;
        } else {
            return false;
        }
    }

    public boolean isPresent(T o){
        return isPresent(root, o);
    }

    public boolean isPresent(Leaf<T> start, T o){
        Leaf<T> temp = start;
        while (temp != null) {
            int compareIndex = o.compareTo(temp.object);
            if(compareIndex == 0){
                return true;
            }
            if (compareIndex < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return false;
    }

    public boolean remove(T o) {
        Leaf<T> x = find(o);
        if(x != null) {
            if((x.right == null)||(x.left == null)) {
                if((x.right == null)&&(x.left == null)){
                        root = null;
                } else {
                    if(x == root){
                        if (x.right == null) {
                            root = x.left;
                            x.left.root = null;
                            x.left = null;
                        } else {
                            root = x.right;
                            x.right.root = null;
                            x.right = null;
                        }
                    } else{
                        if (x.right == null) {
                            if (x.root.right == x) {
                                x.root.right = x.left;
                                x.root = null;
                            } else {
                                x.root.left = x.left;
                                x.root = null;
                            }
                        } else {
                            if (x.root.right == x) {
                                x.root.right = x.right;
                                x.root = null;
                            } else {
                                x.root.left = x.right;
                                x.root = null;
                            }
                        }
                    }
                }
                numbers--;
            } else {
                Leaf<T> iter = x.right, temp = x.left;
                if(x == root){
                    root = x.right;
                } else {
                    if (x.root.right == x) {
                        x.root.right = x.right;
                    } else {
                        x.root.left = x.right;
                    }
                }
                x.root = null;
                x.left = null;
                x.right = null;

                while (iter.left != null) {
                    iter = iter.left;
                }

                iter.left = temp;
                temp.root = iter;
                numbers--;
            }
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        root.root = null;
        root = null;
        numbers = 0;
    }

    public Iterator iterator1() {
        return new Iterator() {

            private int counter = 0;
            private Leaf<T> temp = root;

            @Override
            public boolean hasNext() {
                return  (counter < numbers);
            }

            @Override
            public Object next() {
                if(temp != null){
                    if(temp.right != null){
                        temp = temp.right;
                    } else {
                        if(temp.left != null){
                            temp = temp.left;
                        } else {
                            Leaf<T> flag = temp;
                            while (isPresent(temp.left, flag.object)) {
                                temp = temp.root;
                            }
                            temp = temp.left;
                        }
                    }
                    counter++;
                    return  temp.object;
                } else {
                    return null;
                }
            }
        };
    }

    public Iterator iterator2() {
        return new Iterator() {

            private int counter = 0;
            private Leaf<T> temp = root;

            @Override
            public boolean hasNext() {
                return  (counter < numbers);
            }

            @Override
            public Object next() {
                if(temp != null){
                    if(temp.left != null){
                        temp = temp.left;
                    } else {
                        if(temp.right != null){
                            temp = temp.right;
                        } else {
                            Leaf<T> flag = temp;
                            while (isPresent(temp.right, flag.object)) {
                                temp = temp.root;
                            }
                            temp = temp.right;
                        }
                    }
                    counter++;
                    return  temp.object;
                } else {
                    return null;
                }
            }
        };
    }

    public Iterator iterator3() {
        return new Iterator() {

            private int maxLayer = getMaxLayer(root, size());
            private int nowLayer = maxLayer;
            private int layerCount = 0;
            private int counter = 0;
            private Leaf<T> temp = root;

            private int getMaxLayer(Leaf<T> start, int counter) {
                int max = 1, now = 1;
                for (int i = 0 ; i < counter ;i++) {
                    if (start != null) {
                        Leaf<T> temp = null;

                        if (temp.left != null) {
                            temp = temp.left;
                            now++;
                            max = max(max, now);
                        } else {
                            if (temp.right != null) {
                                temp = temp.right;
                                now++;
                                max = max(max, now);
                            } else {
                                Leaf<T> flag = temp;
                                while (isPresent(temp.right, flag.object)) {
                                    temp = temp.root;
                                    now--;
                                }
                                temp = temp.right;
                            }
                        }
                    }
                }
                return max;
            }

            private int max(int max, int now){
                if(max < now){
                    return now;
                } else {
                    return max;
                }
            }

            @Override
            public boolean hasNext() {
                return( counter < size());
            }

            @Override
            public Object next() {
                if(temp != null){
                    Leaf<T> place = nextOnLayer();

                    if(place == null){
                        while (place == null) {
                            nowLayer--;
                            place = nextOnLayer();
                        }
                        return null;
                    } else {
                        counter++;
                        temp = place;
                        return  temp.object;
                    }
                } else {
                    return null;
                }
            }

            private Leaf<T> nextOnLayer(){
                int tempLayer = 1;
                int tempLayerCounter = 0;

                while (temp.left != null) {
                        if (temp.right != null) {
                            temp = temp.right;
                            tempLayer++;
                        } else {
                            if (temp.left != null) {
                                temp = temp.left;
                                tempLayer++;
                            } else {
                                Leaf<T> flag = temp;
                                while (isPresent(temp.left, flag.object)) {
                                    temp = temp.root;
                                    tempLayer--;
                                }
                                temp = temp.left;
                                tempLayer++;
                            }
                        }

                        if(tempLayer == nowLayer){
                            if(tempLayerCounter > layerCount){
                                return temp;
                            } else {
                                tempLayerCounter++;
                            }
                        }

                }
                return null;
            }
        };
    }

    @Override
    public String toString() {
        if (root == null) {
            return "null";
        } else {
            return root.toString();
        }
    }
}
