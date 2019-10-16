package mackansw.nackademin;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEverTest {

    private Customer p = new Customer("000004190732","Kalle Petterson","2016-01-10");
    private List<Customer> source = new ArrayList<>();

    private Path bill = Paths.get("bills.txt");
    private Path crash = Paths.get("crash.txt");
    private Path customers = Paths.get("customers.txt");
    private Path vists = Paths.get("visits.txt");
    private BestGymEver gym = new BestGymEver();

    @Test
    public final void logExistsTest() {
        assertFalse(gym.checkForFile(bill, false));
        assertTrue(gym.checkForFile(vists, false));
        assertFalse(gym.checkForFile(crash, false));
        assertTrue(gym.checkForFile(customers, false));
    }

    @Test
    public final void matchToListTest() {
        source.add(p);
        assertTrue(gym.matchToList("Kalle Petterson", source));
        assertFalse(gym.matchToList("Mikael Persbrant", source));
        assertTrue(gym.matchToList("000004190732", source));
        assertFalse(gym.matchToList("098701130345", source));
    }

    @Test
    public final void takeInputTest() {
        String fakeInput = "katt" + "\ndå" + "\n123";
        InputStream in = new ByteArrayInputStream(fakeInput.getBytes());
        System.setIn(in);

        BestGymEver gymm = new BestGymEver();

        assertEquals("katt", gymm.takeInput());
        assertNotEquals("hej", gymm.takeInput());
        assertEquals("123", gymm.takeInput());
    }
}