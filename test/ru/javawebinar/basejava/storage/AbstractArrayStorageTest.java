package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_dummy = "dummy";

    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    private static final Resume resume_dummy = new Resume(UUID_dummy);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    void size() throws Exception {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void get() throws Exception {
        Assertions.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test
    void getNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_dummy));
    }

    @Test
    void clear() throws Exception {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void getAll() throws Exception {
        Resume[] resumes = {resume1, resume2, resume3};
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    void update() throws Exception {
        storage.update(resume1);
        Assertions.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(resume_dummy));
    }

    @Test
    void delete() throws Exception {
        storage.delete(UUID_1);
        Assertions.assertEquals(2, storage.size());
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_dummy));
    }

    @Test
    void save() throws Exception {
        storage.save(resume_dummy);
        Assertions.assertEquals(resume_dummy, storage.get(UUID_dummy));
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    void saveAlreadyExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume1));
    }

    @Test
    void storageOverflow() throws Exception {
        for (int i = storage.size() + 1; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume(Integer.toString(i)));
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()), "Extra resume could be added");
    }
}