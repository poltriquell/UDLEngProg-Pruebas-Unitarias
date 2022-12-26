package data;

import exceptions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    public void getGoalTest() throws WrongGoalFormatException {
        Goal goal = new Goal("Primary School Teacher", goalTypes.WORKWITHMINORS);
        String detailedGoal = "Primary School Teacher";
        goalTypes goalType = goalTypes.WORKWITHMINORS;

        assertEquals(detailedGoal, goal.getDetailedGoal());
        assertEquals(goalType, goal.getGoalType());
    }

    @Test
    public void nullDetailedGoalTest() {
        NullPointerException nullDetailed = assertThrows(NullPointerException.class, () -> new Goal(null, goalTypes.GAMESECTOR));
        assertEquals("You must detail the goal besides the GoalType. This field cannot be null.", nullDetailed.getMessage());
    }

    @Test
    public void nullGoalTypeTest() {
        NullPointerException nullGoalType = assertThrows(NullPointerException.class, () -> new Goal("Primary School Teacher", null));
        assertEquals("The GoalType cannot be null.", nullGoalType.getMessage());
    }

    @Test
    public void wrongDetailedGoalTest() {
        WrongGoalFormatException wrongDetailed = assertThrows(WrongGoalFormatException.class, () -> new Goal("Primary School Teacher", goalTypes.NOT_VALID_GOALTYPE));
        assertEquals("Incorrect GoalType format. This GoalType does not exist.", wrongDetailed.getMessage());
    }

    @Test
    public void correctGoalTest() {
        assertDoesNotThrow(() -> new Goal("Major", goalTypes.PUBLICWORKERS));
    }

}
