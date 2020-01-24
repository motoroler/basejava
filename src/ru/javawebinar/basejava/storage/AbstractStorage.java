package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getId(String uuid);

    protected abstract Resume doGet(Object id);

    protected abstract void doSave(Resume resume, Object id);

    protected abstract void doUpdate(Object id, Resume resume);

    protected abstract void doDelete(Object id);

    protected abstract boolean isExist(Object id);

    @Override
    public void update(Resume resume) {
        doUpdate(getExistedSearchKey(resume.getUuid()), resume);
    }

    @Override
    public Resume get(String uuid) {
        return doGet(getExistedSearchKey(uuid));
    }

    @Override
    public void save(Resume resume) {
        doSave(resume, getNotExistedSearchKey(resume.getUuid()));
    }

    @Override
    public void delete(String uuid) {
        doDelete(getExistedSearchKey(uuid));
        System.out.printf("%s resume was deleted\n", uuid);
    }

    private Object getExistedSearchKey(String uuid) {
        Object id = getId(uuid);
        if (!isExist(id)) {
            throw new NotExistStorageException(uuid);
        }
        return id;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object id = getId(uuid);
        if (isExist(id)) {
            throw new ExistStorageException(uuid);
        }
        return id;
    }
}
