package API;

import com.thoughtworks.gauge.Step;

public class StepImp {
    MovieSearch rest=new MovieSearch();
    String apiKey="ba1f4581";
    @Step("OMDB webservisinde <movieseries> film serisinde <movieTitle> filmi idsi alınarak aranır")
    public void check(String movieSeries,String movieTitle){
        rest.searchId(rest.getIdfromSpecMovieTitle(movieSeries,apiKey,movieTitle),apiKey);
    }
}
