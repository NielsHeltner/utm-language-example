package src.main.java.utm;

import static spark.Spark.*;

public class Test {

	public static void main(String[] args) {
		System.out.println("http://localhost:4567/");
		get("/", (req, res) -> "Hello");
	}

}
