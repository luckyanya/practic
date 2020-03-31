import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Singleton {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private static AtomicReference<Singleton> INSTANCE = new AtomicReference<Singleton>();

    public int getId() {
        return id;
    }

    private Singleton(int id) {
        this.id = id;
    }

    public static Singleton getSingleton() {
        INSTANCE.compareAndSet(null, new Singleton(counter.getAndIncrement()));
        return INSTANCE.get();
    }
}