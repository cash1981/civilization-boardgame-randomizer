package no.asgari.civilization.representations;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import no.asgari.civilization.ExcelSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UnitTest {

    @Test
    public void createInfantryTest() throws Exception{
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet infantrySheet = wb.getSheet(ExcelSheet.INFANTRY.toString());
        assertNotNull(infantrySheet);

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
        Queue<Infantry> infantryQueue = new LinkedList<>(infantryUnits);
        System.out.println(infantryQueue);

        wb.close();
    }

    @Test
    public void createMountedTest() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet mountedsheet = wb.getSheet(ExcelSheet.MOUNTED.toString());
        assertNotNull(mountedsheet);

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
        Queue<Mounted> mountedQueue = new LinkedList<>(mountedUnits);
        System.out.println(mountedQueue);

        wb.close();
    }

    @Test
    public void createArtilleryTest() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet artillerySheet = wb.getSheet(ExcelSheet.ARTILLERY.toString());
        assertNotNull(artillerySheet);

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
        Queue<Artillery> artilleryQueue = new LinkedList<>(artilleryUnits);
        System.out.println(artilleryQueue);

        wb.close();
    }

    @Test
    public void createAircraftTest() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("assets/gamedata-faf-waw.xlsx");
        Workbook wb = new XSSFWorkbook(in);
        Sheet aircraftSheet = wb.getSheet(ExcelSheet.AIRCRAFT.toString());
        assertNotNull(aircraftSheet);

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
        Queue<Aircraft> aircraftQueue = new LinkedList<>(aircraftUnits);
        System.out.println(aircraftQueue);

        wb.close();
    }


    @Test
    public void splitStringTest() {
        ImmutableList<String> strengths = ImmutableList.of("1,3", "1,3", "1,3", "1,3", "1,3", "3,1", "3,1", "3,1", "3,1", "3,1", "3,1", "2,2", "2,2", "2,2", "2,2", "2,2", "2,2", "2,2");
        List<Infantry> infantries = strengths.stream()
                .map(val -> createInfantry(val))
                .collect(Collectors.toList());

        System.out.println(infantries);
    }

    private static Infantry createInfantry(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);
        assertEquals(2, Iterables.size(split));

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Infantry(attack, health);
    }

    private static Artillery createArtillery(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);
        assertEquals(2, Iterables.size(split));

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Artillery(attack, health);
    }

    private static Mounted createMounted(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);
        assertEquals(2, Iterables.size(split));

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Mounted(attack, health);
    }


    private static Aircraft createAircraft(String string) {
        Iterable<String> split = Splitter.onPattern(",|\\.").omitEmptyStrings().trimResults().split(string);
        assertEquals(2, Iterables.size(split));

        int attack = Integer.parseInt(Iterables.get(split, 0));
        int health = Integer.parseInt(Iterables.get(split, 1));

        return new Aircraft(attack, health);
    }


    public static <T> Stream<T> stream(Iterable<T> in) {
        return StreamSupport.stream(in.spliterator(), false);
    }

    public static <T> Stream<T> parallelStream(Iterable<T> in) {
        return StreamSupport.stream(in.spliterator(), true);
    }

}
