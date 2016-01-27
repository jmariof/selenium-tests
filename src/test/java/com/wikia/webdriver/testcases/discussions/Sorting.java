package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

@Test(groups = {"Discussions", "Sorting"})
public class Sorting extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = {"Sorting_001"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = {"Sorting_002"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSortPostList() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = {"Sorting_003"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = {"Sorting_004"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedUserOnDesktopCanSwitchBetweenLatestAndTrendingTab() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void userCanSwitchBetweenLatestAndTrendingInDropdown() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.clickSortButtonOnMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickTrendingLinkOnMobile().getSortButtonLabel(), "Trending");
    new com.wikia.webdriver.elements.mercury.Loading(driver).handleAsyncPageReload();
    Assertion.assertTrue(postsList.clickSortButtonOnMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickLatestLinkOnMobile().getSortButtonLabel(), "Latest");
  }

  public void userCanSwitchBetweenLatestAndTrendingTab() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickLatestTabOnDesktop();
    new com.wikia.webdriver.elements.mercury.Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("latest"));

    postsList.clickTrendingTabOnDesktop();
    new com.wikia.webdriver.elements.mercury.Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("trending"));
  }
}