package helpers;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;


public class Browserstack {

  public static String videoUrl(String sessionId) {
    String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    String login = config.login();
    String password = config.password();

    return given()
            .auth().basic(login, password)
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
