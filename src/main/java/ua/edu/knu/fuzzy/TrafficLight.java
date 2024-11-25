package ua.edu.knu.fuzzy;

import com.fuzzylite.Engine;
import com.fuzzylite.activation.General;
import com.fuzzylite.defuzzifier.Centroid;
import com.fuzzylite.norm.s.Maximum;
import com.fuzzylite.norm.t.Minimum;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Gaussian;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;

public class TrafficLight {
    private Engine fuzzyLogicEngine = new Engine();
    private double greenTime;
    private double carsNS;
    private double carsWE;
    private double deltaGreenTime;

    public TrafficLight() {
        fuzzyLogicEngine.setName("traffic-light");
        fuzzyLogicEngine.setDescription("traffic-light");

        addGreenTimeVariable();

        addCarsNSVariable();

        addCarsWEVariable();

        addDeltaGreenTimeVariable();

        addMamdaniRules();

        StringBuilder status = new StringBuilder();
        if (!fuzzyLogicEngine.isReady(status))
            throw new RuntimeException("[engine error] engine is not ready:n" + status);

    }

    public double getDeltaGreenTime() {
        InputVariable inputGreenTime = fuzzyLogicEngine.getInputVariable("GreenTime");
        InputVariable inputCarsNS = fuzzyLogicEngine.getInputVariable("CarsNS");
        InputVariable inputCarsWE = fuzzyLogicEngine.getInputVariable("CarsWE");
        OutputVariable  outputDeltaGreenTime = fuzzyLogicEngine.getOutputVariable("DeltaGreenTime");

        inputGreenTime.setValue(greenTime);
        inputCarsNS.setValue(carsNS);
        inputCarsWE.setValue(carsWE);

        fuzzyLogicEngine.process();

        deltaGreenTime = outputDeltaGreenTime.getValue();

        return deltaGreenTime;
    }

    public void setGreenTime(double greenTime) {
        this.greenTime = greenTime;
    }

    public void setCarsNS(double carsNS) {
        this.carsNS = carsNS;
    }

    public void setCarsWE(double carsWE) {
        this.carsWE = carsWE;
    }

