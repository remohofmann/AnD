package singlylinkedlist;

import java.util.Comparator;

public abstract class MergeSortSinglyLinkedList<E> {

    abstract ListItem<E> getTail();
    abstract ListItem<E> setLast(ListItem<E> item);

    protected ListItem<E> mergesortImpl(Comparator<? super E> comp, int n, ListItem<E> ptr) {
        ListItem<E> ptrLeft = ptr;
        int numLeft = n / 2;
        for (int i = 0; i < numLeft - 1; i++)
            ptr = ptr.next;
        ListItem<E> ptrRight = ptr.next;
        ptr.next = getTail();
        if (numLeft > 1)
            ptrLeft = mergesortImpl(comp, numLeft, ptrLeft);
        if (n - numLeft > 1)
            ptrRight = mergesortImpl(comp, n - numLeft, ptrRight);
        return merge(comp, ptrLeft, ptrRight);
    }


    protected ListItem<E> merge(Comparator<? super E> comp, ListItem<E> ptr1, ListItem<E> ptr2) {
        ListItem<E> retVal = ptr1;
        if (comp.compare((E) ptr1.data, (E) ptr2.data) <= 0) {
            ptr1 = ptr1.next;
        } else {
            retVal = ptr2;
            ptr2 = ptr2.next;
        }
        ListItem<E> ptr = retVal;
        while (ptr1 != getTail() && ptr2 != getTail()) {
            if (comp.compare((E) ptr1.data, (E) ptr2.data) <= 0) {
                if (ptr.next != ptr1) ptr.setNext(ptr1);
                ptr1 = ptr1.next;
            } else {
                if (ptr.next != ptr2) ptr.setNext(ptr2);
                ptr2 = ptr2.next;
            }
            ptr = ptr.next;
        }
        if (ptr2 != getTail()) ptr.setNext(ptr2);
        else {
            ptr.setNext(ptr1);
            while (ptr.next != getTail()) ptr = ptr.next;
            setLast(ptr);
        }
        return retVal;
    }
}
