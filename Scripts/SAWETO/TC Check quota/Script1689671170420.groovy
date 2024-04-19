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

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i. featureInformation'))

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i1. Check quota'))

WebUI.setText(findTestObject('SAWETO/inputSearchMSISDN'), GlobalVariable.MSISDN_testing)

WebUI.click(findTestObject('SAWETO/btnCheck'))

WebUI.setText(findTestObject('SAWETO/inputSearch'), GlobalVariable.expectedSingleDBName)

WebUI.sendKeys(findTestObject('SAWETO/inputSearch'), Keys.chord(Keys.ENTER))

ActualInfoQuota = CustomKeywords.'com.saweto.additionalKeyword.getTextFromTable'(findTestObject('SAWETO/tblListData'), 3)

intValidity = (Integer.parseInt(expectedValidity) - 1)

convertedDateInStatement = CustomKeywords.'com.saweto.additionalKeyword.replaceDateInStatement'(GlobalVariable.expectedInfoQuota, 
    dateInProcon, intValidity, expectedFormatDate)

convertedQuotaInStatement = CustomKeywords.'com.saweto.additionalKeyword.replacequotaInStatement'(convertedDateInStatement, 
    ListQuotaInProcon, ListExpectedFormatQuota)

WebUI.verifyEqual(ActualInfoQuota, convertedQuotaInStatement)

WebUI.takeElementScreenshot(findTestObject('SAWETO/tblListData'))
