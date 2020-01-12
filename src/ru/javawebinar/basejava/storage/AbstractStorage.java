package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            doUpdate(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract int getIndex(String uuid);

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return doGet(index);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract Resume doGet(int index);

    protected abstract void doUpdate(int index, Resume resume);

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, index);
        }
    }

    protected abstract void doSave(Resume resume, int index);

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            doDelete(index);
            System.out.printf("%s resume was deleted\n", uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void doDelete(int index);
}
