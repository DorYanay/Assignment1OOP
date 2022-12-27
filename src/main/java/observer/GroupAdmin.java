package observer;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private UndoableStringBuilder usb;
    // ArrayList<Member> members;
    HashSet<Member> members;

    public GroupAdmin(){
        //members = new ArrayList<Member>();
        members = new HashSet<Member>();
        usb = new UndoableStringBuilder();
    }

    @Override
    public void register(Member obj) {
        if(!members.isEmpty()) {
            if (!members.contains(obj)) {
                members.add(obj);
            }
        }
        else {
            members.add(obj);
        }

    }

    @Override
    public void unregister(Member obj) {
        members.remove(obj);
    }

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

    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyMembers();
    }

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

    @Override
    public void undo() {
        usb.undo();
        notifyMembers();
    }
    public String getCurrentMember() {
        return usb.toString();
    }
    public void notifyMembers(){
        for(Member member:members){
            member.update(usb);
        }
    }
    public int getSize(){
        return members.size();
    }
    public boolean contains(Member obj) {
        return members.contains(obj);
    }
}