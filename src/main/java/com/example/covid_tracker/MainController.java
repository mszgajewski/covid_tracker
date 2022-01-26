package com.example.covid_tracker;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
public class MainController {

    @Autowired
    CoronaRepository coronaRepository;

    @GetMapping("/")
    public String root(Model model) throws NumberFormatException, IOException {
        model.addAttribute("test1", "Witaj użytkowniku!");

        URL url = new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/01-25-2022.csv");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int response = huc.getResponseCode();

        if(response == 200) {
            log.info("Successfuly conected");

            CSVReader reader = null;
            try{

                BufferedReader input = new BufferedReader((new InputStreamReader(huc.getInputStream())));
                reader = new CSVReader(input);

                String[] line;
                int i = 0;
                while ((line = reader.readNext()) != null) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    Corona corona = new Corona();

                    corona.setLastUpdate(LocalDateTime.parse(line[4],formatter));
                    corona.setConfirmed(Long.valueOf(line[7]));
                    corona.setRecovered(Long.valueOf(line[9]));
                    corona.setActive(Long.valueOf(line[10]));
                    corona.setCombinedKey(line[11]);

                    log.info(corona.toString());
                    coronaRepository.save(corona);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        String csvFile = "src/main/resources/corona.csv";

        return "mainTemplate";
    }
}
