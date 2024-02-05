package awsstudy.ds.kr.springboot.web;

import awsstudy.ds.kr.springboot.config.auth.LoginUser;
import awsstudy.ds.kr.springboot.config.auth.dto.SessionUser;
import awsstudy.ds.kr.springboot.service.PostsService;
import awsstudy.ds.kr.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    //public String index(Model model){
    public String index(Model model, @LoginUser SessionUser user){
        //return "index";
        //var model = new org.springframework.ui.Model;
        //model.addAttribute("posts", postsService.findAllDesc());
        model.addAttribute("posts", postsService.findAllDesc());
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
