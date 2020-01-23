package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    public final static int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract Object getId(String uuid);

    protected abstract void fillDeletedElement(int id);

    protected abstract void insertElement(Resume resume, int id);

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("The storage was cleared");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Resume doGet(Object id) {
        return storage[(Integer) id];
    }

    @Override
    protected void doUpdate(Object id, Resume resume) {
        storage[(Integer) id] = resume;
    }

    @Override
    protected void doSave(Resume resume, Object id) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("WARNING! Storage is full!", resume.getUuid());
        } else {
            insertElement(resume, (Integer) id);
            size++;
            System.out.printf("New resume %s was added to the storage\n", resume);
        }
    }

    @Override
    protected void doDelete(Object id) {
        fillDeletedElement((Integer) id);
        storage[size-- - 1] = null;
    }

    @Override
    protected boolean isExist(Object id) {
        return ((Integer) id) >= 0;
    }
}
