package com.hw.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hw.vo.User;

@Controller
public class UserController {

	@RequestMapping("/userlist.do")
	public String list(Model m) {
		//Database¿¡¼­ µ¥ÀÌÅÍ¸¦ °¡Áö°í ¿Â´Ù
		ArrayList<Object> list=null;
		list = new ArrayList<>();
		list.add(new User("id01","pwd01","ÀÌ¸»¼÷"));
		list.add(new User("id02","pwd02","±è¸»¼÷"));
		list.add(new User("id03","pwd03","½ºÆ¼ºì ¸»¼÷"));
		list.add(new User("id04","pwd04","½Ã¹Ù½º ¸»¼÷"));
		list.add(new User("id05","pwd05","Á¦ÀÓ½º ¸»¼÷"));
		m.addAttribute("userlist", list);
		m.addAttribute("center", "user/list"); // center´Â userlist·Î ¹Ù²¸¶ó
		return "main";
	}

	@RequestMapping("/useradd.do")
	public String add(Model m) {
		m.addAttribute("center", "user/add"); // center´Â userlist·Î ¹Ù²¸¶ó
		return "main";
	}
	@RequestMapping("/useraddimpl.do")
	public String addimpl(Model m,User u) {
		System.out.println("addimpl:" +m);
		m.addAttribute("center", "user/addok"); // center´Â userlist·Î ¹Ù²¸¶ó
		return "main";
	}

}



