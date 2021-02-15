package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAddFirst(){
        ArrayDeque test1 = new ArrayDeque();
        test1.addFirst(0);
        test1.addFirst("newlymade");
        test1.addFirst(7);
        assertEquals(test1.get(0),7);
        assertEquals(test1.get(1),"newlymade");
        assertEquals(test1.get(2),0);
    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque test2 = new ArrayDeque();
        test2.removeFirst();
        test2.addFirst(0);
        test2.addFirst(7);
        test2.addFirst(9);
        int a = (int) test2.removeFirst();
        int b = (int) test2.removeFirst();
        int c = (int) test2.removeFirst();
        assertEquals(a,9);
        assertEquals(b,7);
        assertEquals(c,0);
    }

    @Test
    public void testGet(){
        ArrayDeque test3 = new ArrayDeque();
        test3.removeFirst();
        test3.addFirst(0);
        test3.addFirst(7);
        test3.addFirst(9);
        assertEquals(test3.get(0),9);
        assertEquals(test3.get(1),7);
        assertEquals(test3.get(2),0);
    }

    @Test
    public void testAddLast(){
        ArrayDeque test4 = new ArrayDeque();
        test4.removeLast();
        test4.addLast(0);
        test4.addLast(7);
        test4.addLast(9);
        assertEquals(test4.get(0),0);
        assertEquals(test4.get(1),7);
        assertEquals(test4.get(2),9);
    }

    @Test
    public void testEquals(){
        ArrayDeque test5 = new ArrayDeque();
        LinkedListDeque test6 = new LinkedListDeque();
        test5.addLast(5);
        test5.addLast(6);
        test5.addLast(7);
        test6.addLast(5);
        test6.addLast(6);
        test6.addLast(7);
        assertTrue(test5.equals(test6));
    }
}
