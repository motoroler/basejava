package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int ARRAY_SIZE = 10_000;
    private Resume[] storage = new Resume[ARRAY_SIZE];
    private int nextFreeElement = 0;

    public void clear() {
        Arrays.fill(storage, 0, nextFreeElement, null);
        System.out.println("The storage was cleared");
        nextFreeElement = 0;
    }

    public void save(Resume r) {
        int index = -1;
        if (0 <= isExist(r.getUuid())) {
            System.out.printf("ERROR: You try to add CV %s, which already existed in the storage\n", r);
        } else {
            if (nextFreeElement < ARRAY_SIZE) {
                storage[nextFreeElement++] = r;
                System.out.printf("New resume %s was added to the storage\n", r);
            } else {
                System.out.println("WARNING! Storage is full!");
            }
        }
    }

    public Resume get(String uuid) {
        int index = -1;
        if (0 <= (index = isExist(uuid))) {
            return storage[index];
        } else {
            System.out.printf("Resume %s was not found\n", uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = -1;
        if (0 <= (index = isExist(uuid))) {
            storage[index] = storage[nextFreeElement - 1];
            storage[nextFreeElement-- -1] = null;
            System.out.printf( "%s resume was deleted\n", uuid);
        } else {
            System.out.println("ERROR: Provided CV doesn't exist in the storage");
        }
    }

    public void update(Resume resume) {
        int index = -1;
        if (0 <= (index = isExist(resume.getUuid()))) {
            //do nothing.TODO: storage[index] = resume;
        }
    }

    /**
     * @param uuid
     * @return index of uuid in the storage, if it exists in the storage. Otherwise -1 will be returned
     */
    private int isExist(String uuid) {
        for (int i = 0; i < nextFreeElement; i++) {
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
        return Arrays.copyOf(storage, nextFreeElement);
    }

    public int size() {
        return nextFreeElement;
    }
}
