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
			ContractType contractType=ContractType.builder().code("CDI").active(true).description("Contrat à Durée Indéterminée : Le contrat n'a pas de date de fin prédéterminée et offre une stabilité d'emploi.").build();
			contractTypeRepository.save(contractType);
			contractType=ContractType.builder().code("CDD").active(true).description("Contrat à Durée Déterminée : Le contrat a une date de fin précise, souvent utilisé pour des missions temporaires ou des projets spécifiques.").build();
			contractTypeRepository.save(contractType);
			contractType=ContractType.builder().code("Intérim").active(true).description("Contrat de travail temporaire  : Le salarié est mis à disposition par une agence de travail temporaire pour une mission chez un client, similaire à l'intérim.").build();
			contractTypeRepository.save(contractType);
			contractType=ContractType.builder().code("Stage").active(true).description("Contrat de stage : Souvent utilisé pour les étudiants ou jeunes diplômés pour acquérir de l'expérience professionnelle.").build();
			contractTypeRepository.save(contractType);
		};
	}
	//@Bean
	CommandLineRunner commandLineRunner2(ClassificationTypeRepository classificationTypeRepository){
		return args -> {
			ClassificationType classificationType=ClassificationType.builder().nom("Employé").active(true).description("Travaille dans des tâches administratives ou de soutien, généralement sous la supervision d'un cadre.").build();
			classificationTypeRepository.save(classificationType);
			classificationType=ClassificationType.builder().nom("Ouvrier").active(true).description("Exerce des tâches manuelles ou techniques dans des secteurs comme l'industrie ou la construction.").build();
			classificationTypeRepository.save(classificationType);
			classificationType=ClassificationType.builder().nom("Cadre").active(true).description("Responsable de la supervision et de la gestion des autres employés, souvent impliqué dans la prise de décisions stratégiques.").build();
			classificationTypeRepository.save(classificationType);
			classificationType=ClassificationType.builder().nom("Agent de maîtrise").active(true).description("Occupe un poste intermédiaire entre l'ouvrier et le cadre, souvent responsable de la supervision directe des équipes.").build();
			classificationTypeRepository.save(classificationType);
		};
	}
	//@Bean
	public CommandLineRunner testAddCountry(CountryRepository countryRepository) {
		return args -> {
			Country country = Country.builder().codeAlpha1("USA").codeAlpha2("US").name("United States").capital("Washington, D.C.").country("United States").active(true).nationality("American").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AF").codeAlpha2("AFG").name("Afghanistan").capital("Kaboul").country("Afghanistan").active(true).nationality("Afghane").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("ZA").codeAlpha2("ZAF").name("Afrique du Sud").capital("Pretoria").country("Afrique").active(true).nationality("Sud-africaine").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AL").codeAlpha2("ALB").name("Albanie").capital("Tirana").country("Europe").active(true).nationality("Albanaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("DZ").codeAlpha2("DZA").name("Algérie").capital("Alger").country("Afrique").active(true).nationality("Algérienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("DE").codeAlpha2("DEU").name("Allemagne").capital("Berlin").country("Europe").active(true).nationality("Allemande").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AD").codeAlpha2("AND").name("Andorre").capital("Andorre-la-Vieille").country("Europe").active(true).nationality("Andorrane").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AO").codeAlpha2("AGO").name("Angola").capital("Luanda").country("Afrique").active(true).nationality("Angolaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AG").codeAlpha2("ATG").name("Antigua-et-Barbuda").capital("Saint John's").country("Amérique du Nord").active(true).nationality("Antiguaise et barbudienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("SA").codeAlpha2("SAU").name("Arabie saoudite").capital("Riyad").country("Asie").active(true).nationality("Saoudienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AR").codeAlpha2("ARG").name("Argentine").capital("Buenos Aires").country("Amérique du Sud").active(true).nationality("Argentine").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AM").codeAlpha2("ARM").name("Arménie").capital("Erevan").country("Asie").active(true).nationality("Arménienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AU").codeAlpha2("AUS").name("Australie").capital("Canberra").country("Océanie").active(true).nationality("Australienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AT").codeAlpha2("AUT").name("Autriche").capital("Vienne").country("Europe").active(true).nationality("Autrichienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AZ").codeAlpha2("AZE").name("Azerbaïdjan").capital("Bakou").country("Asie").active(true).nationality("Azerbaïdjanaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BS").codeAlpha2("BHS").name("Bahamas").capital("Nassau").country("Amérique du Nord").active(true).nationality("Bahamienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BH").codeAlpha2("BHR").name("Bahreïn").capital("Manama").country("Asie").active(true).nationality("Bahreinienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BD").codeAlpha2("BGD").name("Bangladesh").capital("Dacca").country("Asie").active(true).nationality("Bangladaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BB").codeAlpha2("BRB").name("Barbade").capital("Bridgetown").country("Amérique du Nord").active(true).nationality("Barbadienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BY").codeAlpha2("BLR").name("Bélarus").capital("Minsk").country("Europe").active(true).nationality("Biélorusse").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BE").codeAlpha2("BEL").name("Belgique").capital("Bruxelles").country("Europe").active(true).nationality("Belge").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BZ").codeAlpha2("BLZ").name("Belize").capital("Belmopan").country("Amérique du Nord").active(true).nationality("Bélizienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BJ").codeAlpha2("BEN").name("Bénin").capital("Porto-Novo").country("Afrique").active(true).nationality("Béninoise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BT").codeAlpha2("BTN").name("Bhoutan").capital("Thimbu").country("Asie").active(true).nationality("Bhoutanaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BO").codeAlpha2("BOL").name("Bolivie (État plurinational de)").capital("Sucre").country("Amérique du Sud").active(true).nationality("Bolivienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BA").codeAlpha2("BIH").name("Bosnie-Herzégovine").capital("Sarajevo").country("Europe").active(true).nationality("Bosnienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BW").codeAlpha2("BWA").name("Botswana").capital("Gaborone").country("Afrique").active(true).nationality("Botswanaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BR").codeAlpha2("BRA").name("Brésil").capital("Brasilia").country("Amérique du Sud").active(true).nationality("Brésilienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BN").codeAlpha2("BRN").name("Brunéi Darussalam").capital("Bandar Seri Begawan").country("Asie").active(true).nationality("Bruneienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BG").codeAlpha2("BGR").name("Bulgarie").capital("Sofia").country("Europe").active(true).nationality("Bulgare").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BF").codeAlpha2("BFA").name("Burkina Faso").capital("Ouagadougou").country("Afrique").active(true).nationality("Burkinabé").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("BI").codeAlpha2("BDI").name("Burundi").capital("Bujumbura").country("Afrique").active(true).nationality("Burundaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CV").codeAlpha2("CPV").name("Cabo Verde").capital("Praia").country("Afrique").active(true).nationality("Cap-verdienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("KH").codeAlpha2("KHM").name("Cambodge").capital("Phnom Penh").country("Asie").active(true).nationality("Cambodgienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CM").codeAlpha2("CMR").name("Cameroun").capital("Yaoundé").country("Afrique").active(true).nationality("Camerounaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CA").codeAlpha2("CAN").name("Canada").capital("Ottawa").country("Amérique du Nord").active(true).nationality("Canadienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CL").codeAlpha2("CHL").name("Chili").capital("Santiago").country("Amérique du Sud").active(true).nationality("Chilienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CN").codeAlpha2("CHN").name("Chine").capital("Pékin").country("Asie").active(true).nationality("Chinoise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CY").codeAlpha2("CYP").name("Chypre").capital("Nicosie").country("Asie").active(true).nationality("Chypriote").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CO").codeAlpha2("COL").name("Colombie").capital("Bogota").country("Amérique du Sud").active(true).nationality("Colombienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("KM").codeAlpha2("COM").name("Comores").capital("Moroni").country("Afrique").active(true).nationality("Comorienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CG").codeAlpha2("COG").name("Congo").capital("Brazzaville").country("Afrique").active(true).nationality("Congolaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CD").codeAlpha2("COD").name("Congo (la République démocratique du)").capital("Kinshasa").country("Afrique").active(true).nationality("Congolaise (Kinshasa)").build();  countryRepository.save(country);
			country = Country.builder().codeAlpha1("KR").codeAlpha2("KOR").name("Corée (la République de)").capital("Séoul").country("Asie").active(true).nationality("Sud-coréenne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("KP").codeAlpha2("PRK").name("Corée (la République populaire démocratique de)").capital("Pyongyang").country("Asie").active(true).nationality("Nord-coréenne").build();  countryRepository.save(country);
			country = Country.builder().codeAlpha1("CR").codeAlpha2("CRI").name("Costa Rica").capital("San José").country("Amérique du Nord").active(true).nationality("Costaricaine").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CI").codeAlpha2("CIV").name("Côte d'Ivoire").capital("Yamoussoukro").country("Afrique").active(true).nationality("Ivoirienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("HR").codeAlpha2("HRV").name("Croatie").capital("Zagreb").country("Europe").active(true).nationality("Croate").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("CU").codeAlpha2("CUB").name("Cuba").capital("La Havane").country("Amérique du Nord").active(true).nationality("Cubaine").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("DK").codeAlpha2("DNK").name("Danemark").capital("Copenhague").country("Europe").active(true).nationality("Danoise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("DJ").codeAlpha2("DJI").name("Djibouti").capital("Djibouti").country("Afrique").active(true).nationality("Djiboutienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("DM").codeAlpha2("DMA").name("Dominique").capital("Roseau").country("Amérique du Nord").active(true).nationality("Dominiquaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("EG").codeAlpha2("EGY").name("Égypte").capital("Le Caire").country("Afrique").active(true).nationality("Égyptienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("AE").codeAlpha2("ARE").name("Émirats arabes unis").capital("Abou Dhabi").country("Asie").active(true).nationality("Émirienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("EC").codeAlpha2("ECU").name("Équateur").capital("Quito").country("Amérique du Sud").active(true).nationality("Équatorienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("ER").codeAlpha2("ERI").name("Érythrée").capital("Asmara").country("Afrique").active(true).nationality("Érythréenne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("ES").codeAlpha2("ESP").name("Espagne").capital("Madrid").country("Europe").active(true).nationality("Espagnole").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("EE").codeAlpha2("EST").name("Estonie").capital("Tallinn").country("Europe").active(true).nationality("Estonienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("US").codeAlpha2("USA").name("États-Unis d'Amérique").capital("Washington").country("Amérique du Nord").active(true).nationality("Américaine").build();  countryRepository.save(country);
			country = Country.builder().codeAlpha1("ET").codeAlpha2("ETH").name("Éthiopie").capital("Addis-Abeba").country("Afrique").active(true).nationality("Éthiopienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("FJ").codeAlpha2("FJI").name("Fidji").capital("Suva").country("Océanie").active(true).nationality("Fidjienne").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("FI").codeAlpha2("FIN").name("Finlande").capital("Helsinki").country("Europe").active(true).nationality("Finlandaise").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("FR").codeAlpha2("FRA").name("France").capital("Paris").country("Europe").active(true).nationality("Française").build();
			countryRepository.save(country);
			country = Country.builder().codeAlpha1("GA").codeAlpha2("GAB").name("Gabon").capital("Libreville").country("Afrique").active(true).nationality("Gabonaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GM").codeAlpha2("GMB").name("Gambie").capital("Banjul").country("Afrique").active(true).nationality("Gambienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GE").codeAlpha2("GEO").name("Géorgie").capital("Tbilissi").country("Asie").active(true).nationality("Georgienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GH").codeAlpha2("GHA").name("Ghana").capital("Accra").country("Afrique").active(true).nationality("Ghanéenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GR").codeAlpha2("GRC").name("Grèce").capital("Athènes").country("Europe").active(true).nationality("Hellénique").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GD").codeAlpha2("GRD").name("Grenade").capital("Saint George's").country("Amérique du Nord").active(true).nationality("Grenadienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GT").codeAlpha2("GTM").name("Guatemala").capital("Guatemala").country("Amérique du Nord").active(true).nationality("Guatemaltèque").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GN").codeAlpha2("GIN").name("Guinée").capital("Conakry").country("Afrique").active(true).nationality("Guinéenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GQ").codeAlpha2("GNQ").name("Guinée équatoriale").capital("Malabo").country("Afrique").active(true).nationality("Equato-guinéenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GW").codeAlpha2("GNB").name("Guinée-Bissau").capital("Bissau").country("Afrique").active(true).nationality("Bissau-Guinéenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GY").codeAlpha2("GUY").name("Guyana").capital("Georgetown").country("Amérique du Sud").active(true).nationality("Guyanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("HT").codeAlpha2("HTI").name("Haïti").capital("Port-au-Prince").country("Amérique du Nord").active(true).nationality("Haïtienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("HN").codeAlpha2("HND").name("Honduras").capital("Tegucigalpa").country("Amérique du Nord").active(true).nationality("Hondurienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("HU").codeAlpha2("HUN").name("Hongrie").capital("Budapest").country("Europe").active(true).nationality("Hongroise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IN").codeAlpha2("IND").name("Inde").capital("New Delhi").country("Asie").active(true).nationality("Indienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("ID").codeAlpha2("IDN").name("Indonésie").capital("Jakarta").country("Asie").active(true).nationality("Indonésienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IR").codeAlpha2("IRN").name("Iran").capital("Téhéran").country("Asie").active(true).nationality("Iranienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IQ").codeAlpha2("IRQ").name("Iraq").capital("Bagdad").country("Asie").active(true).nationality("Irakienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IE").codeAlpha2("IRL").name("Irlande").capital("Dublin").country("Europe").active(true).nationality("Irlandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IS").codeAlpha2("ISL").name("Islande").capital("Reykjavik").country("Europe").active(true).nationality("Islandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IL").codeAlpha2("ISR").name("Israël").capital("Jérusalem").country("Asie").active(true).nationality("Israélienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("IT").codeAlpha2("ITA").name("Italie").capital("Rome").country("Europe").active(true).nationality("Italienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("JM").codeAlpha2("JAM").name("Jamaïque").capital("Kingston").country("Amérique du Nord").active(true).nationality("Jamaïcaine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("JP").codeAlpha2("JPN").name("Japon").capital("Tokyo").country("Asie").active(true).nationality("Japonaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("JO").codeAlpha2("JOR").name("Jordanie").capital("Amman").country("Asie").active(true).nationality("Jordanienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("KZ").codeAlpha2("KAZ").name("Kazakhstan").capital("Astana").country("Asie").active(true).nationality("Kazakhstanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("KE").codeAlpha2("KEN").name("Kenya").capital("Nairobi").country("Afrique").active(true).nationality("Kenyane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("KG").codeAlpha2("KGZ").name("Kirghizistan").capital("Bichkek").country("Asie").active(true).nationality("Kirghize").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("KI").codeAlpha2("KIR").name("Kiribati").capital("Tarawa").country("Océanie").active(true).nationality("Kiribatienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("KW").codeAlpha2("KWT").name("Koweït").capital("Koweït").country("Asie").active(true).nationality("Koweitienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LA").codeAlpha2("LAO").name("Lao").capital("Vientiane").country("Asie").active(true).nationality("Laotienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LS").codeAlpha2("LSO").name("Lesotho").capital("Maseru").country("Afrique").active(true).nationality("Lesothane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LV").codeAlpha2("LVA").name("Lettonie").capital("Riga").country("Europe").active(true).nationality("Lettone").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LB").codeAlpha2("LBN").name("Liban").capital("Beyrouth").country("Asie").active(true).nationality("Libanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LR").codeAlpha2("LBR").name("Libéria").capital("Monrovia").country("Afrique").active(true).nationality("Libérienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LY").codeAlpha2("LBY").name("Libye").capital("Tripoli").country("Afrique").active(true).nationality("Libyenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LI").codeAlpha2("LIE").name("Liechtenstein").capital("Vaduz").country("Europe").active(true).nationality("Liechtensteinoise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LT").codeAlpha2("LTU").name("Lituanie").capital("Vilnius").country("Europe").active(true).nationality("Lituanienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LU").codeAlpha2("LUX").name("Luxembourg").capital("Luxembourg").country("Europe").active(true).nationality("Luxembourgeoise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MK").codeAlpha2("MKD").name("Macédoine du Nord").capital("Skopje").country("Europe").active(true).nationality("Macédonienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MG").codeAlpha2("MDG").name("Madagascar").capital("Antananarivo").country("Afrique").active(true).nationality("Malgache").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MY").codeAlpha2("MYS").name("Malaisie").capital("Kuala Lumpur").country("Asie").active(true).nationality("Malaisienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MW").codeAlpha2("MWI").name("Malawi").capital("Lilongwe").country("Afrique").active(true).nationality("Malawienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MV").codeAlpha2("MDV").name("Maldives").capital("Malé").country("Asie").active(true).nationality("Maldivienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("ML").codeAlpha2("MLI").name("Mali").capital("Bamako").country("Afrique").active(true).nationality("Malienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MT").codeAlpha2("MLT").name("Malte").capital("La Valette").country("Europe").active(true).nationality("Maltaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MA").codeAlpha2("MAR").name("Maroc").capital("Rabat").country("Afrique").active(true).nationality("Marocaine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MH").codeAlpha2("MHL").name("Marshall (les Îles)").capital("Majuro").country("Océanie").active(true).nationality("Marshallaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MU").codeAlpha2("MUS").name("Maurice").capital("Port Louis").country("Afrique").active(true).nationality("Mauricienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MR").codeAlpha2("MRT").name("Mauritanie").capital("Nouakchott").country("Afrique").active(true).nationality("Mauritanienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MX").codeAlpha2("MEX").name("Mexique").capital("Mexico").country("Amérique du Nord").active(true).nationality("Mexicaine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("FM").codeAlpha2("FSM").name("Micronésie (États fédérés de)").capital("Palikir").country("Océanie").active(true).nationality("Micronésienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MD").codeAlpha2("MDA").name("Moldavie").capital("Chisinau").country("Europe").active(true).nationality("Moldave").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MC").codeAlpha2("MCO").name("Monaco").capital("Monaco").country("Europe").active(true).nationality("Monégasque").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MN").codeAlpha2("MNG").name("Mongolie").capital("Oulan-Bator").country("Asie").active(true).nationality("Mongole").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("ME").codeAlpha2("MNE").name("Monténégro").capital("Podgorica").country("Europe").active(true).nationality("Monténégrine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MZ").codeAlpha2("MOZ").name("Mozambique").capital("Maputo").country("Afrique").active(true).nationality("Mozambicaine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("MM").codeAlpha2("MMR").name("Myanmar").capital("Naypyidaw").country("Asie").active(true).nationality("Birmane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NA").codeAlpha2("NAM").name("Namibie").capital("Windhoek").country("Afrique").active(true).nationality("Namibienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NR").codeAlpha2("NRU").name("Nauru").capital("Yaren").country("Océanie").active(true).nationality("Nauruane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NP").codeAlpha2("NPL").name("Népal").capital("Katmandou").country("Asie").active(true).nationality("Népalaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NI").codeAlpha2("NIC").name("Nicaragua").capital("Managua").country("Amérique du Nord").active(true).nationality("Nicaraguayenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NE").codeAlpha2("NER").name("Niger").capital("Niamey").country("Afrique").active(true).nationality("Nigérienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NG").codeAlpha2("NGA").name("Nigéria").capital("Abuja").country("Afrique").active(true).nationality("Nigériane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NO").codeAlpha2("NOR").name("Norvège").capital("Oslo").country("Europe").active(true).nationality("Norvégienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NZ").codeAlpha2("NZL").name("Nouvelle-Zélande").capital("Wellington").country("Océanie").active(true).nationality("Neo-zélandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("OM").codeAlpha2("OMN").name("Oman").capital("Mascate").country("Asie").active(true).nationality("Omanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("UG").codeAlpha2("UGA").name("Ouganda").capital("Kampala").country("Afrique").active(true).nationality("Ougandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("UZ").codeAlpha2("UZB").name("Ouzbékistan").capital("Tachkent").country("Asie").active(true).nationality("Ouzbeke").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PK").codeAlpha2("PAK").name("Pakistan").capital("Islamabad").country("Asie").active(true).nationality("Pakistanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PW").codeAlpha2("PLW").name("Palaos").capital("Melekeok").country("Océanie").active(true).nationality("Palau").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PS").codeAlpha2("PSE").name("Palestine").capital("Jérusalem-Est").country("Asie").active(true).nationality("Palestinienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PA").codeAlpha2("PAN").name("Panama").capital("Panama").country("Amérique du Nord").active(true).nationality("Panaméenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PG").codeAlpha2("PNG").name("Papouasie-Nouvelle-Guinée").capital("Port Moresby").country("Océanie").active(true).nationality("Papouane-neoguinéenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PY").codeAlpha2("PRY").name("Paraguay").capital("Asunción").country("Amérique du Sud").active(true).nationality("Paraguayenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("NL").codeAlpha2("NLD").name("Pays-Bas").capital("Amsterdam").country("Europe").active(true).nationality("Néerlandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PE").codeAlpha2("PER").name("Pérou").capital("Lima").country("Amérique du Sud").active(true).nationality("Péruvienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PH").codeAlpha2("PHL").name("Philippines").capital("Manille").country("Asie").active(true).nationality("Philippine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PL").codeAlpha2("POL").name("Pologne").capital("Varsovie").country("Europe").active(true).nationality("Polonaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("PT").codeAlpha2("PRT").name("Portugal").capital("Lisbonne").country("Europe").active(true).nationality("Portugaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("QA").codeAlpha2("QAT").name("Qatar").capital("Doha").country("Asie").active(true).nationality("Qatarienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SY").codeAlpha2("SYR").name("République arabe syrienne").capital("Damas").country("Asie").active(true).nationality("Syrienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("CF").codeAlpha2("CAF").name("République centrafricaine").capital("Bangui").country("Afrique").active(true).nationality("Centrafricaine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("RO").codeAlpha2("ROU").name("Roumanie").capital("Bucarest").country("Europe").active(true).nationality("Roumaine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("GB").codeAlpha2("GBR").name("Royaume-Uni de Grande-Bretagne et d'Irlande du Nord").capital("Londres").country("Europe").active(true).nationality("Britannique (RU)").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("RU").codeAlpha2("RUS").name("Russie").capital("Moscou").country("Europe").active(true).nationality("Russe").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("RW").codeAlpha2("RWA").name("Rwanda").capital("Kigali").country("Afrique").active(true).nationality("Rwandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LC").codeAlpha2("LCA").name("Sainte-Lucie").capital("Castries").country("Amérique du Nord").active(true).nationality("Saint-Lucienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("KN").codeAlpha2("KNA").name("Saint-Kitts-et-Nevis").capital("Basseterre").country("Amérique du Nord").active(true).nationality("Kittitienne-et-névicienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SM").codeAlpha2("SMR").name("Saint-Marin").capital("Saint-Marin").country("Europe").active(true).nationality("Saint-Marinaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("VA").codeAlpha2("VAT").name("Saint-Siège").capital("Vatican").country("Europe").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("VC").codeAlpha2("VCT").name("Saint-Vincent-et-les Grenadines").capital("Kingstown").country("Amérique du Nord").active(true).nationality("Saint-Vincentaise-et-Grenadine").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SB").codeAlpha2("SLB").name("Salomon (les Îles)").capital("Honiara").country("Océanie").active(true).nationality("Salomonaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("WS").codeAlpha2("WSM").name("Samoa").capital("Apia").country("Océanie").active(true).nationality("Samoane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("ST").codeAlpha2("STP").name("Sao Tomé-et-Principe").capital("São Tomé").country("Afrique").active(true).nationality("Santoméenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SN").codeAlpha2("SEN").name("Sénégal").capital("Dakar").country("Afrique").active(true).nationality("Sénégalaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("RS").codeAlpha2("SRB").name("Serbie").capital("Belgrade").country("Europe").active(true).nationality("Serbe").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SC").codeAlpha2("SYC").name("Seychelles").capital("Victoria").country("Afrique").active(true).nationality("Seychelloise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SL").codeAlpha2("SLE").name("Sierra Leone").capital("Freetown").country("Afrique").active(true).nationality("Sierra-leonaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SG").codeAlpha2("SGP").name("Singapour").capital("Singapour").country("Asie").active(true).nationality("Singapourienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SK").codeAlpha2("SVK").name("Slovaquie").capital("Bratislava").country("Europe").active(true).nationality("Slovaque").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SI").codeAlpha2("SVN").name("Slovénie").capital("Ljubljana").country("Europe").active(true).nationality("Slovène").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SO").codeAlpha2("SOM").name("Somalie").capital("Mogadiscio").country("Afrique").active(true).nationality("Somalienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SD").codeAlpha2("SDN").name("Soudan").capital("Khartoum").country("Afrique").active(true).nationality("Soudanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SS").codeAlpha2("SSD").name("Soudan du Sud").capital("Djouba").country("Afrique").active(true).nationality("Sud soudanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("LK").codeAlpha2("LKA").name("Sri Lanka").capital("Sri Jayawardenapura").country("Asie").active(true).nationality("Sri-lankaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SE").codeAlpha2("SWE").name("Suède").capital("Stockholm").country("Europe").active(true).nationality("Suédoise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("CH").codeAlpha2("CHE").name("Suisse").capital("Berne").country("Europe").active(true).nationality("Suisse").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("SR").codeAlpha2("SUR").name("Suriname").capital("Paramaribo").country("Amérique du Sud").active(true).nationality("Surinamaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TJ").codeAlpha2("TJK").name("Tadjikistan").capital("Douchanbe").country("Asie").active(true).nationality("Tadjike").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TW").codeAlpha2("TWN").name("Taïwan").capital("Taipei").country("Asie").active(true).nationality("Taiwanaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TZ").codeAlpha2("TZA").name("Tanzanie").capital("Dodoma").country("Afrique").active(true).nationality("Tanzanienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TD").codeAlpha2("TCD").name("Tchad").capital("N'Djamena").country("Afrique").active(true).nationality("Tchadienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("CZ").codeAlpha2("CZE").name("Tchéquie").capital("Prague").country("Europe").active(true).nationality("Tchèque").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TH").codeAlpha2("THA").name("Thaïlande").capital("Bangkok").country("Asie").active(true).nationality("Thaïlandaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TL").codeAlpha2("TLS").name("Timor-Leste").capital("Dili").country("Asie").active(true).nationality("Est-timoraise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TG").codeAlpha2("TGO").name("Togo").capital("Lomé").country("Afrique").active(true).nationality("Togolaise").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TO").codeAlpha2("TON").name("Tonga").capital("Nukualofa").country("Océanie").active(true).nationality("Tongienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TT").codeAlpha2("TTO").name("Trinité-et-Tobago").capital("Port of Spain").country("Amérique du Nord").active(true).nationality("Trinidadienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TN").codeAlpha2("TUN").name("Tunisie").capital("Tunis").country("Afrique").active(true).nationality("Tunisienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TM").codeAlpha2("TKM").name("Turkménistan").capital("Achgabat").country("Asie").active(true).nationality("Turkmène").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TR").codeAlpha2("TUR").name("Turquie").capital("Ankara").country("Asie").active(true).nationality("Turque").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("TV").codeAlpha2("TUV").name("Tuvalu").capital("Fanafuti").country("Océanie").active(true).nationality("Tuvaluane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("UA").codeAlpha2("UKR").name("Ukraine").capital("Kiev").country("Europe").active(true).nationality("Ukrainienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("UY").codeAlpha2("URY").name("Uruguay").capital("Montevideo").country("Amérique du Sud").active(true).nationality("Uruguayenne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("VU").codeAlpha2("VUT").name("Vanuatu").capital("Port-Vila").country("Océanie").active(true).nationality("Vanuatuane").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("VE").codeAlpha2("VEN").name("Venezuela").capital("Caracas").country("Amérique du Sud").active(true).nationality("Vénézuélienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("VN").codeAlpha2("VNM").name("Viêt Nam").capital("Hanoï").country("Asie").active(true).nationality("Vietnamienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("YE").codeAlpha2("YEM").name("Yémen").capital("Sanaa").country("Asie").active(true).nationality("Yéménite").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("ZM").codeAlpha2("ZMB").name("Zambie").capital("Lusaka").country("Afrique").active(true).nationality("Zambienne").build();
			countryRepository.save(country);

			country = Country.builder().codeAlpha1("ZW").codeAlpha2("ZWE").name("Zimbabwe").capital("Harare").country("Afrique").active(true).nationality("Zimbabwéenne").build();
			countryRepository.save(country);
		};
	}
	////@Bean
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
	////@Bean
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