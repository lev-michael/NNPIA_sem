package cz.upce.fei.nnpia.nnpia_sem.app.frontend;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@SpringBootTest
public class FrontendTest {

    @Test
    public void userCanLoginByCorrectUsernameAndPassword() {
        open("http://localhost:3000/login");
        $(By.name("username")).setValue("test");
        $(By.name("password")).setValue("password");
        $(By.tagName("button")).click();

        $(By.tagName("h1")).should(Condition.exist);
        $(By.tagName("h1")).should(Condition.exactText("Welcome to Movie database!"));
    }

    @Test
    public void userCannotLoginByWrongUsernameOrPassword() {
        open("http://localhost:3000/login");
        $(By.name("username")).setValue("test");
        $(By.name("password")).setValue("test");
        $(By.tagName("button")).click();

        $(By.className("error")).should(Condition.exist);
        $(By.className("error")).should(Condition.exactText("Wrong username or password"));
    }

    @Test
    public void userCanSearchForMovie() {
        open("http://localhost:3000/movies");
        $(By.tagName("input")).setValue("star wars");
        $$(By.className("list-item")).shouldHave(CollectionCondition.sizeGreaterThan(0));
        $(By.className("list-item")).shouldBe(Condition.exist);
        $(By.className("list-item")).shouldBe(Condition.text("Star Wars"));
    }

    @Test
    public void userCanAddMovieToWatchlist() {
        open("http://localhost:3000/login");
        $(By.name("username")).setValue("test");
        $(By.name("password")).setValue("password");
        $(By.tagName("button")).click();

        $(By.tagName("h1")).should(Condition.exist);
        $(By.tagName("h1")).should(Condition.exactText("Welcome to Movie database!"));

        open("http://localhost:3000/movies/69");
        $(byText("♥ Add to watchlist")).click();

        $(byText("♥ Remove from watchlist")).should(Condition.exist);
        open("http://localhost:3000/watchlist");
        $$(By.className("movie-list-item")).shouldHave(CollectionCondition.sizeGreaterThan(0));
        $(By.tagName("input")).setValue("Walk the Line");
        $(By.className("movie-list-item")).shouldBe(Condition.exist);
        $(byText("X Remove")).click();
    }
}
