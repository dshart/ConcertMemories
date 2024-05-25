package com.nashss.se.concertmemories.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.nashss.se.concertmemories.utils.NullUtils.ifNotNull;

public class CollectionUtils {
    private CollectionUtils() { }

    /**
     * copyToList.
     * @oaram collectionToWrap
     * @return List<TElement>
     */
    public static <TElement> List<TElement> copyToList(Collection<TElement> collectionToWrap) {
        return ifNotNull(collectionToWrap, () -> new ArrayList<>(collectionToWrap));
    }
}