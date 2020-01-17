package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    private int doOperation(String uuid, StorageException storageException, int direction) {
        int id = getId(uuid);
        if ((direction * id > 0) || (id == 0 && direction > 0)) {
            return id;
        } else {
            throw storageException;
        }
    }

    @Override
    public void update(Resume resume) {
        doUpdate(doOperation(resume.getUuid(), new NotExistStorageException(resume.getUuid()), 1), resume);
    }

    protected abstract int getId(String uuid);

    @Override
    public Resume get(String uuid) {
        return doGet(doOperation(uuid, new NotExistStorageException(uuid), 1));
    }

    protected abstract Resume doGet(int id);

    protected abstract void doUpdate(int id, Resume resume);

    @Override
    public void save(Resume resume) {
        doSave(resume, doOperation(resume.getUuid(), new ExistStorageException(resume.getUuid()), -1));
    }

    protected abstract void doSave(Resume resume, int id);

    @Override
    public void delete(String uuid) {
        doDelete(doOperation(uuid, new NotExistStorageException(uuid), 1));
        System.out.printf("%s resume was deleted\n", uuid);
    }

    protected abstract void doDelete(int id);
}
