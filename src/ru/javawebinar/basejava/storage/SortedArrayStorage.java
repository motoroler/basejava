package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertElement(Resume resume, int id) {
        int position = -id - 1;
        if (size - position >= 0) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = resume;
    }

    @Override
    protected void fillDeletedElement(int id) {
        if (size - 1 - id >= 0) {
            System.arraycopy(storage, id + 1, storage, id, size - 1 - id);
        }
    }

    @Override
    protected Integer getId(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
