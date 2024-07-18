package com.elmiraouy.jwtsecurity;

import com.elmiraouy.jwtsecurity.Dto.response.AppUserResponseDto;
import com.elmiraouy.jwtsecurity.entities.*;
import com.elmiraouy.jwtsecurity.handlerException.EntityNotFoundException;
import com.elmiraouy.jwtsecurity.repository.*;
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
			AppRole role =AppRole.builder().roleName("Client").build();
			roleRepository.save(role);
			role =AppRole.builder().roleName("Manager").build();
			roleRepository.save(role);
			role =AppRole.builder().roleName("Admin").build();
			roleRepository.save(role);
		};
	}
	//@Bean
	CommandLineRunner commandLineRunner1(ContractTypeRepository contractTypeRepository){
		return args -> {
			ContractType contractType=ContractType.builder().code("CDI").description("Contrat à Durée Indéterminée : Le contrat n'a pas de date de fin prédéterminée et offre une stabilité d'emploi.").build();
			contractTypeRepository.save(contractType);
			contractType=ContractType.builder().code("CDD").description("Contrat à Durée Déterminée : Le contrat a une date de fin précise, souvent utilisé pour des missions temporaires ou des projets spécifiques.").build();
			contractTypeRepository.save(contractType);
			contractType=ContractType.builder().code("Intérim").description("Contrat de travail temporaire  : Le salarié est mis à disposition par une agence de travail temporaire pour une mission chez un client, similaire à l'intérim.").build();
			contractTypeRepository.save(contractType);
			contractType=ContractType.builder().code("Stage").description("Contrat de stage : Souvent utilisé pour les étudiants ou jeunes diplômés pour acquérir de l'expérience professionnelle.").build();
			contractTypeRepository.save(contractType);
		};
	}
	//@Bean
	public CommandLineRunner testAddCountry(CountryRepository countryRepository) {
		return args -> {
			Country country = Country.builder().codeAlpha1("USA").codeAlpha2("US").name("United States").capital("Washington, D.C.").country("United States").nationality("American").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("MA").codeAlpha2("MAR").name("Maroc").capital("RABAT").country("Maroc").nationality("Marocaine").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AF").codeAlpha2("AFG").name("Afghanistan").capital("Kaboul").country("Afghanistan").nationality("Afghane").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("ZA").codeAlpha2("ZAF").name("Afrique du Sud").capital("Pretoria").country("Afrique").nationality("Sud-africaine").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AL").codeAlpha2("ALB").name("Albanie").capital("Tirana").country("Europe").nationality("Albanaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("DZ").codeAlpha2("DZA").name("Algérie").capital("Alger").country("Afrique").nationality("Algérienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("DE").codeAlpha2("DEU").name("Allemagne").capital("Berlin").country("Europe").nationality("Allemande").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AD").codeAlpha2("AND").name("Andorre").capital("Andorre-la-Vieille").country("Europe").nationality("Andorrane").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AO").codeAlpha2("AGO").name("Angola").capital("Luanda").country("Afrique").nationality("Angolaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AG").codeAlpha2("ATG").name("Antigua-et-Barbuda").capital("Saint John's").country("Amérique du Nord").nationality("Antiguaise et barbudienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("SA").codeAlpha2("SAU").name("Arabie saoudite").capital("Riyad").country("Asie").nationality("Saoudienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AR").codeAlpha2("ARG").name("Argentine").capital("Buenos Aires").country("Amérique du Sud").nationality("Argentine").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AM").codeAlpha2("ARM").name("Arménie").capital("Erevan").country("Asie").nationality("Arménienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AU").codeAlpha2("AUS").name("Australie").capital("Canberra").country("Océanie").nationality("Australienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AT").codeAlpha2("AUT").name("Autriche").capital("Vienne").country("Europe").nationality("Autrichienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AZ").codeAlpha2("AZE").name("Azerbaïdjan").capital("Bakou").country("Asie").nationality("Azerbaïdjanaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BS").codeAlpha2("BHS").name("Bahamas").capital("Nassau").country("Amérique du Nord").nationality("Bahamienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BH").codeAlpha2("BHR").name("Bahreïn").capital("Manama").country("Asie").nationality("Bahreinienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BD").codeAlpha2("BGD").name("Bangladesh").capital("Dacca").country("Asie").nationality("Bangladaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BB").codeAlpha2("BRB").name("Barbade").capital("Bridgetown").country("Amérique du Nord").nationality("Barbadienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BY").codeAlpha2("BLR").name("Bélarus").capital("Minsk").country("Europe").nationality("Biélorusse").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BE").codeAlpha2("BEL").name("Belgique").capital("Bruxelles").country("Europe").nationality("Belge").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BZ").codeAlpha2("BLZ").name("Belize").capital("Belmopan").country("Amérique du Nord").nationality("Bélizienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BJ").codeAlpha2("BEN").name("Bénin").capital("Porto-Novo").country("Afrique").nationality("Béninoise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BT").codeAlpha2("BTN").name("Bhoutan").capital("Thimbu").country("Asie").nationality("Bhoutanaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BO").codeAlpha2("BOL").name("Bolivie (État plurinational de)").capital("Sucre").country("Amérique du Sud").nationality("Bolivienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BA").codeAlpha2("BIH").name("Bosnie-Herzégovine").capital("Sarajevo").country("Europe").nationality("Bosnienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BW").codeAlpha2("BWA").name("Botswana").capital("Gaborone").country("Afrique").nationality("Botswanaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BR").codeAlpha2("BRA").name("Brésil").capital("Brasilia").country("Amérique du Sud").nationality("Brésilienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BN").codeAlpha2("BRN").name("Brunéi Darussalam").capital("Bandar Seri Begawan").country("Asie").nationality("Bruneienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BG").codeAlpha2("BGR").name("Bulgarie").capital("Sofia").country("Europe").nationality("Bulgare").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BF").codeAlpha2("BFA").name("Burkina Faso").capital("Ouagadougou").country("Afrique").nationality("Burkinabé").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("BI").codeAlpha2("BDI").name("Burundi").capital("Bujumbura").country("Afrique").nationality("Burundaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CV").codeAlpha2("CPV").name("Cabo Verde").capital("Praia").country("Afrique").nationality("Cap-verdienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("KH").codeAlpha2("KHM").name("Cambodge").capital("Phnom Penh").country("Asie").nationality("Cambodgienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CM").codeAlpha2("CMR").name("Cameroun").capital("Yaoundé").country("Afrique").nationality("Camerounaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CA").codeAlpha2("CAN").name("Canada").capital("Ottawa").country("Amérique du Nord").nationality("Canadienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CL").codeAlpha2("CHL").name("Chili").capital("Santiago").country("Amérique du Sud").nationality("Chilienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CN").codeAlpha2("CHN").name("Chine").capital("Pékin").country("Asie").nationality("Chinoise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CY").codeAlpha2("CYP").name("Chypre").capital("Nicosie").country("Asie").nationality("Chypriote").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CO").codeAlpha2("COL").name("Colombie").capital("Bogota").country("Amérique du Sud").nationality("Colombienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("KM").codeAlpha2("COM").name("Comores").capital("Moroni").country("Afrique").nationality("Comorienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CG").codeAlpha2("COG").name("Congo").capital("Brazzaville").country("Afrique").nationality("Congolaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CD").codeAlpha2("COD").name("Congo (la République démocratique du)").capital("Kinshasa").country("Afrique").nationality("Congolaise (Kinshasa)").build();  countryRepository.save(country);
			Country.builder().codeAlpha1("KR").codeAlpha2("KOR").name("Corée (la République de)").capital("Séoul").country("Asie").nationality("Sud-coréenne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("KP").codeAlpha2("PRK").name("Corée (la République populaire démocratique de)").capital("Pyongyang").country("Asie").nationality("Nord-coréenne").build();  countryRepository.save(country);
			Country.builder().codeAlpha1("CR").codeAlpha2("CRI").name("Costa Rica").capital("San José").country("Amérique du Nord").nationality("Costaricaine").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CI").codeAlpha2("CIV").name("Côte d'Ivoire").capital("Yamoussoukro").country("Afrique").nationality("Ivoirienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("HR").codeAlpha2("HRV").name("Croatie").capital("Zagreb").country("Europe").nationality("Croate").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("CU").codeAlpha2("CUB").name("Cuba").capital("La Havane").country("Amérique du Nord").nationality("Cubaine").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("DK").codeAlpha2("DNK").name("Danemark").capital("Copenhague").country("Europe").nationality("Danoise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("DJ").codeAlpha2("DJI").name("Djibouti").capital("Djibouti").country("Afrique").nationality("Djiboutienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("DM").codeAlpha2("DMA").name("Dominique").capital("Roseau").country("Amérique du Nord").nationality("Dominiquaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("EG").codeAlpha2("EGY").name("Égypte").capital("Le Caire").country("Afrique").nationality("Égyptienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("AE").codeAlpha2("ARE").name("Émirats arabes unis").capital("Abou Dhabi").country("Asie").nationality("Émirienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("EC").codeAlpha2("ECU").name("Équateur").capital("Quito").country("Amérique du Sud").nationality("Équatorienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("ER").codeAlpha2("ERI").name("Érythrée").capital("Asmara").country("Afrique").nationality("Érythréenne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("ES").codeAlpha2("ESP").name("Espagne").capital("Madrid").country("Europe").nationality("Espagnole").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("EE").codeAlpha2("EST").name("Estonie").capital("Tallinn").country("Europe").nationality("Estonienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("US").codeAlpha2("USA").name("États-Unis d'Amérique").capital("Washington").country("Amérique du Nord").nationality("Américaine").build();  countryRepository.save(country);
			Country.builder().codeAlpha1("ET").codeAlpha2("ETH").name("Éthiopie").capital("Addis-Abeba").country("Afrique").nationality("Éthiopienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("FJ").codeAlpha2("FJI").name("Fidji").capital("Suva").country("Océanie").nationality("Fidjienne").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("FI").codeAlpha2("FIN").name("Finlande").capital("Helsinki").country("Europe").nationality("Finlandaise").build();
			countryRepository.save(country);
			Country.builder().codeAlpha1("FR").codeAlpha2("FRA").name("France").capital("Paris").country("Europe").nationality("Française").build();
			countryRepository.save(country);

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