package com.hw.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hw.vo.Item;
import com.hw.vo.User;

@Controller
public class ItemController {

	@RequestMapping("/itemlist.do")
	public String list(Model m) {
		//Database에서 데이터를 가지고 온다
		ArrayList<Object> list=null;
		list = new ArrayList<>();
		list.add(new Item(01,"부가티",120000));
		list.add(new Item(02,"R8",18000));
		list.add(new Item(03,"재규어",15000));
		list.add(new Item(04,"한보루기니",30000));
		list.add(new Item(05,"BMW",13000));
		m.addAttribute("itemlist", list);
		m.addAttribute("center","item/list");
		return "main";
	}

	@RequestMapping("/itemadd.do")
	public String add(Model m) {
		m.addAttribute("center", "item/add"); // center는 userlist로 바껴라
		return "main";
	}
	@RequestMapping("/itemaddimpl.do")
	public String addimpl(Model m,User u) {
		System.out.println("addimpl:" +m);
		m.addAttribute("center", "item/addok"); // center는 userlist로 바껴라
		return "main";
	}

}



