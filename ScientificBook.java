public class ScientificBook extends Book {
    private String scientificField;

    public String getScientificField() {
        return scientificField;
    }

    public void setScientificField(String scientificField) {
        this.scientificField = scientificField;
    }

    public ScientificBook(String title, String author, long ISBN, int year_published, String genre, String scientificField) {
        super(title, author, ISBN, year_published, genre);
        this.scientificField = scientificField;
    }
}
