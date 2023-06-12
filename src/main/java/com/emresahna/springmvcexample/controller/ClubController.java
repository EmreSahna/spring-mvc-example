package com.emresahna.springmvcexample.controller;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.model.Club;
import com.emresahna.springmvcexample.model.User;
import com.emresahna.springmvcexample.security.SecurityUtil;
import com.emresahna.springmvcexample.service.ClubService;
import com.emresahna.springmvcexample.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;
    private final UserService userService;

    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String getClubs(Model model){
        User user = new User();
        List<ClubDto> clubs = clubService.findAll();
        String username = SecurityUtil.getSession().getName();
        if(username != null){
            user = userService.findByUsername(SecurityUtil.getSession().getName());
        }
        model.addAttribute("user", user);
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
        ClubDto club = clubService.findClubDtoById(id);
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

    @GetMapping("/clubs/{id}")
    public String getClub(@PathVariable("id") Long id, Model model){
        User user = new User();
        ClubDto club = clubService.findClubDtoById(id);
        String username = SecurityUtil.getSession().getName();
        if(username != null){
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", club);
        return "clubs-view";
    }

    @GetMapping("/clubs/delete/{id}")
    public String deleteClub(@PathVariable("id") Long id){
        clubService.deleteClub(id);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam("query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}
