package com.saweto
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import java.text.SimpleDateFormat
import java.util.Date


class additionalKeyword {

	Date date = new Date()
	SimpleDateFormat formatDateOne = new SimpleDateFormat("dd-MM-yyyy")
	SimpleDateFormat formatDateTwo = new SimpleDateFormat("yyyy-MM-dd")
	SimpleDateFormat formatDateTree = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
	SimpleDateFormat formatDateFour = new SimpleDateFormat("dd/MM/yyyy")

	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	@Keyword
	def checkWebElementExist(TestObject to) {
		Boolean checkElement
		try {
			WebElement elm = WebUiBuiltInKeywords.findWebElement(to);
			println("Data gk ada ...............")
			checkElement = true
		}catch (WebElementNotFoundException e) {
			println ("Data ada.......................")
			checkElement = false
		}
		return checkElement
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	@Keyword
	def clickBtnDetailinTableInbox(TestObject tbl, String paramsSID, String paramsDate) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(tbl)
		List<WebElement> tr = mailList.findElements(By.xpath("./TBODY/tr"))
		WebElement tdDate
		WebElement tdSID
		String strDate
		String strSid
		for(int i=0; i <= 5; i++) {
			tdDate = tr[i].findElement(By.xpath("./td[4]"))
			strDate = tdDate.getText().substring(0, 10)
			println("======================="+strDate+" and param Date = "+paramsDate+" params SID = "+paramsSID)
			if (strDate.equals(paramsDate)) {
				tdSID = tr[i].findElement(By.xpath("./td[2]"))
				strSid = tdSID.getText()
				println("======================="+strSid)
				if (strSid.equals(paramsSID)) {
					println("Data Ketemu dan klik detail")
					tr[i].findElement(By.xpath("./td[5]")).click()
					break
				}else {
					println("Data not found")
				}
			}
		}
	}

	/**
	 * Print Specific Value of TD
	 * @param table Katalon test object represent for HTML table 
	 * @return text in specific TD
	 */
	@Keyword
	def getTextFromTable(TestObject tbl, int inColumns) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(tbl)
		WebElement selectedColumn = mailList.findElement(By.xpath("./TBODY/tr[1]/td["+inColumns+"]"))
		String printedText = selectedColumn.getText()
		println("================="+printedText+"=================")
		return printedText
	}

	@Keyword
	def getDateNow() {
		String now = formatDateOne.format(date)
		println("-=-=-=-=-==="+now)
		return now
	}

	@Keyword
	def getExpiredDateBasedOnProcon(String validity) {
		Date expDate = date + Integer.parseInt(validity) - 1
		String stringDate = formatDateTwo.format(expDate)
		println("=-=-=-=-==--==-=-="+stringDate)
		return stringDate
	}

	@Keyword
	def replaceDateInStatement(String valueinFile, String formatBefore, int validity, String formatDate) {
		SimpleDateFormat ftDate = new SimpleDateFormat(formatDate, Locale.ENGLISH)
		Date expDate = date + validity
		String srtExpDate = ftDate.format(expDate)
		String replacedText = valueinFile.replace(formatBefore, srtExpDate)
		println("================="+valueinFile+"=================")
		println("================="+expDate+"=================")
		println("================="+replacedText+"=================")
		return replacedText
	}

	@Keyword
	def replacequotaInStatement(String statementBefore, List<String> var, List<String> replaceValue ) {
		String newStatement = statementBefore
		for(int i =0; i< var.size(); i++) {
			newStatement = newStatement.replace(var[i], replaceValue[i])
			println("Statement ke-"+(i+1)+" = "+newStatement)
		}
		return newStatement
	}

	@Keyword
	def convertStringBalancetoInteger(String balance) {
		String removeComma = balance.replace(",", "")
		String convertoInt = Integer.parseInt(removeComma)
		println("-=-=-=-=-===-==-= Int Balance = "+convertoInt)
		return convertoInt
	}

	@Keyword
	def checkElementVisible(TestObject to) {
		Boolean checkElm
		WebElement elm = WebUiBuiltInKeywords.findWebElement(to)
		if(elm.isDisplayed()) {
			checkElm = true
		}else {
			checkElm = false
		}
		return checkElm
	}
}