package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ListStorage extends AbstractStorage {
    private final static List<Resume> storage = new LinkedList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
        System.out.println("The storage was cleared");
    }

    @Override
    protected void doDelete(Object id) {
        storage.remove((int) id);
    }

    @Override
    protected Resume doGet(Object id) {
        return (int) id >= 0 ? storage.get((int) id) : null;
    }

    @Override
    protected void doUpdate(Object id, Resume resume) {
        storage.set((int) id, resume);
    }

    @Override
    protected void doSave(Resume resume, Object id) {
        storage.add(resume);
        System.out.printf("New resume %s was added to the storage\n", resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object id) {
        return (doGet(id) != null);
    }

    @Override
    protected Stream<Resume> doAllSorted() {
        return storage.stream();
    }

}
