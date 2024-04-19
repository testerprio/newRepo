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

WebUI.click(findTestObject('SAWETO/Sidebar Menu/iid. Unreg'))

WebUI.setText(findTestObject('SAWETO/inputSearchMSISDN'), GlobalVariable.MSISDN_testing)

WebUI.click(findTestObject('SAWETO/btnCheck'))

if (CustomKeywords.'com.saweto.additionalKeyword.checkWebElementExist'(findTestObject('Object Repository/SAWETO/Unreg/divAlert'))) {
    println('Nothing package ......')
} else {
    WebUI.sendKeys(findTestObject('SAWETO/inputSearch'), GlobalVariable.expectedSID)

    if (CustomKeywords.'com.saweto.additionalKeyword.checkWebElementExist'(findTestObject('Object Repository/SAWETO/Unreg/btnDetail')) == 
    false) {
        println('The package doesn`t exist')
    } else {
        WebUI.click(findTestObject('SAWETO/Unreg/btnDetail'))

        WebUI.delay(3)

        WebUI.takeScreenshot()

        WebUI.click(findTestObject('SAWETO/Unreg/btnUnreg'))

        WebUI.delay(5)

        WebUI.click(findTestObject('SAWETO/btnCheck'))

        if (CustomKeywords.'com.saweto.additionalKeyword.checkWebElementExist'(findTestObject('SAWETO/inputSearch'))) {
            println('Masih ada record and find spesific SID')

            WebUI.setText(findTestObject('SAWETO/inputSearch'), GlobalVariable.expectedSID)

            if (CustomKeywords.'com.saweto.additionalKeyword.checkWebElementExist'(findTestObject('Object Repository/SAWETO/Unreg/divAlert'))) {
                println('Unreg successfully......')
            } else {
                println('Failed Unreg .......')
            }
        } else {
            println('No more Record SID')
        }
        
        WebUI.takeScreenshot()
    }
}

