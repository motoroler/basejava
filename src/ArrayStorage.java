import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int ARRAY_SIZE = 10_000;
    private Resume[] storage = new Resume[ARRAY_SIZE];
    private int nextFreeElement = 0;

    void clear() {
        Arrays.fill(storage, 0, nextFreeElement, null);
        System.out.println("The storage was cleared");
        nextFreeElement = 0;
    }

    void save(Resume r) {
        if (nextFreeElement < ARRAY_SIZE) {
            storage[nextFreeElement++] = r;
            System.out.printf("New resume %s was added to the storage\n", r);
        } else {
            System.out.println("WARNING! Storage is full!");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < nextFreeElement; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.printf("Resume %s was not found\n", uuid);
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < nextFreeElement; i++) {
            if (storage[i].toString().equals(uuid)) {
                for (int j = i; j < nextFreeElement - 1; j++) {
                    storage[j] = storage[j + 1];// remove storage[i] with uuid and shift storage[i+1] and above to the storage's beginning
                }
                nextFreeElement--;
                System.out.printf("%s resume was deleted\n", uuid);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, nextFreeElement);
    }

    int size() {
        return nextFreeElement;
    }
}
