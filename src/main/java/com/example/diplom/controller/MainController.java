package com.example.diplom.controller;


import com.example.diplom.domain.Cargo;
import com.example.diplom.domain.User;
import com.example.diplom.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Cargo> cargos = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            cargos = messageRepo.findByConsigneeContains(filter);
        } else {
            cargos = messageRepo.findAll();
        }

        model.addAttribute("cargos", cargos);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String shipper,
            @RequestParam(required = false) String consignee, Map<String, Object> model,
            @RequestParam(required = false) String consignment_note,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) Integer number_of_packages,
            @RequestParam(required = false)  String name,
            @RequestParam(required = false)  Double price,
            @RequestParam(required = false)  Double hs_code,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String truck_plate,

            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(required = false) Integer id
    ) throws IOException {
        if(id != null){
            messageRepo.deleteById(id);
        }
        else {
            LocalDateTime parse = LocalDateTime.parse(date);
            Cargo cargo = new Cargo(text,  consignee,  shipper,  consignment_note,  weight,  number_of_packages,  name,
                    price, hs_code,  parse,  truck_plate,  user);


            if (file != null && !file.getOriginalFilename().isEmpty()) {
                cargo.setFilename(file.getOriginalFilename());
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));


            }

            messageRepo.save(cargo);





        }
        Iterable<Cargo> cargos = messageRepo.findAll();

        model.put("cargos", cargos);
        return "main";


    }

}