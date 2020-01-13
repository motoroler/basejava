package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Objects;

public class MapStorage extends AbstractStorage {
    private final static HashMap<Integer, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doUpdate(int index, Resume resume) {
        storage.put(index, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(Resume[]::new);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        storage.put(-index, resume);
    }

    @Override
    protected int getIndex(String uuid) {
        int hashCode = Objects.hashCode(uuid);
        if (storage.containsKey(hashCode)) {
            return hashCode;
        }
        return -hashCode;
    }
}
