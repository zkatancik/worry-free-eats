// Zack Katancik

package Model;

public class Ingredient {
    protected int ingredientId;
    protected String name;
    protected AllergyTypes allergyTypes;

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

	public AllergyTypes getAllergyTypes() {
		return allergyTypes;
	}

	public void setAllergyTypes(AllergyTypes allergyTypes) {
		this.allergyTypes = allergyTypes;
	}

}

