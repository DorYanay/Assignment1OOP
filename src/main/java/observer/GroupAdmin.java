package observer;
import java.util.HashSet;

/**
 * @author Dor Yanay, Yevgeny Ivanov
 * In this assignment we take the UndoableStringbuilder and making an GroupAdmin class that Implements Sender Class from observer
 * GroupAdmin = the Master of all the Members that implements the Member class from observer.
 */
public class GroupAdmin implements Sender {
    private UndoableStringBuilder usb;
    HashSet<Member> members;

    /**
     * Constructor
     */
    public GroupAdmin(){
        members = new HashSet<Member>();
        usb = new UndoableStringBuilder();
    }
    public GroupAdmin(String str) {
        members = new HashSet<Member>();
        usb = new UndoableStringBuilder(str);
    }

    /**
     * Implements the command register from Sender (observer).
     * @param obj = the Member we get from the user. at the moment we get ConcreteMember
     */
    @Override
    public void register(Member obj) {
        members.add(obj);
    }
    /**
     * Implements the command unregister from Sender (observer).
     * @param obj = the Member we get from the user. at the moment we get ConcreteMember
     */
    @Override
    public void unregister(Member obj) {
        members.remove(obj);
    }
    /**
     * Implements the command insert from Sender (observer).
     * @param offset = the starting index you want to insert the string.
     * @param obj = the string you want to input, from the offset we got.
     */
    @Override
    public void insert(int offset, String obj) {
        try {
            usb.insert(offset, obj);
            notifyMembers();
        }
        catch (StringIndexOutOfBoundsException e) {
                System.err.println("Delete - Index out of bounds");
                e.printStackTrace();
            }
    }
    /**
     * Implements the command append from Sender (observer).
     * @param obj = the String we want to add.
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyMembers();
    }
    /**
     * Implements the command delete from Sender (observer).
     * @param start = the index you want to start the deletion from.
     * @param end = the index you want to end the deletion.
     */
    @Override
    public void delete(int start, int end) {
        try {
            usb.delete(start,end);
            notifyMembers();
        }
        catch (StringIndexOutOfBoundsException e) {
            System.err.println("Delete - Index out of bounds");
            e.printStackTrace();
        }

    }
    /**
     * Implements the command undo from Sender (observer).
     * removing the last command (delete / Insert / append / append) and setting it to the data that was before.
     */
    @Override
    public void undo() {
        usb.undo();
        notifyMembers();
    }
    /**
     * The command getCurrentINFO is getting the current info (string) that the user put inside.
     * Implements the command delete from Sender (observer).
     */
    public String getCurrentINFO() {
        return usb.toString();
    }
    /**
     * The command notifyMembers update all the users on the current data that the user put inside.
     */
    public void notifyMembers(){
        for(Member member:members){
            member.update(usb);
        }
    }
    /**
     * The command getSize is getting the number of members registered at the moment.
     */
    public int getSize(){
        return members.size();
    }
    /**
     * The command contains is checking if a certain member is inside the group.
     */
    public boolean contains(Member obj) {
        return members.contains(obj);
    }
}