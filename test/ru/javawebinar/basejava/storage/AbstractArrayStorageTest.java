package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() throws Exception {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() throws Exception {
        Assertions.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void getAll() throws Exception {
        Assertions.assertArrayEquals(new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)}, storage.getAll());
    }

    @Test
    void update() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    void delete() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_1);
            storage.get(UUID_1);
        });
    }

    @Test
    void save() throws Exception {
        storage.save(new Resume("4"));
        Assertions.assertEquals(new Resume("4"), storage.get("4"));
    }

    @Test
    void storageOverflow() throws Exception {
        for (int i = storage.size() + 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume(Integer.toString(i)));
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()), "Extra resume could be added");
    }
}