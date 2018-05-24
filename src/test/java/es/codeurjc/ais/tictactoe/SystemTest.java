package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SystemTest {
	WebDriver driver1;
	WebDriver driver2;
	WebDriverWait waitAlberto;
	WebDriverWait waitAlejandro;
	WebElement buttonPlay1;
	WebElement buttonPlay2;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		ChromeDriverManager.getInstance().setup();
		WebApp.start();
	}
	@Before
	public void setUp() {
		driver1 = new ChromeDriver();
		driver1.get("http://localhost:8080");
		
		driver2 = new ChromeDriver();
		driver2.get("http://localhost:8080");
		
		WebElement alberto = driver1.findElement(By.id("nickname"));
		alberto.sendKeys("Alberto");
		
		WebElement alejandro = driver2.findElement(By.id("nickname"));
		alejandro.sendKeys("Alejandro");
		
		waitAlberto = new WebDriverWait(driver1,3);
		waitAlejandro = new WebDriverWait(driver2,3);
		
		buttonPlay1 = driver1.findElement(By.id("startBtn"));
		buttonPlay2 = driver2.findElement(By.id("startBtn"));
		buttonPlay1.click();
		buttonPlay2.click();
	}
	@After
	public void tearDown() {
		driver1.close();
		driver2.close();
	}
	@AfterClass 
	public static void teardownClass() {
		WebApp.stop();
	}

	@Test
	public void GanaAlberto() throws InterruptedException {
		boolean selected2 = buttonPlay1.isSelected();
		boolean selected1 = buttonPlay2.isSelected();
		waitAlberto.until(ExpectedConditions.elementSelectionStateToBe(buttonPlay2, selected1));
		waitAlejandro.until(ExpectedConditions.elementSelectionStateToBe(buttonPlay1, selected2));
		WebElement cell0 = driver1.findElement(By.id("cell-0"));
		cell0.click();
		WebElement cell1 = driver2.findElement(By.id("cell-4"));
		cell1.click();
		WebElement cell2 = driver1.findElement(By.id("cell-1"));
		cell2.click();
		WebElement cell3 = driver2.findElement(By.id("cell-5"));
		cell3.click();
		WebElement cell4 = driver1.findElement(By.id("cell-2"));
		cell4.click();
		
		waitAlberto.until(ExpectedConditions.alertIsPresent());
		waitAlejandro.until(ExpectedConditions.alertIsPresent());
		String textoFinal1 = driver1.switchTo().alert().getText();
		String textoFinal2 = driver2.switchTo().alert().getText();
		assertEquals(textoFinal1,textoFinal2);
		String ganador = textoFinal1.substring(0, textoFinal1.indexOf("wins")-1);
		assertEquals(ganador,"Alberto");
		String perdedor = textoFinal1.substring(textoFinal1.indexOf("wins") + 6, textoFinal1.indexOf("looses")-1);
		assertEquals(perdedor,"Alejandro");
	}

	@Test
	public void PierdeAlberto() throws InterruptedException {
		boolean selected2 = buttonPlay1.isSelected();
		boolean selected1 = buttonPlay2.isSelected();
		waitAlberto.until(ExpectedConditions.elementSelectionStateToBe(buttonPlay2, selected1));
		waitAlejandro.until(ExpectedConditions.elementSelectionStateToBe(buttonPlay1, selected2));
		WebElement cell0 = driver1.findElement(By.id("cell-4"));
		cell0.click();
		WebElement cell1 = driver2.findElement(By.id("cell-0"));
		cell1.click();
		WebElement cell2 = driver1.findElement(By.id("cell-5"));
		cell2.click();
		WebElement cell3 = driver2.findElement(By.id("cell-1"));
		cell3.click();
		WebElement cell4 = driver1.findElement(By.id("cell-7"));
		cell4.click();
		WebElement cell5 = driver2.findElement(By.id("cell-2"));
		cell5.click();
		
		waitAlberto.until(ExpectedConditions.alertIsPresent());
		waitAlejandro.until(ExpectedConditions.alertIsPresent());
		String textoFinal1 = driver1.switchTo().alert().getText();
		String textoFinal2 = driver2.switchTo().alert().getText();
		assertEquals(textoFinal1,textoFinal2);
		String ganador = textoFinal1.substring(0, textoFinal1.indexOf("wins")-1);
		assertEquals(ganador,"Alejandro");
		String perdedor = textoFinal1.substring(textoFinal1.indexOf("wins") + 6, textoFinal1.indexOf("looses")-1);
		assertEquals(perdedor,"Alberto");
	}
	@Test
	public void Empate() throws InterruptedException {
		boolean selected2 = buttonPlay1.isSelected();
		boolean selected1 = buttonPlay2.isSelected();
		waitAlberto.until(ExpectedConditions.elementSelectionStateToBe(buttonPlay2, selected1));
		waitAlejandro.until(ExpectedConditions.elementSelectionStateToBe(buttonPlay1, selected2));
		WebElement cell0 = driver1.findElement(By.id("cell-1"));
		cell0.click();
		WebElement cell1 = driver2.findElement(By.id("cell-0"));
		cell1.click();
		WebElement cell2 = driver1.findElement(By.id("cell-3"));
		cell2.click();
		WebElement cell3 = driver2.findElement(By.id("cell-2"));
		cell3.click();
		WebElement cell4 = driver1.findElement(By.id("cell-5"));
		cell4.click();
		WebElement cell5 = driver2.findElement(By.id("cell-4"));
		cell5.click();
		WebElement cell6 = driver1.findElement(By.id("cell-6"));
		cell6.click();
		WebElement cell7 = driver2.findElement(By.id("cell-7"));
		cell7.click();
		WebElement cell8 = driver1.findElement(By.id("cell-8"));
		cell8.click();
		
		waitAlberto.until(ExpectedConditions.alertIsPresent());
		waitAlejandro.until(ExpectedConditions.alertIsPresent());
		String textoFinal1 = driver1.switchTo().alert().getText();
		String textoFinal2 = driver2.switchTo().alert().getText();
		assertEquals(textoFinal1,textoFinal2);
		assertEquals(textoFinal1,"Draw!");
	}
}
