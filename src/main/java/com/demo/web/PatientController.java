package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.repo.PatientRepo;
import com.demo.entities.patient;


@Controller
public class PatientController {
    @Autowired
    private PatientRepo patientRepo;

    @GetMapping(path = "/Liste")
    public String Liste(
        Model model,
        @RequestParam(name="page",defaultValue = "0")int page,
        @RequestParam(name="size",defaultValue = "5")int size,
        @RequestParam(name="motCle", defaultValue = "") String motCle){
            Page<patient> patientsPage = patientRepo.findByNomContains(motCle,PageRequest.of(page,size));
            model.addAttribute("patients",patientsPage.getContent());
            model.addAttribute("pages",new int[patientsPage.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("size",size);
            model.addAttribute("motCle",motCle);
            return "Liste";
    }

    @GetMapping(path = "/DeletePatient")
    public String DeletePatient(Long id, String motCle, int page, int size){
        patientRepo.deleteById(id);
        System.out.println(id);
        return "redirect:/Liste?motCle="+motCle+"&page="+page+"&size="+size;
    }
    @GetMapping(path = "/AddPatient")
    public String AddPatient(Model model){
        model.addAttribute("patient",new patient());
        return "AddPatient";
    }

    @PostMapping(path = "/validerPatient")
    public String validerPatient(patient patient){
        patientRepo.save(patient);
        return "redirect:/Liste";
    }
    @GetMapping(path = "/UpdatePatient")
    public String UpdatePatient(Model model,Long id){
        patient pt=patientRepo.findById(id).get();
        model.addAttribute("patient",pt);
        return "AddPatient";
    }


}
