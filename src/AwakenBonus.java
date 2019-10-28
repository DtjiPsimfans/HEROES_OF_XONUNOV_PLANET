/**
 * This class contains attributes of awaken bonus gained for awakening a hero.
 * @author Dtji Psimfans
 * */


public class AwakenBonus {
    // Private attributes
    private double attackSpeedUp;
    private double critRateUp;
    private double critDamageUp;
    private double evasionChanceUp;
    private double resistanceUp;
    private double accuracyUp;
    private double extraTurnChanceUp;
    private double counterattackChanceUp;
    private Skill newSkill;
    private Skill skillToBeReplaced;
    private Skill upgradedSkill;

    // Constructor
    public AwakenBonus(double attackSpeedUp, double critRateUp, double critDamageUp, double evasionChanceUp, double
                       resistanceUp, double accuracyUp, double extraTurnChanceUp, double counterattackChanceUp,
                       Skill newSkill, Skill skillToBeReplaced, Skill upgradedSkill){
        setAttackSpeedUp(attackSpeedUp);
        setCritRateUp(critRateUp);
        setCritDamageUp(critDamageUp);
        setEvasionChanceUp(evasionChanceUp);
        setResistanceUp(resistanceUp);
        setAccuracyUp(accuracyUp);
        setExtraTurnChanceUp(extraTurnChanceUp);
        setCounterattackChanceUp(counterattackChanceUp);
        setNewSkill(newSkill);
        setSkillToBeReplaced(skillToBeReplaced);
        setUpgradedSkill(upgradedSkill);
    }

    public double getAttackSpeedUp() {
        return attackSpeedUp;
    }

    public void setAttackSpeedUp(double attackSpeedUp) {
        if (attackSpeedUp >= 0) {
            this.attackSpeedUp = attackSpeedUp;
        }
    }

    public double getCritRateUp() {
        return critRateUp;
    }

    public void setCritRateUp(double critRateUp) {
        if (critRateUp >= 0) {
            this.critRateUp = critRateUp;
        }
    }

    public double getCritDamageUp() {
        return critDamageUp;
    }

    public void setCritDamageUp(double critDamageUp) {
        if (critDamageUp > 0) {
            this.critDamageUp = critDamageUp;
        }
    }

    public double getEvasionChanceUp() {
        return evasionChanceUp;
    }

    public void setEvasionChanceUp(double evasionChanceUp) {
        if (evasionChanceUp > 0) {
            this.evasionChanceUp = evasionChanceUp;
        }
    }

    public double getResistanceUp() {
        return resistanceUp;
    }

    public void setResistanceUp(double resistanceUp) {
        if (resistanceUp > 0) {
            this.resistanceUp = resistanceUp;
        }
    }

    public double getAccuracyUp() {
        return accuracyUp;
    }

    public void setAccuracyUp(double accuracyUp) {
        if (accuracyUp > 0) {
            this.accuracyUp = accuracyUp;
        }
    }

    public double getExtraTurnChanceUp() {
        return extraTurnChanceUp;
    }

    public void setExtraTurnChanceUp(double extraTurnChanceUp) {
        if (extraTurnChanceUp > 0) {
            this.extraTurnChanceUp = extraTurnChanceUp;
        }
    }

    public double getCounterattackChanceUp() {
        return counterattackChanceUp;
    }

    public void setCounterattackChanceUp(double counterattackChanceUp) {
        if (counterattackChanceUp > 0) {
            this.counterattackChanceUp = counterattackChanceUp;
        }
    }

    public Skill getNewSkill() {
        return newSkill;
    }

    public void setNewSkill(Skill newSkill) {
        this.newSkill = newSkill;
    }

    public Skill getUpgradedSkill() {
        return upgradedSkill;
    }

    public Skill getSkillToBeReplaced() {
        return skillToBeReplaced;
    }

    public void setSkillToBeReplaced(Skill skillToBeReplaced) {
        this.skillToBeReplaced = skillToBeReplaced;
    }

    public void setUpgradedSkill(Skill upgradedSkill) {
        this.upgradedSkill = upgradedSkill;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (!getClass().equals(obj.getClass())){
            return false;
        }

        if (!(obj instanceof AwakenBonus)){
            return false;
        }
        else{
            return attackSpeedUp == ((AwakenBonus) obj).attackSpeedUp && critRateUp == ((AwakenBonus) obj).critRateUp &&
                    critDamageUp == ((AwakenBonus) obj).critDamageUp && evasionChanceUp == ((AwakenBonus) obj).evasionChanceUp &&
                    resistanceUp == ((AwakenBonus) obj).resistanceUp && accuracyUp == ((AwakenBonus) obj).accuracyUp &&
                    extraTurnChanceUp == ((AwakenBonus) obj).extraTurnChanceUp && counterattackChanceUp == ((AwakenBonus) obj).counterattackChanceUp &&
                    newSkill.equals(((AwakenBonus) obj).newSkill) && skillToBeReplaced.equals(((AwakenBonus) obj).skillToBeReplaced) &&
                    upgradedSkill.equals(((AwakenBonus) obj).upgradedSkill);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
