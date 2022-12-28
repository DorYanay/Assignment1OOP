package observer;
/**
 * @author Dor Yanay, Yevgeny Ivanov
 * In this assignment we take the UndoableStringbuilder and making an ConcreteMember class that Implements Member Class from observer.
 */

public class ConcreteMember implements Member{

    private UndoableStringBuilder undosb;
    private String member;

    /**
     * Implementing the update command from Member class.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        undosb = usb;
        member = undosb.toString();
    }

    /**
     * The command getCurrentINFO is getting the current info (string) inside the member at the moment.
     * @return toString.
     */
    public String getCurrentINFO(){
        return member;
    }


}