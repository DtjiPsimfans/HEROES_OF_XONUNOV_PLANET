/**
 * This class contains attributes of secondary awaken bonus gained for secondary awakening a hero.
 * @author Dtji Psimfans
 * */


public class SecondaryAwakenBonus extends AwakenBonus {
    // Private attributes
    private Skill secondNewSkill;
    private Skill secondSkillToBeReplaced;
    private Skill secondUpgradedSkill;

    // Constructor
    public SecondaryAwakenBonus(double attackSpeedUp, double critRateUp, double critDamageUp, double evasionChanceUp, double
            resistanceUp, double accuracyUp, double extraTurnChanceUp, double counterattackChanceUp,
                       Skill newSkill, Skill skillToBeReplaced, Skill upgradedSkill, Skill secondNewSkill,
                                Skill secondSkillToBeReplaced, Skill secondUpgradedSkill){
        super(attackSpeedUp, critRateUp, critDamageUp, evasionChanceUp, resistanceUp, accuracyUp, extraTurnChanceUp,
                counterattackChanceUp, newSkill, skillToBeReplaced, upgradedSkill);
        setSecondNewSkill(secondNewSkill);
        setSecondSkillToBeReplaced(secondSkillToBeReplaced);
        setSecondUpgradedSkill(secondUpgradedSkill);
    }

    public Skill getSecondNewSkill() {
        return secondNewSkill;
    }

    public void setSecondNewSkill(Skill secondNewSkill) {
        this.secondNewSkill = secondNewSkill;
    }

    public Skill getSecondSkillToBeReplaced() {
        return secondSkillToBeReplaced;
    }

    public void setSecondSkillToBeReplaced(Skill secondSkillToBeReplaced) {
        this.secondSkillToBeReplaced = secondSkillToBeReplaced;
    }

    public Skill getSecondUpgradedSkill() {
        return secondUpgradedSkill;
    }

    public void setSecondUpgradedSkill(Skill secondUpgradedSkill) {
        this.secondUpgradedSkill = secondUpgradedSkill;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (!getClass().equals(obj.getClass())){
            return false;
        }

        if (!(obj instanceof SecondaryAwakenBonus)){
            return false;
        }
        else{
            return getAttackSpeedUp() == ((AwakenBonus) obj).getAttackSpeedUp() && getCritRateUp() == ((AwakenBonus) obj).getCritRateUp() &&
                    getCritDamageUp() == ((AwakenBonus) obj).getCritDamageUp() && getEvasionChanceUp() == ((AwakenBonus) obj).getEvasionChanceUp() &&
                    getResistanceUp() == ((AwakenBonus) obj).getResistanceUp() && getAccuracyUp() == ((AwakenBonus) obj).getAccuracyUp() &&
                    getExtraTurnChanceUp() == ((AwakenBonus) obj).getExtraTurnChanceUp() && getCounterattackChanceUp() == ((AwakenBonus) obj).getCounterattackChanceUp() &&
                    getNewSkill().equals(((AwakenBonus) obj).getNewSkill()) && getSkillToBeReplaced().equals(((AwakenBonus) obj).getSkillToBeReplaced()) &&
                    getUpgradedSkill().equals(((AwakenBonus) obj).getUpgradedSkill()) && secondNewSkill.equals(((SecondaryAwakenBonus) obj).secondNewSkill) &&
                    secondSkillToBeReplaced.equals(((SecondaryAwakenBonus) obj).secondSkillToBeReplaced) &&
                    secondUpgradedSkill.equals(((SecondaryAwakenBonus) obj).secondUpgradedSkill);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
