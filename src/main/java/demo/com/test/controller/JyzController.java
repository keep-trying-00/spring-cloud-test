package demo.com.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController
public class JyzController {
	
	@Value("${srpring.myproperties.value}")
	private String value ;
	
	@RequestMapping("/xx")
	public String xxoox(){
		System.out.println(value);
		return value;
	}
	
	@RequestMapping("/login")
	public String xx(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("xxxxxxxx");
		return "login";
	}
	@RequestMapping("/gsbank")
	public String xxs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("aaaaaaaaaa");
		return "signtran";
	}
	
}
