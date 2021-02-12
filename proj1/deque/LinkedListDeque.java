package deque;
public class LinkedListDeque<Gloup> {
    /* the same name as well as the function in the lecture */
    public DequeNode sentinel;
    private DequeNode last;
    int size;


    public LinkedListDeque(){
        sentinel = new DequeNode(null,null,null);
        last = new DequeNode(sentinel,null,sentinel);
        sentinel.next = last;
        sentinel.ahead = last;
        size = 0;
    }
    public LinkedListDeque(Gloup t){
        DequeNode D = new DequeNode(null,t,null);
        sentinel = new DequeNode(null,null,D);
        last = new DequeNode(D,null,sentinel);
        D.next = last;
        D.ahead = sentinel;
        sentinel.ahead = last;
        size = 0;
    }
    public void addFirst(Gloup t){
        /* this variable is to inherit the sentinel.next's information */
        DequeNode D = new DequeNode(sentinel,t,sentinel.next);
        sentinel.next.ahead = D;
        sentinel.next = D;
        size ++;
    }
    public void addLast(Gloup t){
        DequeNode D = new DequeNode(last.ahead,t,last);
        last.ahead.next = D;
        last.ahead = D;
        size ++;
    }
    public boolean isEmpty(){
        if (size <= 0){
            return true;
        }
        return false;
    }
    public int size(){
        if (size < 0){
            return 0;
        }
        return size;
    }
    public void printDeque(){
        DequeNode currentnode = sentinel;   /* plays the same function as the former one */
        while (currentnode.next != last) {
            System.out.print(currentnode.next.item + " ");
        }
        System.out.println();
    }
    public Gloup removeFirst(){
        DequeNode deletenode = sentinel.next;
        sentinel.next.next.ahead = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return deletenode.item;
    }
    public Gloup removeLast(){
        DequeNode deleteNode = last.ahead;
        last.ahead.ahead.next = last;
        last.ahead = last.ahead.ahead;
        size --;
        return deleteNode.item;
    }
    public Gloup get(int index){
        int counter = 0;  /* to count the node's rank */
        DequeNode currentnode = sentinel.next;
        while (index != counter){
            currentnode = currentnode.next;
            counter ++;
        }
        return currentnode.item;
    }


    private boolean hasNext(DequeNode t){
        if (t.next == null){
            return false;
        }
        return true;
    }

    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque) {
            int counter = 0;
            int t = 0;
            DequeNode current = sentinel;
            DequeNode compare = ((LinkedListDeque) o).sentinel;
            while (current.next != last & t < ((LinkedListDeque)o).size) {
                if (current.next.item == compare.next.item){
                    counter ++;
                    t ++;
                }
                current = current.next;
                compare = compare.next;

            }
            if (counter == size){
                return true;
            }
        }
        return false;
    }
    public Gloup 


    private class DequeNode{
        public DequeNode ahead;
        public Gloup item;
        public DequeNode next;
        public DequeNode(DequeNode a,Gloup i,DequeNode n){
            ahead = a;
            item = i;
            next = n;
        }
    }
}
