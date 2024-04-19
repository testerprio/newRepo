import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('SAWETO/TC Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i. featureInformation'))

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i3. smsInbox'))

WebUI.click(findTestObject('SAWETO/SMS Inbox/dropdownMSISDN'))

WebUI.setText(findTestObject('SAWETO/SMS Inbox/inputMSISDN'), GlobalVariable.MSISDN_testing)

WebUI.sendKeys(findTestObject('SAWETO/SMS Inbox/inputMSISDN'), Keys.chord(Keys.ARROW_DOWN, Keys.ENTER))

WebUI.click(findTestObject('SAWETO/btnCheck'))

dateNow = CustomKeywords.'com.saweto.additionalKeyword.getDateNow'()

WebUI.delay(3)

CustomKeywords.'com.saweto.additionalKeyword.clickBtnDetailinTableInbox'(findTestObject('SAWETO/tblListData'), GlobalVariable.expectedSID, 
    dateNow)

actualNotif = WebUI.getText(findTestObject('SAWETO/SMS Inbox/textfieldMsg'))

println('Actual notif = ' + actualNotif)

expectedNotifAfterChangeDate = CustomKeywords.'com.saweto.additionalKeyword.replaceDateInStatement'(GlobalVariable.expectedNotifPurchase, 
    dateInProcon, Integer.parseInt(expectedValidity), expectedFormatDate)

expectedNotifAfterDateQuota = CustomKeywords.'com.saweto.additionalKeyword.replacequotaInStatement'(expectedNotifAfterChangeDate, 
    listQuotaInProcon, listExpectedFormatQuota)

println('Expected Notif = ' + expectedNotifAfterDateQuota)

WebUI.delay(3)

WebUI.verifyEqual(actualNotif, expectedNotifAfterDateQuota)

WebUI.takeElementScreenshot(findTestObject('SAWETO/SMS Inbox/textfieldMsg'))

WebUI.click(findTestObject('SAWETO/SMS Inbox/btnOKpupopMsg'))

WebUI.callTestCase(findTestCase('SAWETO/TC Logout'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

