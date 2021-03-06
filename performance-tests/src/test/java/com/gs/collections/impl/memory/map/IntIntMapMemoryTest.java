/*
 * Copyright 2013 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.memory.map;

import java.util.HashMap;

import com.gs.collections.api.block.function.Function0;
import com.gs.collections.api.block.procedure.primitive.IntProcedure;
import com.gs.collections.api.list.primitive.IntList;
import com.gs.collections.api.set.primitive.IntSet;
import com.gs.collections.impl.MemoryTests;
import com.gs.collections.impl.map.mutable.primitive.IntIntHashMap;
import com.gs.collections.impl.memory.MemoryTestBench;
import com.gs.collections.impl.memory.TestDataFactory;
import gnu.trove.map.hash.TIntIntHashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class IntIntMapMemoryTest
{
    @Test
    @Category(MemoryTests.class)
    public void memoryForScaledMaps()
    {
        for (int size = 0; size < 1000001; size += 25000)
        {
            this.memoryForScaledMaps(size);
        }
    }

    public void memoryForScaledMaps(int size)
    {
        MemoryTestBench.on(TIntIntHashMap.class)
                .printContainerMemoryUsage("IntIntMap", size, new TIntIntHashMapFactory(size));
        MemoryTestBench.on(IntIntHashMap.class)
                .printContainerMemoryUsage("IntIntMap", size, new IntIntHashMapFactory(size));
        MemoryTestBench.on(HashMap.class)
                .printContainerMemoryUsage("IntIntMap", size, new IntegerIntegerHashMapFactory(size));
    }

    public static class IntIntHashMapFactory implements Function0<IntIntHashMap>
    {
        private final IntSet data;

        public IntIntHashMapFactory(int size)
        {
            this.data = TestDataFactory.createRandomSet(size);
        }

        public IntIntHashMap value()
        {
            final IntIntHashMap map = new IntIntHashMap();
            this.data.forEach(new IntProcedure()
            {
                public void value(int each)
                {
                    map.put(each, each);
                }
            });
            return map;
        }
    }

    public static class TIntIntHashMapFactory implements Function0<TIntIntHashMap>
    {
        private final IntList data;

        public TIntIntHashMapFactory(int size)
        {
            this.data = TestDataFactory.create(size);
        }

        public TIntIntHashMap value()
        {
            final TIntIntHashMap map = new TIntIntHashMap();
            this.data.forEach(new IntProcedure()
            {
                public void value(int each)
                {
                    map.put(each, each);
                }
            });
            return map;
        }
    }

    public static class IntegerIntegerHashMapFactory implements Function0<HashMap<Integer, Integer>>
    {
        private final IntList data;

        public IntegerIntegerHashMapFactory(int size)
        {
            this.data = TestDataFactory.create(size);
        }

        public HashMap<Integer, Integer> value()
        {
            final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            this.data.forEach(new IntProcedure()
            {
                public void value(int each)
                {
                    map.put(each, each);
                }
            });
            return map;
        }
    }
}
