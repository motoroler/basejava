package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int STORAGE_LIMIT = 10_000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        System.out.println("The storage was cleared");
        size = 0;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.printf("ERROR: You try to add CV %s, which already existed in the storage\n", resume);
        } else if (size < STORAGE_LIMIT) {
                storage[size++] = resume;
                System.out.printf("New resume %s was added to the storage\n", resume);
        } else {
            System.out.println("WARNING! Storage is full!");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.printf("Resume %s was not found\n", uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size-- -1] = null;
            System.out.printf( "%s resume was deleted\n", uuid);
        } else {
            System.out.println("ERROR: Provided CV doesn't exist in the storage");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("ERROR: Provided CV doesn't exist in the storage");
        }
    }

    /**
     * @param uuid
     * @return index of uuid in the storage, if it exists in the storage. Otherwise -1 will be returned
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
