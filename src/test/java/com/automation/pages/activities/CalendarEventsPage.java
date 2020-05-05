package com.automation.pages.activities;

import com.automation.pages.AbstractPageBase;
import com.automation.utilities.BrowserUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {
    public WebDriver driver = Driver.getDriver();

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;


    public void enterCalendarEventTitle(String titleValue) {
        BrowserUtilities.waitForPageToLoad(22);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    public void enterCalendarEventDescription(String description) {
        //wait until frame is available and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame
    }

    public void clickOnSaveAndClose() {
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
    }

    public String getGeneralInfoTitleText() {
        BrowserUtilities.waitForPageToLoad(22);
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText() {
        BrowserUtilities.waitForPageToLoad(22);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
    }


    //#############################################################
    public List<String> getColumnNames() {
        BrowserUtilities.waitForPageToLoad(20);
        return BrowserUtilities.getTextFromWebElements(columnNames);
    }

    public String getStartTime() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public String getOwnerName() {
        BrowserUtilities.waitForPageToLoad(20);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public void clickToCreateCalendarEvent() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtilities.waitForPageToLoad(20);
    }

    public String getStartDate() {
        BrowserUtilities.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtilities.scrollTo(startDate);
        return startDate.getAttribute("value");
    }

    // -------------------HW 5-----------------------------

    @FindBy(xpath ="//*[@href='/calendar/event/view/1846']")
    public WebElement view;
    @FindBy(xpath = "//*[@href='/calendar/event/update/1846']")
    public WebElement edit;
    @FindBy(xpath = "//*[@href='#']")
    public WebElement delete;

    @FindBy(xpath = "//td[text()='Testers Meeting']//following-sibling::td//a[text()='...']")
    public WebElement  treeDots;

    @FindBy(css = "[class='fa-cog hide-text']")
    public WebElement gridOptionBtn;

    @FindBy(xpath = "//span[text()='Title']")
    public WebElement titleColumn;

    @FindBy(css = "[class='btn-success btn dropdown-toggle']")
    public WebElement saveAndCloseDropDown;

    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and Close')]")
    public WebElement saveAndCloseOption;

    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and New')]")
    public WebElement saveAndNewsOption;

    @FindBy(xpath = "(//ul//li//button[contains(text(),Save)])[3]")
    public WebElement saveOption;

    @FindBy(xpath = "//*[@title='Cancel']")
    public WebElement cancelBtn;

    @FindBy(className = "oro-subtitle")
    public WebElement allCalendarEvents;

    @FindBy(xpath = "//div[@class='ui-timepicker-wrapper']//ul//li[contains(text(),'9:00 PM')]")
    public WebElement time9Pm;

    @FindBy (css ="[id^='oro_calendar_event_form_allDay']")
    public WebElement allDayCheckBox;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(css ="[id^='recurrence-repeat-view']")
    public WebElement repeatCheckBox;

    @FindBy(css ="[id^='recurrence-repeats-view']")
    public WebElement repeatsDropBox;

    @FindBy(xpath = "//label[text()='Summary:']")
    public WebElement summary;

    @FindBy(xpath = "//div[@data-name='recurrence-summary']//div/span[1]")
    public WebElement summary1;

    @FindBy ( xpath= "//div[@data-name='recurrence-summary']//div/span[2]")
    public WebElement summary2;

    @FindBy(xpath = "//input[@checked='checked']")
    public WebElement repeatEveryDays;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement endsNever;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement endsAfter;

    @FindBy(xpath = "(//*[@class=\"recurrence-subview-control__number\"])[7]")
    public WebElement afterOccurrences;

    @FindBy(xpath = "(//input[@type=\"radio\"])[5]")
    public WebElement byDateCheckBox;

    @FindBy(xpath = "//*[@class='datepicker-input hasDatepicker']")
    public WebElement dateTextBox;

    @FindBy(className = "ui-datepicker-month")
    public WebElement datePickerMonth;

    @FindBy(xpath = "//div//span[text()='Daily every 1 day']/following-sibling::span")
    public WebElement byDatePickerOccurrence;

    @FindBy(className = "ui-datepicker-year")
    public WebElement datePickerYear;

    @FindBy(css ="[id^='recurrence-repeats-view']")
    public WebElement repeatsSelector;

    public WebElement weekDayCheckBox(String firstLetterOfDay){
        return Driver.getDriver().findElement(By.xpath("//span[text()='"+firstLetterOfDay+"']"));
    }

    public WebElement datePickerDay(String dayNum){
        String xpath = "//a[text()='"+dayNum+"']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public void clickCancelBtn(){

        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();

    }
    public void clickSaveAndCloseDropDown(){

        wait.until(ExpectedConditions.elementToBeClickable(saveAndCloseDropDown)).click();
    }

    public void clickOnGridOptionBtn(){

        wait.until(ExpectedConditions.elementToBeClickable(gridOptionBtn)).click();

    }

    public void getTreeDots(){

        Actions actions = new Actions(driver);
        BrowserUtilities.wait(3);
        actions.moveToElement(treeDots).pause(2000).perform();

    }
    public Integer timeDifference(){
        String startTime = getStartTime();
        String endTime =getEndTime();

        String [] start =startTime.split(":");
        int startValue = Integer.parseInt(start[0]);

        String [] end =endTime.split(":");
        int endValue = Integer.parseInt(end[0]);
        if (startValue==12){
            startValue-=12;
        }

        return endValue-startValue;
    }

}