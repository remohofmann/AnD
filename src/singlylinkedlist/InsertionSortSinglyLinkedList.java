package singlylinkedlist;

import java.util.Comparator;

public abstract class InsertionSortSinglyLinkedList<E> {

    protected ListItem<E> head; // sentinel head ListItem
    private ListItem<E> last; // reference to last item

    abstract int getSize();

    abstract ListItem<E> getTail();

    abstract ListItem<E> getLast();

    abstract ListItem<E> setLast(ListItem<E> item);

    public void insertionsort(Comparator<? super E> comp) {
        if (getSize() > 1) {
            ListItem<E> loc = head.next;
            while (loc.next != getTail()) {
                ListItem<E> ptr = head;
                while (ptr != loc && comp.compare((E) ptr.next.data, (E) loc.next.data) <= 0)
                    ptr = ptr.next;
                if (ptr != loc) {
                    if (loc.next == getLast())
                        setLast(loc);
                    loc.moveNextAfter(ptr);
                } else
                    loc = loc.next;
            }
        }
    }
}
