package Tactio;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hack_doctors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class Hack_doctors {

	private static String TOKEN ;

	//get last page
	public static int getLastPage(JsonObject rootObject) {

		JsonArray link = rootObject.get("link").getAsJsonArray();
		JsonObject lastPageJsonObject= link.get(2).getAsJsonObject();
		String lastPageUrl =lastPageJsonObject.get("url").getAsString();
		String lastPage = lastPageUrl.substring(lastPageUrl.indexOf("page=")+5);
		int lstPage = Integer.parseInt(lastPage);
		return lstPage;



	}
	public static String getPatients() {

		String id = "";
		try {

			String endpoint = "https://sandbox86.tactiorpm7000.com/tactio-clinician-api/1.1.4/patient";
			String params = "";
			String jsonOutput = endpointToJson(endpoint, params);

			JsonElement jelement = new JsonParser().parse(jsonOutput);
			JsonObject rootObject = jelement.getAsJsonObject();

			//get last page
			int lstPage =getLastPage( rootObject);


			JsonArray entry = rootObject.get("entry").getAsJsonArray();

			for(int page=1;page<lstPage;page++) {
				for (int i = 0;  i<entry.size(); i++) {

					id = entry.get(i).getAsJsonObject().get("resource").getAsJsonObject().get("id").getAsString();

					observation(id);
				}
			}

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void observation(String id) {


		try {

			String endpoint = "https://sandbox86.tactiorpm7000.com/tactio-clinician-api/1.1.4/observation";
			String params = "subject="+id;



			ArrayList<Double> gluc = new ArrayList<Double>();
			ArrayList<Double> bmi = new ArrayList<Double>();
			ArrayList<Double> gluc_post = new ArrayList<Double>();
			ArrayList<Double> gluc_BT = new ArrayList<Double>();
			ArrayList<Double> tgr = new ArrayList<Double>();
			ArrayList<Double> hdl = new ArrayList<Double>();
			ArrayList<Double> ldl = new ArrayList<Double>();
			ArrayList<Double> wbc = new ArrayList<Double>();
			ArrayList<Double> sys = new ArrayList<Double>();
			ArrayList<Double> dias = new ArrayList<Double>();
			ArrayList<Double> pr = new ArrayList<Double>();
			ArrayList<Double> A1C = new ArrayList<Double>();

			params = "subject="+id;

			String jsonOutput = endpointToJson(endpoint, params);
			JsonElement jelement = new JsonParser().parse(jsonOutput);
			JsonObject rootObject = jelement.getAsJsonObject();
			JsonArray entry = rootObject.get("entry").getAsJsonArray();


			int lstPage =getLastPage( rootObject);

			for (int page = 1;  page<lstPage; page++) {

				params = "subject="+id+"&count=5";
				jsonOutput = endpointToJson(endpoint, params);
				jelement = new JsonParser().parse(jsonOutput);
				rootObject = jelement.getAsJsonObject();

				entry = rootObject.get("entry").getAsJsonArray();

				for (int i = 0;  i<entry.size(); i++) {

					String field_gluc =getValue("glucose",  entry, i);
					String field_bmi =getValue("bmi",  entry, i);
					String field_glucose_post_meal =getValue("glucose_post_meal",  entry, i);
					String field_glucose_bedtime =getValue("glucose_bedtime",  entry, i);
					String field_triglyceride =getValue("triglyceride",  entry, i);
					String field_hdl =getValue("hdl",  entry, i);
					String field_ldl =getValue("ldl",  entry, i);
					String field_whiteBloodCount =getValue("whiteBloodCount",  entry, i);
					String field_systolic =getValue("systolic",  entry, i);
					String field_diastolic =getValue("diastolic",  entry, i);
					String field_pulse_rate =getValue("pulse_rate",  entry, i);
					String field_A1C =getValue("A1C",  entry, i);


					//glucose
					if(!field_gluc.equals("")) {
						gluc.add(Double.parseDouble(field_gluc));

					} 

					else if(!field_bmi.equals("")) {

						//bmi
						bmi.add(Double.parseDouble(field_bmi));


					} else if(!field_glucose_post_meal.equals("")) {


						//glucose_post_meal
						gluc_post.add(Double.parseDouble(field_glucose_post_meal));

					}
					else if (!field_glucose_bedtime.equals("")) {
						//gluc_bedtime
						gluc_BT.add(Double.parseDouble(field_glucose_bedtime));

					}
					else if (!field_triglyceride.equals("")) {

						//triglyceride
						tgr.add(Double.parseDouble(field_triglyceride));
					}
					else if (!field_hdl.equals("")) {
						//hdl
						hdl.add(Double.parseDouble(field_hdl));

					}
					//ldl
					else if(!field_ldl.equals("")) {
						ldl.add(Double.parseDouble(field_ldl));

					}
					else if(!field_whiteBloodCount.equals("")) {
						//whiteBloodCount
						wbc.add(Double.parseDouble(field_whiteBloodCount));
					}
					else if(!field_systolic.equals("")) {


						//systolic
						sys.add(Double.parseDouble(field_systolic));

					}
					else if(!field_diastolic.equals("")) {
						//diastolic
						dias.add(Double.parseDouble(field_diastolic));

					}
					else if(!field_pulse_rate.equals("")) {
						//pulse_rate
						pr.add(Double.parseDouble(field_pulse_rate));

					} else if(!field_A1C.equals("")) {
						//A1C
						A1C.add(Double.parseDouble(field_A1C));

					}

				}
			}

			//mean
			double mean_gluc= mean_stDev(gluc)[0];
			double mean_bmi=  mean_stDev(bmi)[0];
			double mean_gluc_post= mean_stDev(gluc_post)[0];
			double mean_gluc_BT= mean_stDev(gluc_BT)[0];
			double mean_tgr=  mean_stDev(tgr)[0];
			double mean_hdl= mean_stDev(hdl)[0];
			double mean_ldl= mean_stDev(ldl)[0];
			double mean_wbc= mean_stDev(wbc)[0];
			double mean_sys= mean_stDev(sys)[0];
			double mean_dias= mean_stDev(dias)[0];
			double mean_pr= mean_stDev(pr)[0];
			double mean_A1C= mean_stDev(A1C)[0];

			//standard_dev
			double stDev_gluc= mean_stDev(gluc)[1];
			double stDev_gluc_post= mean_stDev(gluc_post)[1];
			double stDev_gluc_BT= mean_stDev(gluc_BT)[1];
			double stDev_tgr=  mean_stDev(tgr)[1];
			double stDev_hdl= mean_stDev(hdl)[1];
			double stDev_ldl= mean_stDev(ldl)[1];
			double stDev_wbc= mean_stDev(wbc)[1];
			double stDev_sys= mean_stDev(sys)[1];
			double stDev_dias= mean_stDev(dias)[1];
			double stDev_pr= mean_stDev(pr)[1];
			double stDev_A1C= mean_stDev(A1C)[1];



			Patient p = new Patient(id, mean_bmi, mean_gluc, stDev_gluc, mean_gluc_post, stDev_gluc_post, mean_gluc_BT, stDev_gluc_BT,  mean_A1C, stDev_A1C,
					mean_sys, stDev_sys, mean_dias, stDev_dias, mean_pr, stDev_pr, mean_wbc,  stDev_wbc, mean_tgr, stDev_tgr, mean_hdl, stDev_hdl, mean_ldl, 
					stDev_ldl);


			p.hasInfection();
			p.hasObesity();
			p.hasDiabetesAndHyper();
			p.hasDys();


		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	//create field string with jsonObject
	public static String getValue(String input, JsonArray entry, int index) {
		String value= "";
		JsonObject x = (JsonObject) entry.get(index).getAsJsonObject();
		JsonObject resource = (JsonObject) x.get("resource").getAsJsonObject();
		JsonObject code = (JsonObject) resource.get("code").getAsJsonObject();
		String text =code.get("text").getAsString();

		if(text.equals(input)) {             
			JsonObject vQ = (JsonObject) resource.get("valueQuantity").getAsJsonObject();

			value= vQ.get("value").getAsString();
			return value;


		} 


		return value;
	}


	//method to calculate the mean and standard deviation
	public static double [] mean_stDev(ArrayList<Double> val) {

		double average=0.0;
		for(int i=0;i<val.size();i++) {

			average += val.get(i);



		}
		average/=val.size();

		double stDev=0.0;

		for(int i=0;i<val.size();i++) {

			stDev += (Math.pow((val.get(i)-average), 2.0));



		}
		stDev/=val.size();
		stDev = Math.sqrt(stDev);

		return new double [] {average, stDev};


	}





	private static String endpointToJson(String endpoint, String params) {
		params = params.replace(' ', '+');

		try {
			String fullURL = endpoint;
			if (params.isEmpty() == false) {
				fullURL += "?" + params;
			}

			URL requestURL = new URL(fullURL);

			HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
			String bearerAuth = "Bearer " + TOKEN;
			connection.setRequestProperty("Authorization", bearerAuth);
			connection.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inputLine;
			String jsonOutput = "";
			while ((inputLine = in.readLine()) != null) {
				jsonOutput += inputLine;
			}
			in.close();

			return jsonOutput;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private static void accessToken() {
		String token = "";
		try {

			String url = "https://sandbox86.tactiorpm7000.com/token.php";
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);

			// add header
			post.setHeader("Content-Type", "application/json");

			JSONObject rootObject = new JSONObject();
			rootObject.put("grant_type", "password");
			rootObject.put("client_id", "083e9a44a763473fbeb62fbf90b74551");
			rootObject.put("client_secret", "ba09798f0921456e8b4e5e4588ea536d");
			rootObject.put("username", "tactioClinician");
			rootObject.put("password", "tactio");

			StringEntity entity = new StringEntity(rootObject.toString(), "UTF8");

			post.setEntity(entity);

			HttpResponse response = client.execute(post);

			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			JsonElement jelement = new JsonParser().parse(result.toString());
			JsonObject obj = jelement.getAsJsonObject();
			token = obj.get("access_token").getAsString();

			TOKEN = token;

		} catch (Exception e) {
			System.out.println("Something wrong here... make sure you set your Client ID and Client Secret properly!");
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {


		accessToken();
		getPatients();
		System.out.println(Patient.infection);
		System.out.println(Patient.obese);
		System.out.println(Patient.diaHyper);
		System.out.println(Patient.dys);

	}
}
