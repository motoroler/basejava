package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;
    protected static final String FULLNAME_1 = "Ivanov";
    protected static final String FULLNAME_2 = "Petrov";
    protected static final String FULLNAME_3 = "Sidorov";
    protected static final String FULLNAME_DUMMY = "John Galt";
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_DUMMY = "uuid_dummy";

    protected static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);
    protected static final Resume RESUME_DUMMY = new Resume(UUID_DUMMY, FULLNAME_DUMMY);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_3);
        storage.save(RESUME_2);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void getNotExist() throws Exception {
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_DUMMY));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expectedResumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actualResumes = storage.getAllSorted();
        Assert.assertEquals(expectedResumes, actualResumes);
    }

    @Test
    public void update() throws Exception {
        Resume resume1 = new Resume(UUID_1, FULLNAME_2);
        storage.update(resume1);
        Assert.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test
    public void updateNotExist() throws Exception {
        Assert.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_DUMMY));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteNotExist() throws Exception {
        Assert.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_DUMMY));
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_DUMMY);
        Assert.assertEquals(RESUME_DUMMY, storage.get(UUID_DUMMY));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void saveAlreadyExist() throws Exception {
        Assert.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }
}