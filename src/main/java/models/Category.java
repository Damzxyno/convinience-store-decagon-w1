package models;


public class Category {
    private String categoryName;
    private  String description;

    public Category(String categoryName, String description){
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryName() {return categoryName;}

    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public String getDescription() {return  description;}

    public void setDescription (String description) {this.description = description;}

    @Override
    public String toString(){return "Category{ categoryName: '" + categoryName + "', description: '" + description + "'}";}
}
