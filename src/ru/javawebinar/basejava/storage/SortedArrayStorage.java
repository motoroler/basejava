package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertElement(Resume resume, int index) {
        int position = Math.abs(++index);
        if (size - position >= 0) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = resume;
    }

    @Override
    protected void fillDeletedElement(int index) {
        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
        super.fillDeletedElement(index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
