package BookCircle;

// Java Bean Class that holds the characteristics of a book
public class book {
	static String name;
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		book.name = name;
	}
	public static String getRating() {
		return rating;
	}
	public static void setRating(String rating) {
		book.rating = rating;
	}
	public static String getReview() {
		return review;
	}
	public static void setReview(String review) {
		book.review = review;
	}
	static String rating;
	static String review;
	

}
