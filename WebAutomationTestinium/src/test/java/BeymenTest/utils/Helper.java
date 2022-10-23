package BeymenTest.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scroll2Element(WebElement element) {
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).perform();
    }

    public static Cell getFromExcel(String file, String page, int row, int column)  {
        Cell cell = null;
        try {
            FileInputStream stream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(stream);
            cell = workbook.getSheet(page).getRow(row).getCell(column);
            workbook.close();
            stream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return cell;
    }

    public static void writeToFile(String text, String file){
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(String.valueOf(text));
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
