package models;


public class Category {
    private final String categoryName;
    private final String description;

    public Category(String categoryName, String description){
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryName() {return categoryName;}

    @Override
    public String toString(){return "Category{ categoryName: '" + categoryName + "', description: '" + description + "'}";}
}
