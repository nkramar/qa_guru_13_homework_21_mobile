package helpers;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

        public static String videoUrl(String sessionId) {
            String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

            return given()
                    .auth().basic("nadezhdakramar_8POLQu", "qpszQDzLXrJRHznsyCCK")
                    .log().all()
                    .when()
                    .get(url)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .path("automation_session.video_url");
        }
    }
