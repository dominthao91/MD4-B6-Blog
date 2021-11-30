package thao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import thao.model.Blog;
import thao.service.IBlogService;

import java.util.List;

@Controller
@RequestMapping("blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public ModelAndView showList() {
        List<Blog> blogList = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blog", blogList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog created successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
            ModelAndView modelAndView = new ModelAndView("blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "blog updated successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDelete(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("blog/err-404");
            return modelAndView;
        }

    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute("blog")Blog blog){
        blogService.remove(blog.getId());
        return "redirect:/blogs";
    }

}