    private void addMamdaniRules() {
        RuleBlock ruleBlock = new RuleBlock();
        ruleBlock.setName("mamdani");
        ruleBlock.setDescription("Mamdani inference");
        ruleBlock.setEnabled(true);
        ruleBlock.setConjunction(new Minimum());
        ruleBlock.setDisjunction(new Maximum());
        ruleBlock.setImplication(new Minimum());
        ruleBlock.setActivation(new General());
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VerySmall and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VerySmall and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VerySmall and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VerySmall and CarsWE is Big " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VerySmall and CarsWE is VeryBig " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Small and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Small and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Small and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Small and CarsWE is Big " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Small and CarsWE is VeryBig " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Medium and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Medium and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Medium and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Medium and CarsWE is Big " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Medium and CarsWE is VeryBig " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Big and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Big and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Big and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Big and CarsWE is Big " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is Big and CarsWE is VeryBig " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VeryBig and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VeryBig and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VeryBig and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VeryBig and CarsWE is Big " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Small and CarsNS is VeryBig and CarsWE is VeryBig " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VerySmall and CarsWE is VerySmall " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VerySmall and CarsWE is Small " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VerySmall and CarsWE is Medium " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VerySmall and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VerySmall and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Small and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Small and CarsWE is Small " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Small and CarsWE is Medium " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Small and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Small and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Medium and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Medium and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Medium and CarsWE is Medium " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Medium and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Medium and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Big and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Big and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Big and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Big and CarsWE is Big " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is Big and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VeryBig and CarsWE is VerySmall " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VeryBig and CarsWE is Small " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VeryBig and CarsWE is Medium " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VeryBig and CarsWE is Big " +
                "then DeltaGreenTime is Increase", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Medium and CarsNS is VeryBig and CarsWE is VeryBig " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VerySmall and CarsWE is VerySmall " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VerySmall and CarsWE is Small " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VerySmall and CarsWE is Medium " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VerySmall and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VerySmall and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Small and CarsWE is VerySmall " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Small and CarsWE is Small " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Small and CarsWE is Medium " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Small and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Small and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Medium and CarsWE is VerySmall " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Medium and CarsWE is Small " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Medium and CarsWE is Medium " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Medium and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Medium and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Big and CarsWE is VerySmall " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Big and CarsWE is Small " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Big and CarsWE is Medium " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Big and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is Big and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VeryBig and CarsWE is VerySmall " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VeryBig and CarsWE is Small " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VeryBig and CarsWE is Medium " +
                "then DeltaGreenTime is Same", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VeryBig and CarsWE is Big " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        ruleBlock.addRule(Rule.parse("if GreenTime is Big and CarsNS is VeryBig and CarsWE is VeryBig " +
                "then DeltaGreenTime is Decrease", fuzzyLogicEngine));
        fuzzyLogicEngine.addRuleBlock(ruleBlock);
    }

    private void addDeltaGreenTimeVariable() {
        OutputVariable DeltaGreenTime = new OutputVariable();
        DeltaGreenTime.setName("DeltaGreenTime");
        DeltaGreenTime.setDescription("DeltaGreenTime");
        DeltaGreenTime.setEnabled(true);
        DeltaGreenTime.setRange(-20.000, 20.000);
        DeltaGreenTime.setLockValueInRange(false);
        DeltaGreenTime.setAggregation(new Maximum());
        DeltaGreenTime.setDefuzzifier(new Centroid(100));
        DeltaGreenTime.setDefaultValue(Double.NaN);
        DeltaGreenTime.setLockPreviousValue(false);
        DeltaGreenTime.addTerm(new Gaussian("Decrease", -20.000, 5.997));
        DeltaGreenTime.addTerm(new Gaussian("Same", 0.000, 5.000));
        DeltaGreenTime.addTerm(new Gaussian("Increase", 20.000, 6.000));
        fuzzyLogicEngine.addOutputVariable(DeltaGreenTime);
    }

    private void addCarsWEVariable() {
        InputVariable CarsWE = new InputVariable();
        CarsWE.setName("CarsWE");
        CarsWE.setDescription("CarsWE");
        CarsWE.setEnabled(true);
        CarsWE.setRange(0.000, 90.000);
        CarsWE.setLockValueInRange(false);
        CarsWE.addTerm(new Trapezoid("VerySmall", 0.000, 0.000, 11.000, 18.000));
        CarsWE.addTerm(new Trapezoid("Small", 16.000, 20.000, 32.000, 36.000));
        CarsWE.addTerm(new Trapezoid("Medium", 34.000, 38.000, 52.000, 56.000));
        CarsWE.addTerm(new Trapezoid("Big", 54.000, 58.000, 72.000, 76.000));
        CarsWE.addTerm(new Trapezoid("VeryBig", 72.000, 78.000, 90.000, 90.000));
        fuzzyLogicEngine.addInputVariable(CarsWE);
    }

    private void addCarsNSVariable() {
        InputVariable CarsNS = new InputVariable();
        CarsNS.setName("CarsNS");
        CarsNS.setDescription("CarsNS");
        CarsNS.setEnabled(true);
        CarsNS.setRange(0.000, 90.000);
        CarsNS.setLockValueInRange(false);
        CarsNS.addTerm(new Trapezoid("VerySmall", 0.000, 0.000, 11.000, 18.000));
        CarsNS.addTerm(new Trapezoid("Small", 16.000, 20.000, 32.000, 36.000));
        CarsNS.addTerm(new Trapezoid("Medium", 34.000, 38.000, 52.000, 56.000));
        CarsNS.addTerm(new Trapezoid("Big", 54.000, 58.000, 72.000, 76.000));
        CarsNS.addTerm(new Trapezoid("VeryBig", 72.000, 78.000, 90.000, 90.000));
        fuzzyLogicEngine.addInputVariable(CarsNS);
    }

    private void addGreenTimeVariable() {
        InputVariable GreenTime = new InputVariable();
        GreenTime.setName("GreenTime");
        GreenTime.setDescription("");
        GreenTime.setEnabled(true);
        GreenTime.setRange(10.000, 50.000);
        GreenTime.setLockValueInRange(false);
        GreenTime.addTerm(new Trapezoid("Small", 10.000, 10.000, 20.000, 25.000));
        GreenTime.addTerm(new Trapezoid("Medium", 20.000, 25.000, 35.000, 40.000));
        GreenTime.addTerm(new Trapezoid("Big", 35.000, 40.000, 50.000, 50.000));
        fuzzyLogicEngine.addInputVariable(GreenTime);
    }
}
