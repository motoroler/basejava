package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        if (isBasicCheckPassed(resume)) {
            int placeResumeHere = Math.abs(getIndex(resume.getUuid()) + 1);
            for (int i = size++; i > placeResumeHere; i--) {
                storage[i] = storage[i - 1];
            }
            storage[placeResumeHere] = resume;
            System.out.printf("New resume %s was added to the storage\n", resume);
        }
    }

    @Override
    protected void doDeletion(String uuid, int indexOfDeletingElement) {
        for (int i = indexOfDeletingElement; i < size() - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size-- - 1] = null;
        System.out.printf("%s resume was deleted\n", uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, this.size(), searchKey);
    }
}
