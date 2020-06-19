interface Destroyable {
    public void destroyObj();
}

public class Invader implements Destroyable {
    public boolean active;
    public Invader() {
        active = true;
    }

    public void destroyObj() {
        active = false;
        //생략
    }
    // 생략
}