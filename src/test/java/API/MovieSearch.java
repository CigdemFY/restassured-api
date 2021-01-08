package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;


public class MovieSearch {

    public Response SearchMovieGeneralTitle(String MovieTitle, String apiKey) {
        Response response = null;
        try {
            RestAssured.baseURI = "http://www.omdbapi.com";
            response = given()
                    .param("apikey", apiKey)
                    .param("s", MovieTitle)
                    .when()
                    .get()
                    .then()
                    .statusCode(200)
                    .and()
                    .extract()
                    .response();
            //System.out.println("Response Body is: " + response.asString());
        } catch (Exception ex) {
            System.out.println("Hata oluştu" + ex.getMessage());
        }
        return response;
    }

    public String getIdfromSpecMovieTitle(String MovieTitle, String apiKey, String SpecificMovie) {
        String id = null;
        Response response = SearchMovieGeneralTitle(MovieTitle, apiKey);
        JsonPath path = response.jsonPath();
        List<MovieConsts> movieConstsList = path.getList("Search", MovieConsts.class);
        for (MovieConsts movieconst : movieConstsList) {
            if (movieconst.getTitle().equals(SpecificMovie)) {
                id = movieconst.getImdbID();
                //System.out.println("Selected movie id" +" "+ id);
            }
        }
        return id;
    }

    public void searchId(String id, String apiKey) {
        try {
            RestAssured.baseURI = "http://www.omdbapi.com";
            Response response = given()
                    .param("apikey", apiKey)
                    .param("i", id)
                    .when()
                    .get()
                    .then()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                    .and()
                    .extract()
                    .response();
            System.out.println("THE MOVIE THAT HAS ID OF:"+" "+id+ " "+ response.asString());
        } catch (Exception ex) {
            System.out.println("Hata oluştu" + ex.getMessage());
        }
    }
}
