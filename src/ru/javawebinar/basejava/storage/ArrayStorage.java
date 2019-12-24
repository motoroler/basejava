package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (isBasicCheckPassed(resume)) {
            storage[size++] = resume;
            System.out.printf("New resume %s was added to the storage\n", resume);
        }
    }

    @Override
    protected void doDeletion(String uuid, int index) {
        storage[index] = storage[size - 1];
        storage[size-- - 1] = null;
        System.out.printf("%s resume was deleted\n", uuid);
    }

    /**
     * @param uuid
     * @return index of uuid in the storage, if it exists in the storage. Otherwise -1 will be returned
     */
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}