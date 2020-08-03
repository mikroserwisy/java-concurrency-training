package pl.training.concurrency.ex012_cache;

public class Application {

    public static void main(String[] args) {
        LinkedHashMapCache<Integer, String> cache = new LinkedHashMapCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.get(2);
        System.out.println(cache.getAll());
    }

}
