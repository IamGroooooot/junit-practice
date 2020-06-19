import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ControllerTest {
    // Test case to check that if it rejects selectLevel 
    @Test 
    public void selectLevel_selectLevel_rejectInvalidLevel() {
        // 1. Set Up
        Controller fixture = new Controller();

        // make invalid levels to test
        String[] invalidLevels = {"level9", "", null};
        
        // test for all invalid levels
        for (String invalidLevel: invalidLevels) {
            // set Controller's state to SelectLevel
            fixture.state = State.SelectLevel;

            // 2. Act 
            boolean result = fixture.selectLevel(invalidLevel);

            // 3. Verify
            // should be false
            assertEquals(false, result);
            // state should be not changed
            assertEquals(State.SelectLevel, fixture.state);
        }
    }

    // Test case to check that state SelectLevel and Playing is Visited 
    @Test
    public void selectLevel_selectLevel_toPlaying() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to SelectLevel
        fixture.state = State.SelectLevel;
        // make invalid level
        String validLevel = "level5";

        // 2. Act 
        boolean result = fixture.selectLevel(validLevel);

        // 3. Verify
        // should be true
        assertEquals(true, result);
        // state should be changed to Playing 
        assertEquals(State.Playing, fixture.state);
    }

    // Test case to check that state Playing when destoryed invaders  
    @Test
    public void destoryInvader_playing_minusInvadersCnt() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to SelectLevel
        fixture.state = State.Playing;
        // set Controller's invaderCounts to 10
        fixture.set_leftInvaders(10);
        // stub invader
        Invader stubInvader = new Invader();

        // 2. Act 
        fixture.destroyObject(stubInvader);

        // 3. Verify
        // should be not active
        assertEquals(false, stubInvader.active);
        // leftInvaders should be minused and become 9
        assertEquals(9, fixture.get_leftInvaders());
    }

    // Test case to check transition to state GameClear
    @Test
    public void gameClear_playing_toGameClear() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to SelectLevel
        fixture.state = State.Playing;
        // set Controller's invaderCounts to 1
        fixture.set_leftInvaders(1);
        // stub invader
        Invader stubInvader = new Invader();

        // 2. Act 
        fixture.destroyObject(stubInvader);

        // 3. Verify
        // should be not active
        assertEquals(false, stubInvader.active);
        // leftInvaders should be minused and become 0
        assertEquals(0, fixture.get_leftInvaders());
        // state should be changed to Playing 
        assertEquals(State.GameClear, fixture.state);
    }

    // Test case to check transition 
    // {State.Playing, State.Pause, State.ShareAndView} to state GameClear
    @Test
    public void goBackBtnClicked_fromStates_toSelectLevel() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to test from
        State[] toTestStates = {State.Playing, State.Paused, State.ShareAndViewScore, State.GameClear};

        for (State from : toTestStates){
            fixture.state = from;

            // 2. Act 
            fixture.gotoMenu();

            // 3. Verify
            // state should be changed to Playing 
            assertEquals(State.SelectLevel, fixture.state);
        }
    }

    // Test case to check transition 
    // {State.SelectLevel, State.GameClear} to state ShareAndViewScore
    @Test
    public void shareAndViewBtnClicked_fromStates_toShareAndViewScore() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to test from
        State[] toTestStates = {State.SelectLevel, State.GameClear};

        for (State from : toTestStates){
            fixture.state = from;

            // 2. Act 
            fixture.gotoShareAndViewScore();

            // 3. Verify
            // state should be changed to Playing 
            assertEquals(State.ShareAndViewScore, fixture.state);
        }
    }

    // Test case to check transition 
    // state Playing to state Pause
    @Test
    public void pauseBtnClicked_playing_toPause() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to Playing
        fixture.state = State.Playing;

        // 2. Act 
        fixture.pause();

        // 3. Verify
        // state should be changed to Paused 
        assertEquals(State.Paused, fixture.state);
        // the music shoud be not playing
        assertEquals(false, fixture.isPlayingMusic);
    }

    // Test case to check transition 
    // state Playing to state Pause
    @Test
    public void resumeBtnClicked_pause_toPlaying() {
        // 1. Set Up
        Controller fixture = new Controller();
        // set Controller's state to Playing
        fixture.state = State.Paused;

        // 2. Act 
        fixture.resume();

        // 3. Verify
        // state should be changed to Playing 
        assertEquals(State.Playing, fixture.state);
        // the music shoud be playing
        assertEquals(true, fixture.isPlayingMusic);
    }
}


