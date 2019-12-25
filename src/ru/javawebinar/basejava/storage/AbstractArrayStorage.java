package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
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
        System.out.printf("Resume %s was not found\n", uuid);
        return null;
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
            System.out.println("ERROR: Provided CV doesn't exist in the storage");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDeletion(index);
            storage[size-- - 1] = null;
            System.out.printf("%s resume was deleted\n", uuid);
        } else {
            System.out.println("ERROR: Provided CV doesn't exist in the storage");
        }
    }

    protected abstract void doDeletion(int index);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.printf("ERROR: You try to add CV %s, which already exists in the storage\n", resume);
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("WARNING! Storage is full!");
        } else {
            doSave(resume, index);
            size++;
            System.out.printf("New resume %s was added to the storage\n", resume);
        }
    }

    protected abstract void doSave(Resume resume, int index);
}
