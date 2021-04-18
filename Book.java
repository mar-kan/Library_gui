public class Book {
    private String title;
    private String author;
    private long ISBN;
    private int year_published;
    private String genre;

    public Book(String title, String author,long ISBN,int year_published, String genre) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.year_published = year_published;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
