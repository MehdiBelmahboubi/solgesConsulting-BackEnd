package com.elmiraouy.jwtsecurity;

import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.entities.AppRole;
import com.elmiraouy.jwtsecurity.entities.Company;
import com.elmiraouy.jwtsecurity.entities.TypeUnitOrganisational;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.repository.AppRoleRepository;
import com.elmiraouy.jwtsecurity.repository.CompanyRepository;
import com.elmiraouy.jwtsecurity.repository.TypeUnitOrganisationalRepository;
import com.elmiraouy.jwtsecurity.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;


@SpringBootApplication
public class SolgeConsultingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolgeConsultingApplication.class, args);
	}
	//@Bean
	CommandLineRunner commandLineRunner(AppRoleRepository roleRepository){
		return args -> {
			AppRole role =AppRole.builder().roleName("client").build();
			roleRepository.save(role);
			role =AppRole.builder().roleName("manager").build();
			roleRepository.save(role);
			role =AppRole.builder().roleName("admin").build();
			roleRepository.save(role);
		};
	}
	//@Bean
	CommandLineRunner commandLineRunnerRoleUsers1(AppRoleRepository roleRepository, AppUserService appUserService){
		return args -> {
			Optional<AppRole> role1 =roleRepository.findByRoleName("Client");
			AppUserResponseDto byEmail = appUserService.findByEmail("miraouy@gmail.com");
			appUserService.addRoleToUser(role1.get().getId(), byEmail.getId());
			Optional<AppRole> role2 =roleRepository.findByRoleName("Admin");
			appUserService.addRoleToUser(role2.get().getId(), byEmail.getId());
			Optional<AppRole> role3 =roleRepository.findByRoleName("Manager");
			appUserService.addRoleToUser(role3.get().getId(), byEmail.getId());


		};
	}
	//@Bean
	CommandLineRunner commandLineRunnerTypeUnit(
			TypeUnitOrganisationalRepository typeUnitOrganisationalRepository,
			CompanyRepository companyRepository){
		return args -> {
			Company companyIdOne = companyRepository.findById(1L).orElseThrow(
					() -> new EntityNotFoundException("Company not found")
			);
		    TypeUnitOrganisational typeUnitOrganisationalOne=TypeUnitOrganisational
					.builder()
					.active(true)
					.name("Direction Générale")
					.code("DG")
					.level(1)
					.company(companyIdOne)
					.createDate(LocalDateTime.now())
					.build();
			typeUnitOrganisationalOne.setCompany(companyIdOne);
			TypeUnitOrganisational typeUnitOrganisationalTwo=TypeUnitOrganisational
					.builder()
					.active(true)
					.name("Direction")
					.code("D")
					.level(2)
					.company(companyIdOne)
					.createDate(LocalDateTime.now())
					.build();
			typeUnitOrganisationalTwo.setCompany(companyIdOne);
			TypeUnitOrganisational typeUnitOrganisationalThree=TypeUnitOrganisational
					.builder()
					.active(true)
					.name("Département")
					.code("DP")
					.level(3)
					.company(companyIdOne)
					.createDate(LocalDateTime.now())
					.build();
			typeUnitOrganisationalThree.setCompany(companyIdOne);
			TypeUnitOrganisational typeUnitOrganisationalFour=TypeUnitOrganisational
					.builder()
					.active(true)
					.name("Division")
					.code("DV")
					.level(4)
					.company(companyIdOne)
					.createDate(LocalDateTime.now())
					.build();
            typeUnitOrganisationalFour.setCompany(companyIdOne);
			typeUnitOrganisationalRepository.save(typeUnitOrganisationalOne);
			typeUnitOrganisationalRepository.save(typeUnitOrganisationalTwo);
			typeUnitOrganisationalRepository.save(typeUnitOrganisationalThree);
			typeUnitOrganisationalRepository.save(typeUnitOrganisationalFour);
			companyRepository.save(companyIdOne);
		};
	}

}
