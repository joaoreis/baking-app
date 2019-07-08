package br.com.joaoreis.bakingapp.recipes.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.joaoreis.bakingapp.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        mainActivityTestRule.getActivity();
    }

    @Test
    public void showRecipes() {
        onView(withId(R.id.recipe_list_layout))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void clickOnRecipe() {
        onView(withId(R.id.recipe_list_layout))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }

    @Test
    public void testToolbar() {
        onView(withId(R.id.toolbar))
                .check(matches(isDisplayed()));
    }
}