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

WebUI.callTestCase(findTestCase('SAWETO/TC Login'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('SAWETO/TC Unreg by Subs'), [('listData') : []], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.delay(5)

WebUI.callTestCase(findTestCase('SAWETO/TC Check Balance'), [:], FailureHandling.CONTINUE_ON_FAILURE)

balanceBefore = GlobalVariable.intBalance

println('----#-#-#-------' + balanceBefore)

WebUI.delay(3)

WebUI.callTestCase(findTestCase('SAWETO/TC Purchase'), [('ProjectName') : '[PRE-RETAIL] create 4 sid 123 xtra combo flex for seller package & Create 3 SID Bonus XCF - M'
        , ('Tester') : 'Elvia'], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.delay(0)

WebUI.callTestCase(findTestCase('SAWETO/TC Check Balance'), [:], FailureHandling.CONTINUE_ON_FAILURE)

balanceAfter = GlobalVariable.intBalance

println('_+_+_+_+_+_+_++' + balanceAfter)

priceReg = (balanceBefore - balanceAfter)

println((((balanceBefore + ' dikurangi ') + balanceAfter) + ' = ') + priceReg)

WebUI.delay(0)

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i. featureInformation'))

WebUI.callTestCase(findTestCase('SAWETO/TC Check Validity'), [('actualExpDate') : '', ('expectedExpDate') : '', ('validityFromProcon') : GlobalVariable.expectedValidity
        , ('substringValidity') : ''], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i. featureInformation'))

WebUI.callTestCase(findTestCase('SAWETO/TC Check Info Package'), [('actualInfoPackage') : '', ('expectedInfoPackage') : ''
        , ('intValidity') : 0, ('dateInProcon') : 'dd-mmm-yyyy', ('expectedFormatDate') : 'dd-MMM-yy'], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('SAWETO/Sidebar Menu/i. featureInformation'))

WebUI.callTestCase(findTestCase('SAWETO/TC Check quota'), [('ActualInfoQuota') : '', ('ExpectedInfoQuota') : findTestData(
            'PROCON/proconFile').getValue(1, 1), ('convertedDateInStatement') : '', ('convertedQuotaInStatement') : '', ('expectedValidity') : GlobalVariable.expectedValidity
        , ('intValidity') : 0, ('dateInProcon') : '%EXP_DATE%', ('expectedFormatDate') : 'dd-MM-yyyy', ('ListQuotaInProcon') : [
            '[data1]', '[voice1]'], ('ListExpectedFormatQuota') : ['3.50 GB', '5']], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('SAWETO/TC Unreg by Subs'), [('listData') : []], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('SAWETO/TC Logout'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()

