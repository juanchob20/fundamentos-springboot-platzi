package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOtherBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MybeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyOtherBean myOtherBean;

	private MybeanWithProperties mybeanWithProperties;

	private UserPojo userPojo;

	@Autowired
	public FundamentosApplication(@Qualifier("componentTwoImpl") ComponentDependency componentDependency, MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency, MyOtherBean myOtherBean,
								  MybeanWithProperties mybeanWithProperties,UserPojo userPojo){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myOtherBean=myOtherBean;
		this.mybeanWithProperties=mybeanWithProperties;
		this.userPojo=userPojo;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myOtherBean.printNumbers();
		System.out.println(mybeanWithProperties.function());

		System.out.println(userPojo.getEmail()+""+userPojo.getPassword());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor: "+value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero "+e.getMessage());
		}

	}
}
