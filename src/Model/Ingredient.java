// Zack Katancik

package Model;

public class Ingredient {
    protected int ingredientId;
    protected String name;
    protected int allergyTypes;

	public Ingredient(int ingredientId, String name, AllergyTypes allergyTypes) {
		this.ingredientId = ingredientId;
		this.name = name;
		this.allergyTypes = allergyTypes;
    }
    
    /* Getters and Setters */

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAllergyTypes() {
		return allergyTypes;
	}

	public void setAllergyTypes(int allergyTypesId) {
		this.allergyTypes = allergyTypes;
	}

}

