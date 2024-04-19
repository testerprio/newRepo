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

WebUI.click(findTestObject('SAWETO/Sidebar Menu/iia. Registration'))

WebUI.click(findTestObject('SAWETO/Sidebar Menu/iic. NoCostRegistration'))

WebUI.click(findTestObject('SAWETO/Cost or NoCost Registration/selectMSISDN'), FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('SAWETO/Cost or NoCost Registration/inputMSISDN'), GlobalVariable.MSISDN_testing)

WebUI.sendKeys(findTestObject('SAWETO/Cost or NoCost Registration/inputMSISDN'), Keys.chord(Keys.ARROW_DOWN, Keys.ENTER))

WebUI.setText(findTestObject('SAWETO/Cost or NoCost Registration/inputSID'), GlobalVariable.expectedSID)

WebUI.setText(findTestObject('SAWETO/Cost or NoCost Registration/inputProjectName'), ProjectName)

WebUI.setText(findTestObject('SAWETO/Cost or NoCost Registration/inputPIC'), Tester)

WebUI.click(findTestObject('SAWETO/Cost or NoCost Registration/btnSubmit'))

WebUI.delay(3)

WebUI.takeScreenshot()

WebUI.click(findTestObject('SAWETO/Cost or NoCost Registration/btnSubmitatPopup'))

