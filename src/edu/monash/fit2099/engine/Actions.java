package edu.monash.fit2099.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * A thin wrapper for <code>java.util.ArrayList&lt;Action&gt;</code> that does not allow nulls to be added.
 *
 */
public class Actions implements Iterable<Action> {
	private ArrayList<Action> actions = new ArrayList<Action>();

	/**
	 * Constructs an empty list.
	 */
	public Actions() {
	}

	/**
	 * Constructs a collection containing a single (non-null) Action.
	 * 
	 * @param action the Action to add
	 */
	public Actions(Action action) {
		add(action);
	}

	/**
	 * Appends another Actions to this one. This works like a copy constructor (but
	 * note that it will do a shallow copy: the references are copied but the
	 * Actions themselves are not cloned.
	 * 
	 * @param actions the Actions to append
	 */
	public void add(Actions actions) {
		for (Action action : actions) {
			add(action);
		}
	}

	/**
	 * Appends a single Action to this collection, if it is non-null.  If it is null, then it is ignored.
	 * 
	 * @param action the Action to append
	 * @return true unconditionally
	 */
	public boolean add(Action action) {
		if (action != null) {
			actions.add(action);
		}
		return true;
	}

	/**
	 * Returns a read only Iterator for a copy of the underlying collection. The
	 * means you can call add() and remove() while iterating without modifiying the
	 * iterated collecion.
	 * 
	 * Implementing this method means that Actions implements the Iterable interface, which allows
	 * you to use it in a foreach, e.g. <code>for (Action a: actions) {
	 *    ...
	 *    </code>
	 * 
	 * @return an iterator
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Action> iterator() {
		return Collections.unmodifiableList(new ArrayList<Action>(actions)).iterator();
	}

	/**
	 * Delete the contents of this collection, leaving it empty.
	 */
	public void clear() {
		actions.clear();
	}

	/**
	 * Count the number of Actions in the collection.
	 * 
	 * @return the number of Actions in the collection.
	 */
	public int size() {
		return actions.size();
	}

	/**
	 * Remove the first occurrence of an Action from the collection, if it is present.  If it is not present, the list is unchanged.
	 * @param action the Action to remove
	 */
	public void remove(Action action) {
		actions.remove(action);
	}

	/**
	 * Return the <code>i</code>'th Action in the collection.
	 * 
	 * @param i index of the Action to retrieve
	 * @return the <code>i</code>'th Action in the collection
	 * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
	 */
	public Action get(int i) {
		return actions.get(i);
	}
}
