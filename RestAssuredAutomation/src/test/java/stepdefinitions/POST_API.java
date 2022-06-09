package stepdefinitions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import runner.Runner;
import utility.WriteToExcel;

public class POST_API {
	Logger log = LogManager.getLogger(Runner.class.getName());

	@Given("user takes the apiname PostApi and Body")
	public void user_takes_the_apiname_post_api_and_body(DataTable dataTable) throws IOException {
		log.info(" <------------------------------ POST Request API Details ------------------------------>");
		WriteToExcel ex = new WriteToExcel();

		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			Response response = null;

			try {
				response = RestAssured.given().contentType(ContentType.JSON)
						.header("X-ART-API-KEY", "koPKTXm5v9PPSGHpuqifHeaTh2b8mAA7").body(mapdata.get("Body"))
						.post(mapdata.get("baseURI"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			int statuscode = response.getStatusCode();
			
			long responsetime = response.getTime();
			String sec = ((float) responsetime) / 1000 + " s ";

			Date date = new Date();
			String current_date = new SimpleDateFormat("yyyy-MM-dd").format(date);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String current_time = sdf.format(cal.getTime());

			ex.writetoexcel(mapdata.get("name"), mapdata.get("baseURI"), statuscode, sec, current_date, current_time);
			
			log.info("API_Name : " + mapdata.get("name") + " , " + " Endpoint URI : " + mapdata.get("baseURI") + " , "
					+ " Status Code : " + statuscode + " , " + " Response Time : " + sec);
		}
	}

}
