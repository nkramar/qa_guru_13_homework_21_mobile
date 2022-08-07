package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class AndroidSearchTests extends TestBase {
  @Test
  @DisplayName("Simple Wikipedia search test")
  void searchTest() {
    step("Type search", () -> {
      $(AppiumBy.accessibilityId("Search Wikipedia")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
              .sendKeys("BrowserStack");
    });
    step("Verify content found", () ->
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0)));
  }

  @Test
  @DisplayName("Search and open article test")
  void searchAndOpenArticleTest() {

    step("Type search", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
    });
    step("Verify content found", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/search_results_list")).shouldHave(
              CollectionCondition.sizeGreaterThan(0));
    });
    step("Open article about Java ", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
      //$(AppiumBy.className("android.widget.TextView")).shouldHave(Condition.text("Java"));
    });


  }
}

