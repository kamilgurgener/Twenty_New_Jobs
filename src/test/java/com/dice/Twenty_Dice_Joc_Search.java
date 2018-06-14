package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Twenty_Dice_Joc_Search {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		List<String> list = new ArrayList<>();
		list.add("Application Developer");
		list.add("Application Support Analyst");
		list.add("Chief Information Officer (CIO) ");
		list.add("Cloud Architect");
		list.add("Cloud Consultant");
		list.add("Cloud Services Developer");
		list.add("Cloud Software and Network Engineer");
		list.add("Cloud System Engineer");
		list.add("Computer and Information Research Scientist");
		list.add("Computer Network Architect");
		list.add("Computer Programmer");
		list.add("Computer Systems Analyst");
		list.add("Computer Systems Manager");
		list.add("Customer Support Administrator");
		list.add("Customer Support Specialist");
		list.add("Data Quality Manager");
		list.add("Java Developer");
		list.add("Junior Software Engineer");
		list.add("NET Developer");
		list.add("Network Administrator");

		List<String> newList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			String url = "https://www.dice.com";
			driver.get(url);

			driver.findElement(By.cssSelector("#search-field-keyword")).clear();
			driver.findElement(By.cssSelector("#search-field-keyword")).sendKeys(list.get(i));

			String zipCode = "02111";
			driver.findElement(By.cssSelector("#search-field-location")).clear();
			driver.findElement(By.cssSelector("#search-field-location")).sendKeys(zipCode);

			driver.findElement(By.cssSelector("#findTechJobs")).click();

			String count = driver.findElement(By.cssSelector("#posiCountId")).getText();
			newList.add(list.get(i) + "-" + count.replaceAll(",", ""));
		}

		System.out.println(newList);

		List<String> lst = new ArrayList<>(newList);

		try {
			FileReader reader = new FileReader("Job_List.txt");
			BufferedReader br = new BufferedReader(reader);
			String str;
			while ((str = br.readLine()) != null) {
				String url = "https://www.dice.com";
				driver.get(url);

				driver.findElement(By.cssSelector("#search-field-keyword")).clear();
				driver.findElement(By.cssSelector("#search-field-keyword")).sendKeys(str);

				String zipCode = "02111";
				driver.findElement(By.cssSelector("#search-field-location")).clear();
				driver.findElement(By.cssSelector("#search-field-location")).sendKeys(zipCode);

				driver.findElement(By.cssSelector("#findTechJobs")).click();

				String count = driver.findElement(By.cssSelector("#posiCountId")).getText();
				newList.add(str + "-" + count.replaceAll(",", ""));
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}
		driver.close();
		System.out.println(lst);

	}

}
