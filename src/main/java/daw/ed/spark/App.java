package daw.ed.spark;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerRoute;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Hello world!
 *
 */
public class App {
    /**
     * List holding the places
     */
    //private static List<String> places = new List<>();

    private static ArrayList<String> places =
        new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));

    
    public static void main(String[] args) {
        
        get(new Route("/") {
 
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return "<h1>Success!</h1>";
            }
        });

        get(new Route("/hello/:name") {
            @Override
            public Object handle(Request request, Response response) {
                String name = request.params(":name");
                return String.format("<H1>Hello %s</H1>",name);
            }
        });

        get(new Route("/private") {
        	@Override
            public Object handle(Request request, Response response) {
            	response.status(401);
            	return "<h1>Go Away!!</h1>";
            }
        });

        get(new Route("/news/:section") {
        	@Override
        	public Object handle(Request request, Response response) {
            	response.type("text/xml");
            	return String.format("<?xml version='1.0' encoding='UTF-8'?><news>%s</news>",
            		request.params(":section"));
        	}
        });

        get(new FreeMarkerRoute("/freemarker/hello") {
            @Override
            public ModelAndView handle(Request request, Response response) {
            	return modelAndView(null, "hello.ftl");
            }
        });

        get(new FreeMarkerRoute("/freemarker/base") {
            @Override
            public ModelAndView handle(Request request, Response response) {
            	Map<String, Object> data = new HashMap<>();
                data.put("name","Prueba nombre");
            	return modelAndView(data, "content.ftl");
            }
        });

        get(new FreeMarkerRoute("/places") {
            @Override
            public ModelAndView handle(Request request, Response response) {

                Map<String, Object> data = new HashMap<>();
                data.put("places",places);

                return modelAndView(data, "places_list.ftl");
            }
        });


        get(new FreeMarkerRoute("/place")  {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return modelAndView(null,"place_form.ftl");
            }
        });

        post(new Route("/place")  {
            @Override
            public Object handle(Request request, Response response) {
                String new_place = request.queryParams("place");
                places.add(new_place);

                response.redirect("/places");
                return null;
            }
        });
    }
}
