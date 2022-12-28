import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import observer.Member;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests class
 *  @author Dor Yanay, Yevgeny Ivanov
 */
public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    // stub method to check external dependencies compatibility
    @Test
    public void test() {
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(() -> JvmUtilities.objectFootprint(s1));

        logger.info(() -> JvmUtilities.objectFootprint(s1, s2));

        logger.info(() -> JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }


    @Test
    void register() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        ConcreteMember m4 = new ConcreteMember();
        ConcreteMember m5 = new ConcreteMember();
        ConcreteMember m6 = new ConcreteMember();
        GroupAdmin ga = new GroupAdmin();
        ga.register(m1);
        ga.register(m2);
        ga.register(m3);
        ga.register(m4);
        ga.register(m5);
        assertEquals(ga.getSize(), 5);
        assertTrue(ga.contains(m1));
        assertTrue(ga.contains(m2));
        assertTrue(ga.contains(m3));
        assertTrue(ga.contains(m4));
        assertTrue(ga.contains(m5));
        assertFalse(ga.contains(m6));
    }

    @Test
    void unregister() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        ConcreteMember m4 = new ConcreteMember();
        ConcreteMember m5 = new ConcreteMember();
        GroupAdmin ga = new GroupAdmin();
        ga.register(m1);
        ga.register(m2);
        ga.register(m3);
        ga.register(m4);
        ga.register(m5);
        ga.unregister(m1);
        ga.unregister(m2);
        ga.unregister(m3);
        ga.unregister(m4);
        ga.unregister(m5);
        assertEquals(ga.getSize(), 0);
        assertFalse(ga.contains(m1));
        assertFalse(ga.contains(m2));
        assertFalse(ga.contains(m3));
        assertFalse(ga.contains(m4));
        assertFalse(ga.contains(m5));
    }

    @Test
    void notifyMembers() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin ga = new GroupAdmin();
        ga.register(m1);
        ga.register(m2);
        ga.register(m3);
        ga.append("TRAIN");
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.undo();
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.append("");
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.undo();
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.insert(0, "TRAIN");
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.delete(0, 4);
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.insert(7, "WORLD");
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());
        ga.delete(5, 7);
        assertEquals(m1.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), ga.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), ga.getCurrentINFO());

    }

}

