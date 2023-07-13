package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member=new Member();

        member.setName(form.getName());
        member.setPassword(form.getPassword());
        member.setNickname(form.getNickname());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

    @GetMapping("/members/choose")
    public String chooseform(){
        return "members/chooseMemberForm";
    }

    @PostMapping("/members/choose")
    public String choose(Model model, MemberForm form){

        Member members;
        Optional<Member> member = memberService.findName(form.getName());

        try{
            members=member.get();
        } catch (Exception e) {
            return "redirect:/";
        }
        model.addAttribute("members", members);

        return "members/memberOne";

    }

    @GetMapping("/members/login")
    public String loginform(){return "members/login";}

    @PostMapping("/members/login")
    public String login(Model model, MemberForm form){

        Member members;
        String nick;
        Optional<Member> member = memberService.login(form.getName(), form.getPassword());

        try{
            members=member.get();
            nick=members.getNickname();
        } catch (Exception e) {
            model.addAttribute("error", "다시 시도해주세요");
            return "members/login";
        }
        model.addAttribute("nicks", nick);
        return "home";

    }


}
