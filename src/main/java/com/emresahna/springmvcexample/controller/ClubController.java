package com.emresahna.springmvcexample.controller;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.model.Club;
import com.emresahna.springmvcexample.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String getClubs(Model model){
        List<ClubDto> clubs = clubService.findAll();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/add")
    public String addClubForm(Model model){
        model.addAttribute("club", new Club());
        return "clubs-create";
    }

    @PostMapping("/clubs/add")
    public String addClub(@Valid @ModelAttribute("club") ClubDto club,
                          BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club", club);
            return "clubs-create";
        }
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/edit/{id}")
    public String editClubForm(@PathVariable("id") Long id, Model model){
        ClubDto club = clubService.findById(id);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/edit/{id}")
    public String editClub(@PathVariable("id") Long id,
                           @Valid @ModelAttribute("club") ClubDto club,
                           BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club", club);
            return "clubs-edit";
        }

        club.setId(id);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }

}
