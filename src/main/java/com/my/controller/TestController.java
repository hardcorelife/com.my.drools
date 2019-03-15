package com.my.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.my.model.*;
import org.kie.api.KieBase;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.myfilter.AddressFilter;

@RequestMapping("/test")
@Controller
public class TestController {

	@Resource
	private KieSession kieSession;
	
	@Resource
	private KieBase kieBase;

	@ResponseBody
	@RequestMapping("/address")
	public void test() {
		Address address = new Address();
		address.setPostcode("99425");

		AddressCheckResult result = new AddressCheckResult();
		FactHandle f = kieSession.insert(address);
		FactHandle f1 = kieSession.insert(result);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		if (result.isPostCodeResult()) {
			System.out.println("规则校验通过");
		}
		kieSession.delete(f);
		kieSession.delete(f1);
		// 这里添加过滤器
		Address address1 = new Address();
		address1.setPostcode("2017");
		AddressCheckResult result1 = new AddressCheckResult();
		FactHandle f2 = kieSession.insert(address1);
		FactHandle f3 = kieSession.insert(result1);
		AddressFilter addressFilter = new AddressFilter(address1);
		int ruleFiredCount1 = kieSession.fireAllRules(addressFilter);
		System.out.println("触发了" + ruleFiredCount1 + "条规则");
		kieSession.delete(f2);
		kieSession.delete(f3);
	}

	@ResponseBody
	@RequestMapping("/address1")
	public void test1() {
		Address address = new Address();
		address.setPostcode("99425");
		List<String> list = new ArrayList<String>();
		AddressCheckResult result = new AddressCheckResult();
		FactHandle f = kieSession.insert(address);
		FactHandle f1 = kieSession.insert(result);
		kieSession.setGlobal("myGlobalList", list);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		if (result.isPostCodeResult()) {
			System.out.println("规则校验通过");
		}
		System.out.println(list.toString());
		kieSession.delete(f);
		kieSession.delete(f1);

	}
	
	@ResponseBody
	@RequestMapping("/rule")
	public void rule() {
		Address address = new Address();
		address.setPostcode("99425");
		FactHandle f = kieSession.insert(address);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);

	}
	
	@ResponseBody
	@RequestMapping("/function")
	public void function() {
		Address address = new Address();
		address.setPostcode("function");
		FactHandle f = kieSession.insert(address);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);

	}

	@ResponseBody
	@RequestMapping("/person")
	public Person person() {
		Person zhouxx = new Person("张三",30);
		FactHandle f = kieSession.insert(zhouxx);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);
		return zhouxx;
	}

	@ResponseBody
	@RequestMapping("/discount")
	public Order discount() {
		Order order = new Order();
		List<Product> products = new ArrayList<>();
		products.add(new Product("MP3", 120, 2));
		products.add(new Product("MP4", 200));
		products.add(new Product(1,"TCL电视", 3000 , 3));
		products.add(new Product("红米note2", 799, 5));

		order.setProducts(products);

		FactHandle f = kieSession.insert(order);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);
		System.out.println("实际需要支付：" + order.getPay());
		return order;

	}

	@ResponseBody
	@RequestMapping("/point")
	public PointDomain point() {

		final PointDomain pointDomain = new PointDomain();
		pointDomain.setUserName("hello kity");
		pointDomain.setBackMondy(100d);
		pointDomain.setBuyMoney(500d);
		pointDomain.setBackNums(1);
		pointDomain.setBuyNums(5);
		pointDomain.setBillThisMonth(5);
		pointDomain.setBirthDay(true);
		pointDomain.setPoint(0l);

		FactHandle f = kieSession.insert(pointDomain);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		System.out.println("执行完毕BillThisMonth："+pointDomain.getBillThisMonth());
		System.out.println("执行完毕BuyMoney："+pointDomain.getBuyMoney());
		System.out.println("执行完毕BuyNums："+pointDomain.getBuyNums());
		System.out.println("执行完毕规则引擎决定发送积分："+pointDomain.getPoint());
		kieSession.delete(f);
		return pointDomain;

	}

	@ResponseBody
	@RequestMapping("/score")
	public ScoreRule score() {
		//编写fact对象
		ScoreRule score=new ScoreRule();
		score.setScore(75);
		FactHandle f = kieSession.insert(score);
//		kieSession.startProcess("rules");
		kieSession.getAgenda().getRuleFlowGroup( "score" );
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);
		return score;
	}

	@ResponseBody
	@RequestMapping("/contain")
	public Person contain() {
		School school=new School();
		school.setCount(50);
		school.setName("一班");
		Person zhouxx = new Person("一班",30);
//		zhouxx.setSchool(school);
		FactHandle f = kieSession.insert(zhouxx);
		FactHandle f2 =kieSession.insert(school);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);
		kieSession.delete(f2);
		return zhouxx;
	}

	@ResponseBody
	@RequestMapping("/enum")
	public void enums() {
		Address address = new Address();
		address.setPostcode("Monday");
		FactHandle f = kieSession.insert(address);
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);

	}
	
	@ResponseBody
	@RequestMapping("/declarePerson")
	public void declarePerson() {
		// 在rule中声明的类，到Java 中使用。
		// get a reference to a knowledge base with a declared type:
		// get the declared FactType
		FactType personType = kieBase.getFactType( "rules","Person1" );
		// handle the type as necessary:
		// create instances:
		Object bob = new Object();
		try {
			bob = personType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("person没有实例话成功！");
		}

		// set attributes values
		personType.set( bob,"name","Bob" );
		personType.set( bob, "age",42 );

		// insert fact into a session
		FactHandle f = kieSession.insert( bob );
		int ruleFiredCount = kieSession.fireAllRules();

		// read attributes
		String name = (String) personType.get( bob, "name" );
		int age = (int) personType.get( bob, "age" );
		
		// 在规则文件中创建了address对象在Java中能直接获得吗？
		System.out.println(name);
		System.out.println("触发了" + ruleFiredCount + "条规则");
		kieSession.delete(f);

	}
	
	@ResponseBody
	@RequestMapping("/metadata")
	public void metadata() {
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");

	}
	@ResponseBody
	@RequestMapping("/hello")
	public void hello() {
		int ruleFiredCount = kieSession.fireAllRules();
		System.out.println("触发了" + ruleFiredCount + "条规则");

	}
}