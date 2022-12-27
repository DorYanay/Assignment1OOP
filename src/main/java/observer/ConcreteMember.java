package observer;


public class ConcreteMember implements Member{

    private UndoableStringBuilder undosb;
    private String member;


    @Override
    public void update(UndoableStringBuilder usb) {
        undosb = usb;
        member = undosb.toString();
    }

    public String getCurrentMember(){
        return member;
    }


}