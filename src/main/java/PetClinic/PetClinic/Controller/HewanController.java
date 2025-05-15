package PetClinic.PetClinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import PetClinic.PetClinic.Model.Hewan;
import PetClinic.PetClinic.Repository.HewanRepository;

@Controller
@RequestMapping("/hewan")
public class HewanController {

    @Autowired
    private HewanRepository hewanRepo;

    @GetMapping
    public String listHewan(Model model) {
        model.addAttribute("hewanList", hewanRepo.findAll());
        model.addAttribute("hewan", new Hewan());
        return "hewan/index";
    }

    @PostMapping("/save")
    public String saveHewan(@ModelAttribute Hewan hewan) {
        hewanRepo.save(hewan);
        return "redirect:/hewan";
    }

    @GetMapping("/delete/{id}")
    public String deleteHewan(@PathVariable Long id) {
        hewanRepo.deleteById(id);
        return "redirect:/hewan";
    }

    @GetMapping("/edit/{id}")
    public String editHewan(@PathVariable("id") Long id, Model model) {
        Hewan hewan = hewanRepo.findById(id).orElse(null);
            if (hewan != null) {
            model.addAttribute("hewan", hewan);
            model.addAttribute("hewanList", hewanRepo.findAll());
            return "hewan/index";
    } else {
        return "redirect:/hewan";
    }
}
}
