package Model;

public class Reviews {
	private Integer reviewId;
	private String reviewText;
	private Integer recipeId;
	private Integer rating;
	private Integer userId;
	
	public Reviews(String reviewText, Integer recipeId, Integer rating, Integer userId) {
		super();
		this.reviewText = reviewText;
		this.recipeId = recipeId;
		this.rating = rating;
		this.userId = userId;
	}
	
	public Reviews(Integer reviewId, String reviewText, Integer recipeId, Integer rating, Integer userId) {
		super();
		this.reviewId = reviewId;
		this.reviewText = reviewText;
		this.recipeId = recipeId;
		this.rating = rating;
		this.userId = userId;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
