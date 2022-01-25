package com.example.covid_tracker;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String root(Model model) throws IOException {
        model.addAttribute("test1", "Witaj użytkowniku!");

        String csvFile = "src/main/resources/static/01-24-2022.csv";
        CSVReader reader = null;
        try{
            reader = new CSVReader(new FileReader(csvFile));

            String[] line;
            int i = 0;
            while ((line = reader.readNext()) != null) {
                if (i == 0){
                    i++;
                    continue;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Corona corona = new Corona();

                corona.setLastUpdate(LocalDateTime.parse(line[4],formatter));
                corona.setConfirmed(Long.valueOf(line[7]));
                corona.setRecovered(Long.valueOf(line[9]));
                corona.setActive(Long.valueOf(line[10]));
                corona.setCombinedKey(line[11]);

                log.info(corona.toString());
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


        return "mainTemplate";
    }
}
