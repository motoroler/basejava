package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    public final static int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Resume storageGet(int index) {
        return storage[index];
    }

    @Override
    protected void storageSet(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public void clear() {
        super.clear();
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[size-- - 1] = null;
    }

    @Override
    protected void incrementSize() {
        size++;
    }

    @Override
    protected void checkStorageLimitCondition(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println();
            throw new StorageException("WARNING! Storage is full!", resume.getUuid());
        }
    }
}
