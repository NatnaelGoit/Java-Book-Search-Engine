import java.util.*;

public class InvertedIndex {
    public static void main(String[] args) {
        List<Media> docs = List.of(
            new Book("Mistborn", List.of("Brandon Sanderson"),
                     new Scanner("Epic fantasy worldbuildling content")),
            new Book("Farenheit 451", List.of("Ray Bradbury"),
                     new Scanner("Realistic \"sci-fi\" content")),
            new Book("The Hobbit", List.of("J.R.R. Tolkein"),
                     new Scanner("Epic fantasy quest content"))
        );
        
        Map<String, Set<Media>> result = createIndex(docs);
        System.out.println(docs);
        System.out.println();
        System.out.println(result);
    }

    public static Map<String, Set<Media>> createIndex(List<Media> docs) {
        Objects.requireNonNull(docs, "docs cannot be null");

        Map<String, Set<Media>> index = new TreeMap<>();

        for (Media media : docs) {
            if (media != null) {
                List<String> tokens = media.getContent();
                if (tokens != null) {
                    for (String token : tokens) {
                        if (token != null && !token.isEmpty()) {
                            String normalized = token.toLowerCase();
                            Set<Media> entries = index.get(normalized);
                            if (entries == null) {
                                entries = new HashSet<>();
                                index.put(normalized, entries);
                            }
                            entries.add(media);
                        }
                    }
                }
            }
        }

        return index;
    }
}
