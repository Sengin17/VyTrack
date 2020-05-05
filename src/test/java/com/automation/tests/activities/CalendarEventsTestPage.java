package com.automation.tests.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventsPage;
import com.automation.tests.AbstractTestBase;
import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CalendarEventsTestPage extends AbstractTestBase {

    public WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();


    /**
     * Test Case #1
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Hover on three dots “…” for “Testers meeting”
     * calendar event
     * 5. Verify that “view”, “edit” and “delete” options
     * are available
     */

    @Test
    public void test1() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “view”, “edit” and “delete” options");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.getTreeDots();
        Assert.assertTrue(calendarEventsPage.view.isDisplayed());
        Assert.assertTrue(calendarEventsPage.edit.isDisplayed());
        Assert.assertTrue(calendarEventsPage.delete.isDisplayed());
        test.pass("View, Edit and Delete options verified");
    }

    /**
     * Test Case #2 1.Go to “https://qa1.vytrack.com/"
     * 2.Login as a store manager
     * 3.Navigate to “Activities -> Calendar Events”
     * 4.Click on “Grid Options” button
     * 5.Deselect all options except “Title”
     * 6.Verify that “Title” column still displayed
     */
    @Test
    public void test2() {

        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “Title” column still displayed");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickOnGridOptionBtn();


        List<WebElement> gridOptions = driver.findElements(By.xpath("//input[@data-role='renderable']"));
        for (int i = 1; i < gridOptions.size(); i++) {
            gridOptions.get(i).click();
            BrowserUtilities.wait(1);
        }
        System.out.println(gridOptions.size());
        BrowserUtilities.wait(3);
        Assert.assertTrue(calendarEventsPage.titleColumn.isDisplayed());
        test.pass("Title column verified");

    }

    /**
     * Test Case #3
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Expand “Save And Close” menu
     * 6. Verify that “Save And Close”, “Save And New”
     * and “Save” options are available
     */
    @Test
    public void test3() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “Save And Close”, “ Save And New ” and “ Save ” ");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.clickSaveAndCloseDropDown();

        Assert.assertEquals(calendarEventsPage.saveAndCloseOption.getText().trim(), "Save And Close");
        Assert.assertEquals(calendarEventsPage.saveAndNewsOption.getText().trim(), "Save And New");
        Assert.assertEquals(calendarEventsPage.saveOption.getText().trim(), "Save");

        test.pass("“Save And Close”, “Save And New  and “Save” verified");

    }

    /**
     * Test Case #4
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Then, click on “Cancel” button
     * 6. Verify that “All Calendar Events” page subtitle is
     * displayed
     */
    @Test
    public void test4() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “All Calendar Events” page subtitle is displayed");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.clickCancelBtn();
        BrowserUtilities.wait(3);
        Assert.assertTrue(calendarEventsPage.allCalendarEvents.isDisplayed());

        test.pass("“All Calendar Events” page subtitle is displayed");

    }

    /**
     * Test Case #5
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Verify that difference between end and start time
     * is exactly 1 hour
     */
    @Test
    public void test5() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that difference between end and start time is exactly 1 hour");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        Assert.assertTrue(calendarEventsPage.timeDifference() == 1);

        test.pass("Time differences verified");
    }

    /**
     * Test Case #6
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “9:00 PM” as a start time
     * 6. Verify that end time is equals to “10:00 PM”
     */
    @Test
    public void test6() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that end time is equals to “10:00 PM”");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.startTime.click();
        BrowserUtilities.wait(5);
        calendarEventsPage.time9Pm.click();
        BrowserUtilities.wait(5);
        Assert.assertEquals(calendarEventsPage.getEndTime(),"10:00 PM");
        test.pass("Time is equals to “10:00 PM verified");

    }

    /**
     * Test Case #7
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “All-Day Event” checkbox
     * 6. Verify that “All-Day Event” checkbox is selected
     * 7. Verify that start and end time input boxes are
     * not displayed
     * 8. Verify that start and end date input boxes are
     * displayed
     */
    @Test
    public void test7() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that start and end date input boxes are displayed");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.allDayCheckBox.click();
        BrowserUtilities.wait(3);
        Assert.assertTrue(calendarEventsPage.allDayCheckBox.isSelected());
        BrowserUtilities.wait(3);
        Assert.assertTrue(calendarEventsPage.startTime.isEnabled());
        Assert.assertTrue(calendarEventsPage.endTime.isEnabled());
        Assert.assertTrue(calendarEventsPage.startDate.isDisplayed());
        Assert.assertTrue(calendarEventsPage.endDate.isDisplayed());
        test.pass("start and end date input boxes are displayed verified");

    }

    /**
     * Test Case #8
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Daily” is selected by default and
     * following options are available in
     * “Repeats” drop-down:
     */
    @Test
    public void test8() {
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “Daily” is selected by default and following options are available in “Repeats” drop-down:");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.repeatCheckBox.click();

        Assert.assertTrue(calendarEventsPage.repeatCheckBox.isSelected());

        Select select = new Select(calendarEventsPage.repeatsDropBox);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Daily");
        List<WebElement> repeatsSelect = select.getOptions();

        Assert.assertEquals(repeatsSelect.get(0).getText(), "Daily");
        Assert.assertEquals(repeatsSelect.get(1).getText(), "Weekly");
        Assert.assertEquals(repeatsSelect.get(2).getText(), "Monthly");
        Assert.assertEquals(repeatsSelect.get(3).getText(), "Yearly");
        test.pass("Test is verified");

    }

    /**
     * Test Case #9
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Repeat Every” radio button is
     * selected
     * 8. Verify that “Never” radio button is selected as an
     * “Ends” option.
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day”
     */
    @Test
    public void test9() {

        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “Never” radio button is selected as an “Ends” option");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.repeatCheckBox.click();

        Assert.assertTrue(calendarEventsPage.repeatCheckBox.isSelected());
        Assert.assertTrue(calendarEventsPage.repeatEveryDays.isSelected());
        Assert.assertTrue(calendarEventsPage.endsNever.isSelected());
        String expected ="Summary: Daily every 1 day";
        String actual = calendarEventsPage.summary.getText()+" "+calendarEventsPage.summary1.getText();
        Assert.assertEquals(actual,expected);
        test.pass("Test is verified");
    }
    /**
     * Test Case #10
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “After 10 occurrences” as an “Ends”
     * option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end
     * after 10 occurrences”
     */
    @Test
    public void test10(){
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end after 10 occurrences");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.repeatCheckBox.click();

        calendarEventsPage.endsAfter.click();
        calendarEventsPage.afterOccurrences.sendKeys("10", Keys.ENTER);

        String expected ="Summary: Daily every 1 day, end after 10 occurrences";
        String actual = calendarEventsPage.summary.getText()+
                " "+calendarEventsPage.summary1.getText()+
                calendarEventsPage.summary2.getText();
        Assert.assertEquals(actual,expected);

        test.pass("Test is verified");
    }

    /**
     * Test Case #11
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “By Nov 18, 2021” as an “Ends” option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end by
     * Nov 18, 2021”
     */
    @Test
    public void test11(){
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.repeatCheckBox.click();
        calendarEventsPage.byDateCheckBox.click();
        calendarEventsPage.dateTextBox.click();
        Select selectMonth = new Select(calendarEventsPage.datePickerMonth);
        selectMonth.selectByVisibleText("Nov");
        Select selectYear = new Select(calendarEventsPage.datePickerYear);
        selectYear.selectByVisibleText("2021");
        calendarEventsPage.datePickerDay("18").click();

        String expected ="Summary: Daily every 1 day, end by Nov 18, 2021";

        String actual = calendarEventsPage.summary.getText() +  " " +
                        calendarEventsPage.summary1.getText() +
                        calendarEventsPage.byDatePickerOccurrence.getText();

        Assert.assertEquals(actual,expected);

        test.pass("Test is verified");
    }

    /**
     * Test Case #12
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “Weekly” options as a “Repeat” option
     * 7. Select “Monday and Friday” options as a
     * “Repeat On” options
     * 8. Verify that “Monday and Friday” options are
     * selected
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Weekly every 1 week on
     * Monday, Friday”
     */
    @Test
    public void test12(){
        loginPage.login();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that following message as a summary is displayed: “Summary: Weekly every 1 week on Monday, Friday");
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtilities.wait(4);
        calendarEventsPage.repeatCheckBox.click();
        Select select = new Select(calendarEventsPage.repeatsSelector);
        select.selectByVisibleText("Weekly");
        calendarEventsPage.weekDayCheckBox("M").click();
        calendarEventsPage.weekDayCheckBox("F").click();
        String expected = "Summary: Weekly every 1 week on Monday, Friday";
        String actual = calendarEventsPage.summary.getText() + " " +
                        calendarEventsPage.summary1.getText();
        Assert.assertEquals(actual,expected);
        test.pass("Test is Verified");
    }
}