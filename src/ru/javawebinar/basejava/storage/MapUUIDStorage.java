package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUUIDStorage extends AbstractStorage {
    private final static Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doDelete(Object id) {
        storage.remove((String) id);
    }

    @Override
    protected Resume doGet(Object id) {
        return storage.get((String) id);
    }

    @Override
    protected void doUpdate(Object id, Resume resume) {
        storage.put((String) id, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.values().stream().
                sorted(Comparator.comparing(Resume::getUuid)).
                collect(Collectors.toList());
    }

    @Override
    protected void doSave(Resume resume, Object id) {
        storage.put((String) id, resume);
        System.out.printf("New resume %s was added to the storage\n", resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object id) {
        return (storage.containsKey(id));
    }
}
