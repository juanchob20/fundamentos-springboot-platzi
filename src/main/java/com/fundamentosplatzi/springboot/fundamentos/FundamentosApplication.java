package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOtherBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MybeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.catalina.LifecycleState;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyOtherBean myOtherBean;

	private MybeanWithProperties mybeanWithProperties;

	private UserPojo userPojo;

	private UserRepository userRepository;

	@Autowired
	public FundamentosApplication(@Qualifier("componentTwoImpl") ComponentDependency componentDependency, MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency, MyOtherBean myOtherBean,
								  MybeanWithProperties mybeanWithProperties,UserPojo userPojo,
								  UserRepository userRepository){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myOtherBean=myOtherBean;
		this.mybeanWithProperties=mybeanWithProperties;
		this.userPojo=userPojo;
		this.userRepository=userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//metodosAnteriores();
		saveUsersInDataBase();
		getInformationJPQLFromUser();
	}

	private void getInformationJPQLFromUser(){
		/*
		LOGGER.info("Usuario con el metodo findByUserEmail "+
			userRepository.findByUserEmail("juan@gmail.com")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario"))
		);

		userRepository.findAndSort("jua", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort "+user));

		userRepository.findByName("juan")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method "+user));

		LOGGER.info("Usuario encontrado por nombre y email " +userRepository.findByEmailAndName("maria@gmail.com","maria")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%juan%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike "+user));

		userRepository.findByNameOrEmail("maria",null)
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail "+user));

		 */
		userRepository
				.findByBirthDateBetween(LocalDate.of(1995,3,1),LocalDate.of(2020,3,2))
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con intervalo de fechas "+ user));

		userRepository.findByNameContainingOrderByIdDesc("juan")
				.stream()
				.forEach(user -> LOGGER.info("Usuario por findByNameContainingOrderByIdDesc "+ user));

		LOGGER.info(
			"Usuario encontrado con getAllByBirthDateAndEmail "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2001,04,25),"pablo@gmail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario a partir del named parameter"))
		);
	}

	private void saveUsersInDataBase(){
		User user1 = new User("juan","juan@gmail.com", LocalDate.of(1992,01,21));
		User user2 = new User("juan jose","jose@gmail.com", LocalDate.of(1994,01,01));
		User user3 = new User("pedro","pedro@gmail.com", LocalDate.of(1996,03,30));
		User user4 = new User("maria","maria@gmail.com", LocalDate.of(1991,07,15));
		User user5 = new User("juan pablo","pablo@gmail.com", LocalDate.of(2001,04,25));
		User user6 = new User("lore","lore@gmail.com", LocalDate.of(2003,07,20));
		User user7 = new User("santi","santi@gmail.com", LocalDate.of(1999,03,12));
		User user8 = new User("aleja","aleja@gmail.com", LocalDate.of(1989,02,03));
		User user9 = new User("luisa","luisa@gmail.com", LocalDate.of(1992,10,05));
		User user10 = new User("diego","diego@gmail.com", LocalDate.of(1993,01,17));
		User user11 = new User("camilo","camilo@gmail.com", LocalDate.of(1993,04,17));
		User user12 = new User("daniel","daniel@gmail.com", LocalDate.of(1993,03,17));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);

	}


	private void metodosAnteriores(){
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
