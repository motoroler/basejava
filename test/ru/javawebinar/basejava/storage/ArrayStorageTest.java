package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.BeforeEach;

class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Override
    @BeforeEach
    void setUp() throws Exception {
        super.setUp();
    }
}