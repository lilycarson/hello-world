package Tactio;

import java.util.ArrayList;

public class Patient {
	//attributes of a patient
	private String id;
	private double meanBmi;
	private double meanGlucose;
	private double sdGlucose;
	private double meanGlucose_Post_Meal;
	private double sdGlucose_Post_Meal;
	private double meanGlucose_Bedtime;
	private double sdGlucose_Bedtime;
	private double meanA1C;
	private double sdA1C;
	private double meanSystolic;
	private double sdSystolic;
	private double meanDiastolic;
	private double sdDiastolic;
	private double meanPulse_Rate;
	private double sdPulse_Rate;
	private double meanWhiteBloodCount;
	private double sdWhiteBloodCount;
	private double meanTriglyceride;
	private double sdTriglyceride;
	private double meanHdl;
	private double sdHdl;
	private double meanLdl;
	private double sdLdl;


	//patient constructor
	public Patient(String id, double meanBmi, double meanGlucose, double sdGlucose, double meanGlucose_Post_Meal, double sdGlucose_Post_Meal, double meanGlucose_Bedtime, double sdGlucose_Bedtime, double meanA1C, double sdA1C, double meanSystolic, double sdSystolic, double meanDiastolic, double sdDiastolic, double meanPulse_Rate, double sdPulse_Rate, double meanWhiteBloodCount, double sdWhiteBloodCount, double meanTriglyceride, double sdTriglyceride, double meanHdl, double sdHdl, double meanLdl, double sdLdl) {
		this.id = id;
		this.meanBmi = meanBmi;
		this.meanGlucose = meanGlucose;
		this.sdGlucose = sdGlucose;
		this.meanGlucose_Post_Meal = meanGlucose_Post_Meal;
		this.sdGlucose_Post_Meal = sdGlucose_Post_Meal;
		this.meanGlucose_Bedtime = meanGlucose_Bedtime;
		this.sdGlucose_Bedtime = sdGlucose_Bedtime;
		this.meanA1C = meanA1C;
		this.sdA1C = sdA1C;
		this.meanSystolic = meanSystolic;
		this.sdSystolic = sdSystolic;
		this.meanDiastolic = meanDiastolic;
		this.sdDiastolic = sdDiastolic;
		this.meanPulse_Rate = meanPulse_Rate;
		this.sdPulse_Rate = sdPulse_Rate;
		this.meanWhiteBloodCount = meanWhiteBloodCount;
		this.sdWhiteBloodCount = sdWhiteBloodCount;
		this.meanTriglyceride = meanTriglyceride;
		this.sdTriglyceride = sdTriglyceride;
		this.meanHdl = meanHdl;
		this.sdHdl = sdHdl;
		this.meanLdl = meanLdl;
		this.sdLdl = sdLdl;	
	}

	public String getId() {
		return this.id;
	}

	static ArrayList<String> infection = new ArrayList<String>();
	static ArrayList<String> obese = new ArrayList<String>();
	static ArrayList<String> diaHyper = new ArrayList<String>();
	static ArrayList<String> dys = new ArrayList<String>();


	//check for infection
	public void hasInfection() {
		if((this.meanWhiteBloodCount >= 5500)&&(this.sdWhiteBloodCount >= 1000)) {
			infection.add(this.id);

		}
	}


	//check for obesity
	public void hasObesity() {
		if(this.meanBmi >= 30) {
			obese.add(this.id);

		}
	}

	//check for diabetes and hypertension
	public void hasDiabetesAndHyper() {
		if ((this.meanGlucose >= 5.5) && (this.sdGlucose >= 0.6) && (this.meanGlucose_Post_Meal >= 6.9) && (this.sdGlucose_Post_Meal >= 0.9) && (this.meanGlucose_Bedtime >= 7.2) && (this.sdGlucose_Bedtime >= 1) && (this.meanA1C >= 6) && (this.sdA1C >= 0.4) && (this.meanSystolic >= 175) && (this.sdSystolic >= 10) && (this.meanDiastolic >= 115) && (this.sdDiastolic >= 10) && (this.meanPulse_Rate >= 70) && (this.sdPulse_Rate >= 10)) {
			diaHyper.add(this.id);

		}
	}

	//check for dyslipidemia
	public void hasDys() {
		if ((this.meanTriglyceride >= 1.13) && (this.sdTriglyceride >= 0.1) && (this.meanHdl >= 1.6) && (this.sdHdl >= 0.15) && (this.meanLdl >= 3.1) && (this.sdLdl >= 0.3)) {
			dys.add(this.id);

		}

	}

	public String toString() {

		return (" id: " + id  + " meanBmi: "+ meanBmi +" meanGlucose: "+ meanGlucose +
				" sdGlucose: " + sdGlucose+ " meanGlucose_Post_Meal: " + meanGlucose_Post_Meal+ " sdGlucose_Post_Meal:"
				+ sdGlucose_Post_Meal + " meanGlucose_Bedtime: " + meanGlucose_Bedtime+ " sdGlucose_Bedtime: " 
				+ sdGlucose_Bedtime+ " meanA1C: "+ meanA1C + " sdA1C: " + sdA1C+ " meanSystolic: " + meanSystolic +
				" sdSystolic: "+ sdSystolic+ " meanDiastolic: "+ meanDiastolic+" sdDiastolic: " + sdDiastolic
				+" meanPulse_Rate: " + meanPulse_Rate+ " sdPulse_Rate: "+ sdPulse_Rate+ " meanWhiteBloodCount: "
				+ meanWhiteBloodCount+ " sdWhiteBloodCount" + sdWhiteBloodCount+ " meanTriglyceride: "+ meanTriglyceride
				+" sdTriglyceride: " + sdTriglyceride+ " meanHdl: "+ meanHdl+ " sdHdl: "+ sdHdl+ " meanLdl: "+ meanLdl
				+" sdLdl: " + sdLdl + "\n");


	}
}