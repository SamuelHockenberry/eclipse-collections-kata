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

import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.list.primitive.MutableCharList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.primitive.MutableCharSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.CharSets;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointAdapter;
import org.eclipse.collections.impl.string.immutable.CodePointList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StringAdaptersTest
{
    private static final String THE_QUICK_BROWN_FOX = "The quick brown fox";

    private final CharAdapter charAdapter = Strings.asChars(THE_QUICK_BROWN_FOX);
    private final CodePointAdapter codePointAdapter = Strings.asCodePoints(THE_QUICK_BROWN_FOX);
    private final CodePointList codePointList = CodePointList.from(THE_QUICK_BROWN_FOX);

    @Test
    @Tag("SOLUTION")
    public void charAdapter()
    {
        CharAdapter upperCase = this.charAdapter.collectChar(Character::toUpperCase);
        Assertions.assertEquals(THE_QUICK_BROWN_FOX.toUpperCase(), upperCase.toString());

        CharAdapter lowerCase = this.charAdapter.collectChar(Character::toLowerCase);
        Assertions.assertEquals(THE_QUICK_BROWN_FOX.toLowerCase(), lowerCase.toString());

        CharAdapter lettersOnly = this.charAdapter.select(Character::isAlphabetic);
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());

        CharAdapter specialCharsOnly = this.charAdapter.reject(Character::isAlphabetic);
        Assertions.assertEquals("   ", specialCharsOnly.toString());

        // Converter methods
        MutableCharBag bag = lowerCase.toBag();
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
        MutableCharList list = lowerCase.toList().sortThis().distinct();
        var expectedCharList = CharLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedCharList, list);
        MutableCharSet set = lowerCase.toSet();
        var expectedCharSet = CharSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedCharSet,set);
    }

    @Test
    @Tag("SOLUTION")
    public void codePointAdapter()
    {
        CodePointAdapter upperCase = this.codePointAdapter.collectInt(Character::toUpperCase);
        Assertions.assertEquals("THE QUICK BROWN FOX", upperCase.toString());

        CodePointAdapter lowerCase = this.codePointAdapter.collectInt(Character::toLowerCase);
        Assertions.assertEquals("the quick brown fox", lowerCase.toString());

        CodePointAdapter lettersOnly = this.codePointAdapter.select(Character::isAlphabetic);
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());

        CodePointAdapter specialIntsOnly = this.codePointAdapter.reject(Character::isAlphabetic);
        Assertions.assertEquals("   ", specialIntsOnly.toString());

        // Converter methods
        MutableIntBag bag = lowerCase.toBag();
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
        MutableIntList list = lowerCase.toList().sortThis().distinct();
        var expectedIntList = IntLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntList, list);
        MutableIntSet set = lowerCase.toSet();
        var expectedIntSet = IntSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntSet, set);
    }

    @Test
    @Tag("SOLUTION")
    public void codePointList()
    {
        CodePointList upperCase = this.codePointList.collectInt(Character::toUpperCase);
        Assertions.assertEquals("THE QUICK BROWN FOX", upperCase.toString());

        CodePointList lowerCase = this.codePointList.collectInt(Character::toLowerCase);
        Assertions.assertEquals("the quick brown fox", lowerCase.toString());

        CodePointList lettersOnly = this.codePointList.select(Character::isAlphabetic);
        Assertions.assertEquals("Thequickbrownfox", lettersOnly.toString());

        CodePointList specialIntsOnly = this.codePointList.reject(Character::isAlphabetic);
        Assertions.assertEquals("   ", specialIntsOnly.toString());

        // Converter methods
        MutableIntBag bag = lowerCase.toBag();
        Assertions.assertEquals(3, bag.occurrencesOf(' '));
        MutableIntList list = lowerCase.toList().sortThis().distinct();
        var expectedIntList = IntLists.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntList, list);
        MutableIntSet set = lowerCase.toSet();
        var expectedIntSet = IntSets.mutable.with(' ', 'b', 'c', 'e', 'f', 'h', 'i', 'k', 'n', 'o', 'q', 'r', 't', 'u', 'w', 'x');
        Assertions.assertEquals(expectedIntSet, set);
    }
}
