import java.util.Arrays;

public class Controller {
    State state;
    int[][] transitionTable = {/* 생략 */};
    KeyCode keycode;
    Object[] collidedObjects;
    boolean isPlayingMusic;
    private int leftInvaders;
    String[] levels = {"level1", "level2", "level3", "level4", "level5"};

    // stub ctor 
    public Controller() {
        state = State.SelectLevel;
        leftInvaders = 10;
        isPlayingMusic = true;
    }

    public boolean selectLevel(String level) {
        boolean isValid = Arrays.asList(levels).contains(level);
        if (isValid) {
            loadLevel(level);
            state = State.Playing;
        }

        return Arrays.asList(levels).contains(level);
    }

    public void loadLevel(String level) {
        // 생략
    }

    public void destroyObject(Destroyable obj) {
        set_leftInvaders(get_leftInvaders() - 1);
        obj.destroyObj();
        // 생략
    }

    public void gotoMenu() {
        state = State.SelectLevel;
        // 생략
    }

    public void gotoShareAndViewScore(){
        state = State.ShareAndViewScore;
        // 생략
    }

    public void pause() { 
        state = State.Paused;
        pauseMusic();
        // 생략
    }

    public void resume() { 
        state = State.Playing;
        resumeMusic();
        // 생략
    }

    public void pauseMusic() {
        isPlayingMusic = false;
        // 생략
    }

    public void resumeMusic() {
        isPlayingMusic = true;
        // 생략
    }

    public void set_leftInvaders(int val) {
        leftInvaders = val;
        if (leftInvaders <= 0) {
            state = state.GameClear;
        }
    }

    public int get_leftInvaders() {
        return leftInvaders;
    }

    // ... 생략
}