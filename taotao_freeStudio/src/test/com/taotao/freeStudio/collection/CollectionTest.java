package com.taotao.freeStudio.collection;

import com.taotao.freeStudio.test.collection.ConcurrentHashSet;
import org.junit.Test;

import java.util.Set;

public class CollectionTest {


    @Test
    public void testConcurrentHashSet(){
        Set<String> set = new ConcurrentHashSet<>();
    }
}
