package com.nashss.se.concertmemories.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nashss.se.concertmemories.utils.NullUtils.ifNotNull;

public class CollectionUtils {
    private CollectionUtils() { }

    public static <TElement> List<TElement> copyToList(Collection<TElement> collectionToWrap) {
        return ifNotNull(collectionToWrap, () -> new ArrayList<>(collectionToWrap));
    }
}