package Model;

public class AllergyTypes {
	protected int allergyTypesId;
	protected Allergy allergy;
	
	public enum Allergy {
		MilkAllergy, PeanutAllergy, TreeNutsAllergy, WheatAllergy,
	    SoyAllergy, FishAllergy,ShellFishAllergy, EggAllergy, NotAllergy
	}
	
	public AllergyTypes(int allergyTypesId, Allergy allergy) {
		this.allergyTypesId = allergyTypesId;
		this.allergy = allergy;
	}
	
	public AllergyTypes(int allergyTypesId) {
		this.allergyTypesId = allergyTypesId;
	}

	public int getAllergyTypesId() {
		return allergyTypesId;
	}

	public void setAllergyTypesId(int allergyTypesId) {
		this.allergyTypesId = allergyTypesId;
	}

	public Allergy getAllergy() {
		return allergy;
	}

	public void setAllergy(Allergy allergy) {
		this.allergy = allergy;
	}
}
