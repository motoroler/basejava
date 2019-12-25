package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void doSave(Resume resume, int index) {
        int placeResumeHere = Math.abs(++index);
        for (int i = size; i > placeResumeHere; i--) {
            storage[i] = storage[i - 1];
        }
        storage[placeResumeHere] = resume;
    }

    @Override
    protected void doDeletion(int index) {
        for (int i = index; i < size() - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
