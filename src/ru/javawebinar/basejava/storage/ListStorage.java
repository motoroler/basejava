package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {
    private final static LinkedList<Resume> storage = new LinkedList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume storageGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void storageSet(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    public void clear() {
        super.clear();
        storage.removeAll(storage);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage.remove(index);
    }
}
