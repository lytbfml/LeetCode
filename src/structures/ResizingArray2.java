package structures;

import java.util.*;

/**
 * Implementation of the java.util.List interface based on
 * an expandable array.  The implementation is complete except
 * for the subList() method.
 * <p>
 * The iterator is implemented to be independent of the backing
 * array.
 */
public class ResizingArray2<E> implements List<E> {
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Size of the list.
	 */
	private int numItems;
	
	/**
	 * Structures.Array in which elements are stored.
	 */
	private E[] theArray;
	
	/**
	 * Constructs a list with default initial capacity.
	 */
	public ResizingArray2() {
		// unsafe cast unavoidable
		theArray = (E[]) new Object[DEFAULT_CAPACITY];
		numItems = 0;
	}
	
	/**
	 * Constructs a list that initially contains the
	 * elements of the given collection.
	 *
	 * @param c the initial collection of elements
	 */
	public ResizingArray2(Collection<? extends E> c) {
		this();
		Iterator<? extends E> iter = c.iterator();
		while (iter.hasNext()) {
			add(iter.next());
		}
	}
	
	
	@Override
	public boolean add(E obj) {
		checkCapacity(numItems + 1);
		theArray[numItems++] = obj;
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		}
		Iterator<? extends E> iter = c.iterator();
		while (iter.hasNext()) {
			add(iter.next());
		}
		return true;
	}
	
	@Override
	public boolean remove(Object obj) {
		for (int pos = 0; pos < numItems; pos++) {
			if (obj == theArray[pos] || obj != null && obj.equals(theArray[pos])) {
				remove(pos);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void add(int pos, E obj) {
		checkIndexNonStrict(pos);
		checkCapacity(numItems + 1);
		
		// shift elements right to make space at pos
		for (int i = numItems; i > pos; i--) {
			theArray[i] = theArray[i - 1];
		}
		theArray[pos] = obj;
		numItems++;
	}
	
	@Override
	public boolean addAll(int pos, Collection<? extends E> c) {
		checkIndexNonStrict(pos);
		if (c.isEmpty()) {
			return false;
		}
		int newItems = c.size();
		checkCapacity(numItems + newItems);
		
		// shift elements over to make space for c.size() new elements at pos
		for (int i = numItems + newItems - 1; i > pos + newItems - 1; --i) {
			theArray[i] = theArray[i - newItems];
		}
		
		Iterator<? extends E> iter = c.iterator();
		int i = pos;
		while (iter.hasNext()) {
			theArray[i++] = iter.next();
		}
		numItems += newItems;
		return true;
	}
	
	@Override
	public E remove(int pos) {
		checkIndexStrict(pos);
		E returnVal = theArray[pos];
		
		// shift elements left to occupy position pos
		for (int j = pos + 1; j < numItems; ++j) {
			theArray[j - 1] = theArray[j];
		}
		theArray[numItems - 1] = null;
		--numItems;
		shrinkArray();
		return returnVal;
	}
	
	@Override
	public E set(int pos, E obj) {
		checkIndexStrict(pos);
		E returnVal = theArray[pos];
		theArray[pos] = obj;
		return returnVal;
	}
	
	@Override
	public E get(int pos) {
		checkIndexStrict(pos);
		return theArray[pos];
	}
	
	@Override
	public boolean contains(Object obj) {
		for (int i = 0; i < numItems; i++) {
			if (obj == theArray[i] || obj != null && obj.equals(theArray[i])) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> iter = c.iterator();
		while (iter.hasNext()) {
			if (!contains(iter.next())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int indexOf(Object obj) {
		for (int i = 0; i < numItems; i++) {
			if (obj == theArray[i] || obj != null && obj.equals(theArray[i])) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int lastIndexOf(Object obj) {
		for (int i = numItems - 1; i >= 0; i--) {
			if (obj == theArray[i] || obj != null && obj.equals(theArray[i])) {
				return i;
			}
		}
		return -1;
	} // lastIndexOf
	
	@Override
	public List<E> subList(int fromPos, int toPos) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int size() {
		return numItems;
	}
	
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	@Override
	public void clear() {
		theArray = (E[]) new Object[DEFAULT_CAPACITY];
		numItems = 0;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}
	
	@Override
	public ListIterator<E> listIterator() {
		return new ListItr();
	}
	
	@Override
	public ListIterator<E> listIterator(int pos) {
		checkIndexNonStrict(pos);
		return new ListItr(pos);
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null)
			throw new NullPointerException();
		boolean change = false;
		Iterator<E> iter = this.iterator();
		while (iter.hasNext())
			if (c.contains(iter.next())) {
				iter.remove();
				change = true;
			}
		return change;
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		if (c == null)
			throw new NullPointerException();
		boolean change = false;
		Iterator<E> iter = this.iterator();
		while (iter.hasNext())
			if (!c.contains(iter.next())) {
				iter.remove();
				change = true;
			}
		return change;
	}
	
	@Override
	// obj may be a linked list.
	public boolean equals(Object obj) {
		if ((obj == null) || !(obj instanceof List<?>))
			return false;
		List<?> list = (List<?>) obj;
		if (list.size() != numItems)
			return false;
		Iterator<?> iter = list.iterator();
		for (int i = 0; i < numItems; i++) {
			if (!iter.hasNext())
				return false;
			Object t = iter.next();
			if (!(t == theArray[i] || t != null && t.equals(theArray[i])))
				return false;
		}
		return true;
	}
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(theArray, numItems);
	}
	
	@Override
	public <T> T[] toArray(T[] arr) {
		if (numItems > arr.length)
			arr = Arrays.copyOf(arr, numItems);
		for (int i = 0; i < numItems; i++)
			arr[i] = (T) theArray[i];
		if (numItems < arr.length)
			arr[numItems] = null;
		return arr;
	}
	
	@Override
	public int hashCode() {
		// Code copied from List API documentation
		int hashCode = 1;
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			E obj = iter.next();
			hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
		}
		return hashCode;
	}
	
	/**
	 * Expands the capacity of the array if necessary to accommodate
	 * the the given total number of elements
	 *
	 * @param newNumItems the new size of the collection
	 */
	private void checkCapacity(int newNumItems) {
		if (theArray.length < newNumItems) {
			// Double as necessary
			int newCapacity = theArray.length;
			while (newCapacity < newNumItems) {
				newCapacity *= 2;
			}
			theArray = Arrays.copyOf(theArray, newCapacity);
			
		}
	}
	
	/**
	 * Shrinks the capacity of the array of the number
	 * of elements drops below one-fourth of capacity.
	 */
	private void shrinkArray() {
		if (numItems < (theArray.length / 4) && theArray.length >= 2 * DEFAULT_CAPACITY) {
			theArray = Arrays.copyOf(theArray, theArray.length / 2);
		}
	}
	
	
	/**
	 * Checks that the given index is at least 0 and strictly less than
	 * the list size and throws an IndexOutOfBoundsException if not.
	 *
	 * @param pos the index to check
	 */
	private void checkIndexStrict(int pos) {
		if (pos >= numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: "
					+ numItems);
	}
	
	/**
	 * Checks whether the given index is between 0 and list size, inclusive,
	 * and throws an IndexOutOfBoundsException if not.
	 *
	 * @param pos the index to check
	 */
	private void checkIndexNonStrict(int pos) {
		if (pos > numItems || pos < 0)
			throw new IndexOutOfBoundsException("Index: " + pos + ", Size: "
					+ numItems);
	}
	
	
	/**
	 * Inner class for the Collection Iterator Interface.
	 * (Note this class is not generic; it refers to the
	 * enclosing class's type parameter E.
	 */
	private class Itr implements Iterator<E> {
		/**
		 * Index of next element to be returned by next().
		 */
		protected int index;
		
		/**
		 * Index of element most recently returned by next().  A value of -1
		 * means that a remove() operation is not allowed.
		 */
		protected int prev;
		
		/**
		 * Constructs an iterator starting at the beginning of the list.
		 */
		public Itr() {
			index = 0;
			prev = -1;
		}
		
		@Override
		public boolean hasNext() {
			return index < numItems;
		}
		
		@Override
		public E next() {
			if (index >= numItems) {
				throw new NoSuchElementException();
			}
			E ret = get(index);
			prev = index;
			++index;
			return ret;
		}
		
		@Override
		public void remove() {
			if (prev < 0) {
				throw new IllegalStateException();
			}
			ResizingArray2.this.remove(prev);
			index = prev;
			prev = -1;
		}
	} // Itr
	
	/**
	 * Inner class for the List Iterator Interface.
	 */
	private class ListItr extends Itr implements ListIterator<E> {
		
		// A ListIterator has no current element.  The index variable
		// has a value that is between 0 and size inclusive and
		// indicates the cursor position, where theArray[index] is the
		// next element, if index < size, and theArray[index - 1] is the
		// previous element, if index > 0
		
		// As in the implementation of Itr, the variable prev is the index
		// of the most recently returned element, whether it was returned
		// by next() or by previous().  This variable has a value -1 when
		// operations on the recently returned element (such as remove() and
		// and set() are not allowed.
		
		// Alternating calls to next() and previous()
		// will return the same element repeatedly.
		
		/**
		 * Constructs a ListIterator starting at the beginning of the list
		 */
		public ListItr() {
			super();
		}
		
		/**
		 * Constructs a list iterator beginning at the given initial position.
		 * Precondition: 0 <= start <= numItems.
		 *
		 * @param start initial position for the iterator
		 */
		public ListItr(int start) {
			super();
			index = start;
		}
		
		@Override
		public boolean hasPrevious() {
			return index > 0;
		}
		
		@Override
		public E previous() {
			if (index <= 0) {
				throw new NoSuchElementException();
			}
			--index;
			E ret = get(index);
			prev = index;
			return ret;
		}
		
		@Override
		public int nextIndex() {
			return index;
		}
		
		@Override
		public int previousIndex() {
			return index - 1;
		}
		
		@Override
		public void set(E obj) {
			if (prev < 0) {
				throw new IllegalStateException();
			}
			ResizingArray2.this.set(prev, obj);
		}
		
		@Override
		public void add(E obj) {
			ResizingArray2.this.add(index, obj);
			index++;
			prev = -1;
		}
		
	} // ListItr
} // ResizingArray