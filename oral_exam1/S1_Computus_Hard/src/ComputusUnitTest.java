import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ComputusUnitTest {
    ComputusAlgorithim easter = new ComputusAlgorithim();
    int[] marchdates = {1807, 1812, 1815, 1818, 1823, 1826, 1834, 1837, 1839, 1842};
    int[] aprildates = {1800, 1801, 1802, 1803, 1804, 1805, 1806, 1808, 1809, 1810};

    //Easter dates used from https://www.census.gov/srd/www/genhol/easter500.html

    @Test
    void testComputusMonth(){
        for(int i = 0; i < marchdates.length; i++){
            easter.getEasterDate(marchdates[i]);
            assertEquals(3, easter.getMonth());
        }
        for(int i = 0; i < aprildates.length; i++){
            easter.getEasterDate(aprildates[i]);
            assertEquals(4, easter.getMonth());
        }

    }
    @Test
    void testComputusDay(){
        for(int i = 0; i < marchdates.length; i ++){
            easter.getEasterDate(marchdates[i]);
            switch(marchdates[i]){
                case 1807:
                    assertEquals(29, easter.getDay());
                    break;
                case 1812:
                    assertEquals(29, easter.getDay());
                    break;
                case 1815:
                    assertEquals(26, easter.getDay());
                    break;
                case 1818:
                    assertEquals(22, easter.getDay());
                    break;
                case 1823:
                    assertEquals(30, easter.getDay());
                    break;
                case 1826:
                    assertEquals(26, easter.getDay());
                    break;
                case 1834:
                    assertEquals(30, easter.getDay());
                    break;
                case 1837:
                    assertEquals(26, easter.getDay());
                    break;
                case 1839:
                    assertEquals(31, easter.getDay());
                    break;
                case 1842:
                    assertEquals(27, easter.getDay());
                    break;
            }
            easter.getEasterDate(aprildates[i]);
            switch(aprildates[i]){
                case 1800:
                    assertEquals(13, easter.getDay());
                    break;
                case 1801:
                    assertEquals(5, easter.getDay());
                    break;
                case 1802:
                    assertEquals(18, easter.getDay());
                    break;
                case 1803:
                    assertEquals(10, easter.getDay());
                    break;
                case 1804:
                    assertEquals(1, easter.getDay());
                    break;
                case 1805:
                    assertEquals(14, easter.getDay());
                    break;
                case 1806:
                    assertEquals(6, easter.getDay());
                    break;
                case 1808:
                    assertEquals(17, easter.getDay());
                    break;
                case 1809:
                    assertEquals(2, easter.getDay());
                    break;
                case 1810:
                    assertEquals(22, easter.getDay());
                    break;
            }

        }
    }
}
