package main.java.soniatomas.cpe305fall2016project.skinsort;

import java.util.HashMap;
import java.util.List;

public class CreateProductsOperation implements Operation {
  private HashMap<String, Object> operationVariables;

  public CreateProductsOperation() {
    operationVariables = new HashMap<String, Object>();
  }

  public HashMap<String, Object> getVariables() {
    return operationVariables;
  }

  public HashMap<String, Object> execute(HashMap<String, String> parameters) {
    operationVariables.put("status", "FAILURE");
    if (parameters != null && parameters.get("product_name") != null) {
      operationVariables.put("status", "SUCCESS");
      String name = parameters.get("product_name");
      String brand = parameters.get("product_brand");
      String category = parameters.get("product_category");
      String type = parameters.get("product_type");
      Product product = new Product(category, type, brand, name);
      
      String ingredients = parameters.get("product_ingredients");
      InputValidator inputValidator = new InputValidator();
      List<String> ingredientList = inputValidator.ingredientInputStringToList(ingredients);
      for (String ingredient : ingredientList) {
        Ingredient newIngredient = new Ingredient(ingredient);
        product.addIngredient(newIngredient);
      }
      RatingUpdater ratingUpdater = new RatingUpdater();
      double systemRating = ratingUpdater.updateProductSystemRating(product);
      product.getRating().setSystemRating(systemRating);
      
      SystemData.getInstance().setProductOne(product);
    }
    if (parameters != null && parameters.get("product_two_name") != null) {
      operationVariables.put("status", "SUCCESS");
      String nameTwo = parameters.get("product_two_name");
      String brandTwo = parameters.get("product_two_brand");
      String categoryTwo = parameters.get("product_two_category");
      String typeTwo = parameters.get("product_two_type");
      Product productTwo = new Product(categoryTwo, typeTwo, brandTwo, nameTwo);
      
      String ingredientsTwo = parameters.get("product_two_ingredients");
      InputValidator inputValidator = new InputValidator();
      List<String> ingredientListTwo = inputValidator.ingredientInputStringToList(ingredientsTwo);
      for (String ingredient : ingredientListTwo) {
        Ingredient newIngredient = new Ingredient(ingredient);
        productTwo.addIngredient(newIngredient);
      }      
      RatingUpdater ratingUpdater = new RatingUpdater();
      double systemRating = ratingUpdater.updateProductSystemRating(productTwo);
      productTwo.getRating().setSystemRating(systemRating);
      SystemData.getInstance().setProductTwo(productTwo);
    }
    return operationVariables;
  }

}
