import java.text.*;
import java.util.*;

public class Book implements Media, Comparable<Book> {
    private static final DecimalFormat RATING_FORMAT = new DecimalFormat("0.##");

    static {
        RATING_FORMAT.setMinimumFractionDigits(1);
    }

    private final String title;
    private final List<String> authors;
    private final List<String> content;
    private int ratingSum;
    private int ratingCount;

    public Book(String title, List<String> authors, Scanner contentScanner) {
        this.title = Objects.requireNonNull(title, "title cannot be null");

        List<String> authorCopy = new ArrayList<>();
        if (authors != null) {
            for (String author : authors) {
                if (author != null && !author.isEmpty()) {
                    authorCopy.add(author);
                }
            }
        }
        this.authors = Collections.unmodifiableList(authorCopy);

        List<String> tokens = new ArrayList<>();
        if (contentScanner != null) {
            while (contentScanner.hasNext()) {
                tokens.add(contentScanner.next());
            }
        }
        this.content = Collections.unmodifiableList(tokens);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getArtists() {
        return authors;
    }

    @Override
    public void addRating(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("score must be non-negative");
        }
        ratingSum += score;
        ratingCount++;
    }

    @Override
    public int getNumRatings() {
        return ratingCount;
    }

    @Override
    public double getAverageRating() {
        if (ratingCount == 0) {
            return 0.0;
        }
        return (double) ratingSum / ratingCount;
    }

    @Override
    public List<String> getContent() {
        return content;
    }

    @Override
    public int compareTo(Book other) {
        Objects.requireNonNull(other, "other book cannot be null");

        int byAverage = Double.compare(other.getAverageRating(), getAverageRating());
        if (byAverage != 0) {
            return byAverage;
        }

        int byRatings = Integer.compare(other.getNumRatings(), getNumRatings());
        if (byRatings != 0) {
            return byRatings;
        }

        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        if (ratingCount == 0) {
            return title + " by " + authors;
        }
        return title + " by " + authors + ": "
                + RATING_FORMAT.format(getAverageRating())
                + " (" + ratingCount + " ratings)";
    }
}
