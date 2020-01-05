package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;

class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    @BeforeEach
    void setUp() throws Exception {
        super.setUp();
    }
}