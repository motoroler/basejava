package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Disabled;

class ListStorageTest extends AbstractArrayStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Disabled
    void storageOverflow() throws Exception {
        super.storageOverflow();
    }
}