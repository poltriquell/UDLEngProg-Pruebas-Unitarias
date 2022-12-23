package data;
import data.goalTypes;

final public class Goal {

    private final String detailedGoal;
    private final goalTypes goalType;

    public Goal(String detailedGoal, goalTypes goalType) {
        checkValidGoal(detailedGoal, goalType);
        this.detailedGoal = detailedGoal;
        this.goalType = goalType;
    }

    private void checkValidGoal(String detailedGoal, goalTypes goalType) {
        if (detailedGoal == null) throw new NullPointerException("You must detail the goal besides the GoalType. This field cannot be null.");
        if (goalType == null) throw new NullPointerException("The GoalType cannot be null.");
        if (wrongDetailedGoal(goalType)) throw new IllegalArgumentException("Incorrect GoalType format. This GoalType does not exist.");
    }

    private boolean wrongDetailedGoal(goalTypes goalType) {
        return goalType != goalTypes.WORKWITHMINORS && goalType != goalTypes.GAMESECTOR
                && goalType != goalTypes.PUBLICWORKERS && goalType != goalTypes.PUBLICADMINCONSORTIUM;
    }

    public String getDetailedGoal() {
        return detailedGoal;
    }

    public goalTypes getGoalType() {
        return goalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (detailedGoal != null ? !detailedGoal.equals(goal.detailedGoal) : goal.detailedGoal != null) return false;
        return goalType == goal.goalType;
    }

    @Override
    public int hashCode() {
        int result = detailedGoal != null ? detailedGoal.hashCode() : 0;
        result = 31 * result + (goalType != null ? goalType.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "Goal{" + "detailedGoal='" + detailedGoal + '\'' + "goalType" + goalType + '}';
    }
}
