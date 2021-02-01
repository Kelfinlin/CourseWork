
public class LinkedListDeque {
    /* the same name as well as the function in the lecture */
    public DequeNode sentinel;


    public LinkedListDeque(){
        sentinel = new DequeNode(null,7,null);
    }
    public LinkedListDeque(int t){
        sentinel = new DequeNode(null,7,null);
        sentinel.next = new DequeNode(null,t,null);
    }
    public void addfirst(int t){
        /* this variable is to inherit the sentinel.next's information */
        DequeNode formerfirst = sentinel.next;
        sentinel.next = new DequeNode(sentinel,t,formerfirst);
    }
    public void addlast(int t){
        
    }

    private class DequeNode{
        public DequeNode ahead;
        public int item;
        public DequeNode next;
        public DequeNode(DequeNode a,int i,DequeNode n){
            ahead = a;
            item = i;
            next = n;
        }
    }
}
