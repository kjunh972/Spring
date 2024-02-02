package Hello.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // ./hello라고 웹페이로 들어오면 처리
    public String hello (Model model) {
        //매개변수로 전달된 Model 객체는 해당 메서드에서 생성한 데이터를 담아서 뷰로 전달할 수 있게 한다.
        //addAttribute 메서드는 컨트롤러에서 생성한 데이터를 뷰로 전달하는 역할이다.
        model.addAttribute("data","hello!!"); //키는 data, 값은 hello!!
        return "hello"; //templates/hello를 찾아라 라는 뜻.
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(value = "name") String name, Model model) {
        //@RequestParam에서 required값이 기본값이 true이다.required = true
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에 <head> 부분과 <body> 부분이 있는데 <body>에 return "hello "+name; 이 데이터를 직접 넣어주겠다는 뜻이다.
    //ResponseBody를 이용해 자바 객체를 HTTP 응답 본문의 객체 변환하여 클라이언트로 전송시킨다.
    //즉, 내가 어떤 상황으로 인해 url을 통해 뷰로 가는것이 아닌 String 자체를 반환하고 싶을때 쓰면 된다.
    public String helloString(@RequestParam("name") String name) {
        return "hello "+name; //name = spring이면 "hello spring"이라고 출력됨
    }

    @GetMapping("hello-api")
    @ResponseBody
    //위 방식은 json 방식이다. (key, value로 이루어져있다.)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        //정적으로 static으로 클래스를 만들면 HelloController이 클래스 안에서 HelloController.hello로 또 사용이 가능하다.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
