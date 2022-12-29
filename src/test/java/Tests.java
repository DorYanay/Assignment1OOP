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
        GroupAdmin g = new GroupAdmin();
        System.out.println("logger GroupAdmin memoryStats before registering members");
        logger.info(() -> JvmUtilities.memoryStats(g));
        System.out.println("logger GroupAdmin objectTotalSize after registering members");
        logger.info(() -> JvmUtilities.objectTotalSize(g));
        g.register(m1);
        g.register(m2);
        g.register(m3);
        g.register(m4);
        g.register(m5);
        System.out.println("logger GroupAdmin memoryStats after registering members");
        logger.info(() -> JvmUtilities.memoryStats(g));
        System.out.println("logger GroupAdmin objectTotalSize after registering members");
        logger.info(() -> JvmUtilities.objectTotalSize(g));
        //Registering twice the same member.
        g.register(m1);
        assertEquals(g.getSize(), 5);
        assertTrue(g.contains(m1));
        assertTrue(g.contains(m2));
        assertTrue(g.contains(m3));
        assertTrue(g.contains(m4));
        assertTrue(g.contains(m5));
        assertFalse(g.contains(m6));
    }

    @Test
    void unregister() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        ConcreteMember m4 = new ConcreteMember();
        ConcreteMember m5 = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(m1);
        g.register(m2);
        g.register(m3);
        g.register(m4);
        g.register(m5);
        System.out.println("logger GroupAdmin memoryStats after registering members");
        logger.info(() -> JvmUtilities.memoryStats(g));
        System.out.println("logger GroupAdmin objectTotalSize after registering members");
        logger.info(() -> JvmUtilities.objectTotalSize(g));
        g.unregister(m1);
        g.unregister(m2);
        g.unregister(m3);
        g.unregister(m4);
        g.unregister(m5);
        System.out.println("logger GroupAdmin memoryStats after unregistering members");
        logger.info(() -> JvmUtilities.memoryStats(g));
        System.out.println("logger GroupAdmin objectTotalSize after unregistering members");
        logger.info(() -> JvmUtilities.objectTotalSize(g));
        assertEquals(g.getSize(), 0);
        assertFalse(g.contains(m1));
        assertFalse(g.contains(m2));
        assertFalse(g.contains(m3));
        assertFalse(g.contains(m4));
        assertFalse(g.contains(m5));
    }

    @Test

    void GroupATest() {
        GroupAdmin g = new GroupAdmin();
        assertEquals(g.getCurrentINFO(),"");
        GroupAdmin g1 = new GroupAdmin("JAVA");
        assertEquals(g1.getCurrentINFO(),"JAVA");
        g1.undo();
        assertEquals(g1.getCurrentINFO(),"");
        GroupAdmin g2 = new GroupAdmin(null);
        assertEquals(g2.getCurrentINFO(),"");
    }
    @Test
    void notifyMembers() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();
        GroupAdmin g = new GroupAdmin();
        g.register(m1);
        g.register(m2);
        g.register(m3);
        //checking append.
        g.append("TRAIN");
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        System.out.println("logger GroupAdmin memoryStats after appending String");
        logger.info(() -> JvmUtilities.memoryStats(g));
        g.undo();
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        System.out.println("logger GroupAdmin memoryStats after undo");
        logger.info(() -> JvmUtilities.memoryStats(g));
        //checking empty string.
        g.append("");
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        g.undo();
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        //checking insert with string.
        g.insert(0, "TRAIN");
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        System.out.println("logger GroupAdmin memoryStats after inserting string");
        logger.info(() -> JvmUtilities.memoryStats(g));
        //checking delete
        g.delete(0, 4);
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        System.out.println("logger GroupAdmin memoryStats after deleting string");
        logger.info(() -> JvmUtilities.memoryStats(g));
        //Checking Insert and Delete with index out of bounds.
        g.insert(7, "WORLD");
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());
        g.delete(5, 7);
        assertEquals(m1.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m2.getCurrentINFO(), g.getCurrentINFO());
        assertEquals(m3.getCurrentINFO(), g.getCurrentINFO());

    }

}

