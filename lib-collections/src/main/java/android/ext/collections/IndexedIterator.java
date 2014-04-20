package android.ext.collections;

import android.ext.core.Objects;

import java.util.Iterator;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class IndexedIterator<E> implements Iterator<E> {
    private final Indexed<E> mIndexed;
    private int mCursor;

    public IndexedIterator(Indexed<E> indexed) {
        mIndexed = Objects.notNull(indexed);
        mCursor = -1;
    }

    @Override
    public boolean hasNext() {
        return mCursor + 1 < mIndexed.size();
    }

    @Override
    public E next() {
        return mIndexed.get(++mCursor);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
