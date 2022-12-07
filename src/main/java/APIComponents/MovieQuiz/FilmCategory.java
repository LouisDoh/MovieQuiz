package APIComponents.MovieQuiz;

public class FilmCategory {

    int filmID;
    int catID;
    String title;
    String catName;
    String desc;

    public FilmCategory(Film film, Category category) {
        this.filmID = film.getFilmID();
        this.catID = category.getCatID();
        this.title = film.getTitle();
        this.catName = category.getCatName();
        this.desc = film.getDesc();
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
