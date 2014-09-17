package no.asgari.civilization.excel;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import no.asgari.civilization.SheetName;
import no.asgari.civilization.representations.Aircraft;
import no.asgari.civilization.representations.Artillery;
import no.asgari.civilization.representations.Infantry;
import no.asgari.civilization.representations.Mounted;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class UnitReader {

    public LinkedList<Aircraft> aircraftList;
    public LinkedList<Artillery> artilleryList;
    public LinkedList<Mounted> mountedList;
    public LinkedList<Infantry> infantryList;

    public void readAllUnitsFromExcel() throws IOException {
        createInfantryTest();
        createMountedTest();
        createArtilleryTest();
        createAircraftTest();
    }

    private void createInfantryTest() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet infantrySheet = wb.getSheet(SheetName.INFANTRY.toString());

        List<Cell> unfilteredCells = new ArrayList<>();
        infantrySheet.forEach(row -> row.forEach(unfilteredCells::add));

        List<Infantry> infantryUnits = unfilteredCells.stream()
                .filter(p -> !p.toString().isEmpty())
                .filter(p -> !p.toString().equals("RAND()"))
                .filter(p -> p.getRow().getRowNum() != 0)
                .filter(cell -> cell.getColumnIndex() == 0)
                .map(cell -> createInfantry(cell.toString()))
                .collect(Collectors.toList());

        Collections.shuffle(infantryUnits);

        //Now we want to take every other one
        infantryList = new LinkedList<>(infantryUnits);
        System.out.println(infantryList);

        wb.close();
    }

    private void createMountedTest() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet mountedsheet = wb.getSheet(SheetName.MOUNTED.toString());

        List<Cell> unfilteredCells = new ArrayList<>();
        mountedsheet.forEach(row -> row.forEach(unfilteredCells::add));

        List<Mounted> mountedUnits = unfilteredCells.stream()
                .filter(p -> !p.toString().isEmpty())
                .filter(p -> !p.toString().equals("RAND()"))
                .filter(p -> p.getRow().getRowNum() != 0)
                .filter(cell -> cell.getColumnIndex() == 0)
                .map(cell -> createMounted(cell.toString()))
                .collect(Collectors.toList());

        Collections.shuffle(mountedUnits);

        //Now we want to take every other one
        mountedList = new LinkedList<>(mountedUnits);
        System.out.println(mountedList);

        wb.close();
    }

    private void createArtilleryTest() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet artillerySheet = wb.getSheet(SheetName.ARTILLERY.toString());

        List<Cell> unfilteredCells = new ArrayList<>();
        artillerySheet.forEach(row -> row.forEach(unfilteredCells::add));

        List<Artillery> artilleryUnits = unfilteredCells.stream()
                .filter(p -> !p.toString().isEmpty())
                .filter(p -> !p.toString().equals("RAND()"))
                .filter(p -> p.getRow().getRowNum() != 0)
                .filter(cell -> cell.getColumnIndex() == 0)
                .map(cell -> createArtillery(cell.toString()))
                .collect(Collectors.toList());

        Collections.shuffle(artilleryUnits);

        //Now we want to take every other one
        artilleryList = new LinkedList<>(artilleryUnits);
        System.out.println(artilleryList);

        wb.close();
    }

    private void createAircraftTest() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet aircraftSheet = wb.getSheet(SheetName.AIRCRAFT.toString());

        List<Cell> unfilteredCells = new ArrayList<>();
        aircraftSheet.forEach(row -> row.forEach(unfilteredCells::add));

        List<Aircraft> aircraftUnits = unfilteredCells.stream()
                .filter(p -> !p.toString().isEmpty())
                .filter(p -> !p.toString().equals("RAND()"))
                .filter(p -> p.getRow().getRowNum() != 0)
                .filter(cell -> cell.getColumnIndex() == 0)
                .map(cell -> createAircraft(cell.toString()))
                .collect(Collectors.toList());

        Collections.shuffle(aircraftUnits);

        //Now we want to take every other one
        aircraftList = new LinkedList<>(aircraftUnits);
        System.out.println(aircraftList);

        wb.close();
    }


    private static Infantry createInfantry(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Infantry(attack, health);
    }

    private static Artillery createArtillery(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Artillery(attack, health);
    }

    private static Mounted createMounted(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Mounted(attack, health);
    }


    private static Aircraft createAircraft(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Aircraft(attack, health);
    }

}