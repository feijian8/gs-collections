/*
 * Copyright 2012 Goldman Sachs.
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

package com.gs.collections.impl;

import java.util.Random;

import com.gs.collections.impl.list.mutable.FastList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FastListAddTest
{
    @Test
    @Category(PerformanceTests.class)
    public void runFastListAdd()
    {
        this.runIntegerFastListAdd("integer");
        this.runLongFastListAdd("long");
        this.runIntegerFastListAdd("integer");
        this.runStringFastListAdd("string");
        this.runIntegerFastListAdd("integer");
        this.runLongFastListAdd("long");
        this.runIntegerFastListAdd("integer");
        this.runStringFastListAdd("string");

    }

    private void runIntegerFastListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Integer[] ints = new Integer[1000000];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = r.nextInt();
        }
        this.runFastListAdds(type, ints);
    }

    private void runLongFastListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        Long[] longs = new Long[1000000];
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = r.nextLong();
        }
        this.runFastListAdds(type, longs);
    }

    private void runStringFastListAdd(String type)
    {
        System.currentTimeMillis();
        Random r = new Random(123412123);
        String[] strings = new String[1000000];
        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = String.valueOf(r.nextLong());
        }
        this.runFastListAdds(type, strings);
    }

    private void runFastListAdds(String type, Object[] objects)
    {
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAdd(objects, 1000, 1000);
        }
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAdd(objects, 1000000, 10);
        }
        long now = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            this.runFastListAdd(objects, 1000000, 10);
        }
        long time = System.currentTimeMillis() - now;
        System.out.println("FastList, list size 1,000,000, " + type + " adds/msec: " + 1000000000 / time);
    }

    public void runFastListAdd(Object[] objects, int length, int runs)
    {
        for (int i = 0; i < runs; i++)
        {
            FastList<Object> list = FastList.newList();
            for (int j = 0; j < length; j++)
            {
                list.add(objects[j]);
            }
        }
    }
}
