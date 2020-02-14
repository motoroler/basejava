package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertElement(Resume resume, int id) {
        storage[size] = resume;
    }

    @Override
    protected void fillDeletedElement(int id) {
        storage[id] = storage[size - 1];
    }

    /**
     * @param uuid
     * @return index of uuid in the storage, if it exists in the storage. Otherwise -1 will be returned
     */
    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Stream.of(Arrays.copyOf(storage, size))
                .sorted(Comparator.comparing(Resume::getUuid))
                .collect(Collectors.toList());
    }
}