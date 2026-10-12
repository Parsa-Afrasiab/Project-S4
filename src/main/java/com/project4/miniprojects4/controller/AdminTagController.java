package com.project4.miniprojects4.controller;

import com.project4.miniprojects4.model.Tag;
import com.project4.miniprojects4.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tags")
public class AdminTagController {

    private final TagService tagService;

    public AdminTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public String manageTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "manage-tags";
    }

    @PostMapping("/save")
    public String saveTag(@RequestParam String name) {
        Tag tag = new Tag(name);
        tagService.save(tag);

        return "redirect:/admin/tags";
    }

    @PostMapping("/delete/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);

        return "redirect:/admin/tags";
    }
}
