package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

abstract class AbstractStorageTest {
    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_DUMMY = "dummy";

    protected static final Resume RESUME_1 = new Resume(UUID_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3);
    protected static final Resume RESUME_DUMMY = new Resume(UUID_DUMMY);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() throws Exception {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() throws Exception {
        Assertions.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_DUMMY));
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void getAll() throws Exception {
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] storageAll = storage.getAll();
        Assertions.assertEquals(resumes.length, storageAll.length);
        for (Resume resume : resumes) {
            Assertions.assertTrue(Arrays.asList(storageAll).contains(resume));
        }
    }

    @Test
    void update() throws Exception {
        Resume resume1 = new Resume(UUID_1);
        storage.update(resume1);
        Assertions.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_DUMMY));
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        Assertions.assertEquals(2, storage.size());
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_DUMMY));
    }

    @Test
    void save() throws Exception {
        storage.save(RESUME_DUMMY);
        Assertions.assertEquals(RESUME_DUMMY, storage.get(UUID_DUMMY));
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void saveAlreadyExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }
}