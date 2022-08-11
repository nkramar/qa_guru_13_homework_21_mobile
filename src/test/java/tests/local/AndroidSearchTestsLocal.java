package tests.local;

import com.codeborne.selenide.CollectionCondition;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class AndroidSearchTestsLocal extends TestBaseLocal {


  @Test
  void checkTextsOnboardingScreens() {

    step("Check the text on the first screen", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
              .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
    });

    step("Click on 'continue' button", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });

    step("Check the text on the second screen", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
              .findBy(text("New ways to explore")).isDisplayed();
    });

    step("Click on 'continue' button", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });

    step("Check the text on the third screen", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
              .findBy(text("Reading lists with sync")).isDisplayed();
    });

    step("Click on 'continue' button", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
    });

    step("Check the text on forth screen", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
              .findBy(text("Send anonymous data")).isDisplayed();
    });
  }

  @Test
  void addLanguageOnFirstScreen() {

    step("Click on button 'add or edit languages'", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")).click();
    });

    step("Click on button 'add language'", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).last().click();
    });

    step("Choose German language", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/language_subtitle")).findBy(text("German")).click();
    });

    step("Check that German was added to languages list", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).findBy(text("Deutsch")).isDisplayed();
    });

    step("Go back to the first screen", () -> {
      $(AppiumBy.className("android.widget.ImageButton")).click();
    });

    step("Check that the list of languages was increased to 2 items", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/languagesList"))
              .$$(AppiumBy.id("org.wikipedia.alpha:id/option_label")).shouldHave(size(2));
    });
  }

  @Test
  @DisplayName("Simple Wikipedia search test")
  void searchTest() {
    step("Go from language choice page to the search page", () -> {
      back();
    });
    step("Type search", () -> {
      $(AppiumBy.accessibilityId("Search Wikipedia")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
              .sendKeys("Appium");
    });
    step("Verify content found", () ->
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(sizeGreaterThan(0)));
  }

  @Test
  @DisplayName("Search and open article test")
  void searchAndOpenArticleTest() {
    step("Go from language choice page to the search page", () -> {
      back();
    });
    step("Type search", () -> {
      $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
      $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
    });
    step("Verify content found", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/search_results_list")).shouldHave(
              CollectionCondition.sizeGreaterThan(0));
    });
    step("Open article about Java ", () -> {
      $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
              .findBy(text("Java (programming language)")).click();
      $$(AppiumBy.className("android.view.View"))
              .findBy(text("Java (programming language)")).isDisplayed();
    });


  }

}

