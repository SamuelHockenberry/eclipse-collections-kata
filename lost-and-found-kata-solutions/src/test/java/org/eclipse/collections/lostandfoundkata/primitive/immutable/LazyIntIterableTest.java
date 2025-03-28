/*
 * Copyright (c) 2021 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.lostandfoundkata.primitive.immutable;

import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.LazyBooleanIterable;
import org.eclipse.collections.api.LazyByteIterable;
import org.eclipse.collections.api.LazyCharIterable;
import org.eclipse.collections.api.LazyDoubleIterable;
import org.eclipse.collections.api.LazyFloatIterable;
import org.eclipse.collections.api.LazyIntIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.LazyLongIterable;
import org.eclipse.collections.api.LazyShortIterable;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.IntBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LazyIntIterableTest
{

    private LazyIntIterable lazyIntIterable = IntInterval.oneTo(5).asLazy();

    @Test
    @Tag("SOLUTION")
    public void converting()
    {
        // Converter methods
        MutableIntList list = this.lazyIntIterable.toList();
        Assertions.assertEquals(IntLists.mutable.with(1, 2, 3, 4, 5), list);

        MutableIntSet set = this.lazyIntIterable.toSet();
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4, 5), set);

        MutableIntBag bag = this.lazyIntIterable.toBag();
        Assertions.assertEquals(IntBags.mutable.with(1, 2, 3, 4, 5), bag);
    }

    @Test
    @Tag("SOLUTION")
    public void filtering()
    {
        // Inclusive
        LazyIntIterable select = this.lazyIntIterable.select(each -> each % 2 == 0);
        Assertions.assertEquals(IntInterval.evensFromTo(2, 4), select.toList());

        // Exclusive
        LazyIntIterable reject = this.lazyIntIterable.reject(each -> each % 2 == 0);
        Assertions.assertEquals(IntInterval.oddsFromTo(1, 5), reject.toList());
    }

    @Test
    @Tag("SOLUTION")
    public void transforming()
    {
        // Lazy transformations
        LazyBooleanIterable lazyBooleanIterable = this.lazyIntIterable.collectBoolean(each -> each % 2 == 0);
        var expectedBooleanList = BooleanLists.mutable.with(false, true, false, true, false);
        Assertions.assertEquals(expectedBooleanList, lazyBooleanIterable.toList());

        LazyByteIterable lazyByteIterable = this.lazyIntIterable.collectByte(each -> (byte) each);
        var expectedByteList = ByteLists.mutable.with((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        Assertions.assertEquals(expectedByteList, lazyByteIterable.toList());
        long byteSum = lazyByteIterable.sum();
        Assertions.assertEquals(15L, byteSum);

        LazyCharIterable lazyCharIterable = this.lazyIntIterable.collectChar(each -> (char) each);
        var expectedCharList = CharLists.mutable.with((char) 1, (char) 2, (char) 3, (char) 4, (char) 5);
        Assertions.assertEquals(expectedCharList, lazyCharIterable.toList());
        long charSum = lazyCharIterable.sum();
        Assertions.assertEquals(15L, charSum);

        LazyShortIterable lazyShortIterable = this.lazyIntIterable.collectShort(each -> (short) each);
        var expectedShortList = ShortLists.mutable.with((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);
        Assertions.assertEquals(expectedShortList, lazyShortIterable.toList());
        long shortSum = lazyShortIterable.sum();
        Assertions.assertEquals(15L, shortSum);

        LazyFloatIterable lazyFloatIterable = this.lazyIntIterable.collectFloat(each -> (float) each);
        var expectedFloatList = FloatLists.mutable.with(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        Assertions.assertEquals(expectedFloatList, lazyFloatIterable.toList());
        double floatSum = lazyFloatIterable.sum();
        Assertions.assertEquals(15.0, floatSum, 0.0);

        LazyLongIterable lazyLongIterable = this.lazyIntIterable.collectLong(each -> (long) each);
        var expectedLongList = LongLists.mutable.with(1L, 2L, 3L, 4L, 5L);
        Assertions.assertEquals(expectedLongList, lazyLongIterable.toList());
        long longSum = lazyLongIterable.sum();
        Assertions.assertEquals(15L, longSum);

        LazyDoubleIterable lazyDoubleIterable = this.lazyIntIterable.collectDouble(each -> (long) each);
        var expectedDoubleList = DoubleLists.mutable.with(1.0, 2.0, 3.0, 4.0, 5.0);
        Assertions.assertEquals(expectedDoubleList, lazyDoubleIterable.toList());
        double doubleSum = lazyDoubleIterable.sum();
        Assertions.assertEquals(15.0, doubleSum, 0.0);

        LazyIterable<String> lazyIterable = this.lazyIntIterable.collect(Integer::toString);
        var expectedStringList = Lists.mutable.with("1", "2", "3", "4", "5");
        Assertions.assertEquals(expectedStringList, lazyIterable.toList());
    }

    @Test
    @Tag("SOLUTION")
    public void calculating()
    {
        // Math Operations
        long sum = this.lazyIntIterable.sum();
        int max = this.lazyIntIterable.max();
        int maxIfEmpty = this.lazyIntIterable.maxIfEmpty(0);
        int min = this.lazyIntIterable.min();
        int minIfEmpty = this.lazyIntIterable.minIfEmpty(0);
        double average = this.lazyIntIterable.average();
        double averageIfEmpty = this.lazyIntIterable.averageIfEmpty(0.0);
        double median = this.lazyIntIterable.median();
        double medianIfEmpty = this.lazyIntIterable.medianIfEmpty(0.0);
        IntSummaryStatistics stats = this.lazyIntIterable.summaryStatistics();

        Assertions.assertEquals(15, sum);
        Assertions.assertEquals(1, min);
        Assertions.assertEquals(min, minIfEmpty);
        Assertions.assertEquals(5, max);
        Assertions.assertEquals(max, maxIfEmpty);
        Assertions.assertEquals(3.0, average, 0.0);
        Assertions.assertEquals(average, averageIfEmpty, 0.0);
        Assertions.assertEquals(3.0, median, 0.0);
        Assertions.assertEquals(median, medianIfEmpty, 0.0);
        Assertions.assertEquals(sum, stats.getSum());
        Assertions.assertEquals(min, stats.getMin());
        Assertions.assertEquals(max, stats.getMax());
        Assertions.assertEquals(average, stats.getAverage(), 0.0);
        Assertions.assertEquals(this.lazyIntIterable.size(), stats.getCount());
    }
}
