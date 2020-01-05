package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    public final static int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract int getIndex(String uuid);


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        System.out.println("The storage was cleared");
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            fillDeletedElement(index);
            storage[size-- - 1] = null;
            System.out.printf("%s resume was deleted\n", uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void fillDeletedElement(int index);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            System.out.println();
            throw new StorageException("WARNING! Storage is full!", resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
            System.out.printf("New resume %s was added to the storage\n", resume);
        }
    }

    protected abstract void insertElement(Resume resume, int index);
}
