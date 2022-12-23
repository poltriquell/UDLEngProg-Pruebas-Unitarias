package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    public void getGoalTest() {
        Goal goal = new Goal("Primary School Teacher", goalTypes.WORKWITHMINORS);
        String detailedGoal = "Primary School Teacher";
        goalTypes goalType = goalTypes.WORKWITHMINORS;

        assertEquals(detailedGoal, goal.getDetailedGoal());
        assertEquals(goalType, goal.getGoalType());
    }

}
