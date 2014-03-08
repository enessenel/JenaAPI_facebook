


/* Author: Enes Þenel - S003272
 * 
 * facebookRelations class for defining some objects, subjects and predicates,
 * that can define a simple facebook relationship system.
 * 
 * I defined first classes and predicates and then created them for simplicity and modularity.
 */


import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.MinCardinalityRestriction;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.XSD;


public class facebookRelations {


	public OntClass person;
	public OntClass malePerson;
	public OntClass femalePerson;
	public OntClass school;
	public OntClass elementarySchool;
	public OntClass highSchool;
	public OntClass university;
	public OntClass media;
	public OntClass video;
	public OntClass picture;
	public OntClass department;
	public OntClass city;
	public OntClass animal;
	public OntClass dog;

	public DatatypeProperty hasAge;
	public DatatypeProperty hasBirthday;
	public DatatypeProperty graduatedIn;

	public ObjectProperty hasSibling;
	public ObjectProperty hasSister;
	public ObjectProperty hasBrother;
	public ObjectProperty hasParent;
	public ObjectProperty hasFather;
	public ObjectProperty hasMother;
	public ObjectProperty hasFriend;
	public ObjectProperty hasMedia;
	public ObjectProperty inMedia;
	public ObjectProperty hasVideo;
	public ObjectProperty inVideo;
	public ObjectProperty hasPicture;
	public ObjectProperty inPicture;
	public ObjectProperty hasDepartment;
	public ObjectProperty hasGraduated;
	public ObjectProperty inSchool;
	public ObjectProperty bornIn;
	public ObjectProperty livesIn;
	public ObjectProperty hasPet;
	public ObjectProperty liked;
	public ObjectProperty hasDog;

	MinCardinalityRestriction hasAgeMinCard;
	MinCardinalityRestriction hasDepartmentMinCard;

	public OntModel ontModel;
	public String ns;
	public String baseURI;


	public facebookRelations(){

		ontModel = ModelFactory.createOntologyModel();
		ns = new String("http://www.facebook.com/onto1#");
		baseURI = new String("http://www.facebook.com/onto1");
		Ontology onto = ontModel.createOntology(baseURI);	

		initializeClasses();
		intializeDatatypeProperties();
		initializeObjectProperties();
		setSubclasses();
		setDisjoints();
		setDomainAndRange();
		setCardinalityRestrictions();
	}


	public void initializeClasses(){

		person 			 = ontModel.createClass(ns + "Person");
		malePerson	     = ontModel.createClass(ns + "MalePerson");
		femalePerson 	 = ontModel.createClass(ns + "FemalePerson");
		school 			 = ontModel.createClass(ns + "School");
		elementarySchool = ontModel.createClass(ns + "ElementarySchool");
		highSchool	     = ontModel.createClass(ns + "HighSchool");
		university 		 = ontModel.createClass(ns + "University");
		department 		 = ontModel.createClass(ns + "Department");
		media 			 = ontModel.createClass(ns + "Media");
		video 			 = ontModel.createClass(ns + "Video");
		picture			 = ontModel.createClass(ns + "Picture");
		city			 = ontModel.createClass(ns + "City");
		animal			 = ontModel.createClass(ns + "Animal");
		dog 			 = ontModel.createClass(ns + "Dog");
	}

	public void intializeDatatypeProperties(){

		hasAge			 = ontModel.createDatatypeProperty(ns + "hasAge");
		hasBirthday		 = ontModel.createDatatypeProperty(ns + "hasBirthday");
		graduatedIn 	 = ontModel.createDatatypeProperty(ns + "graduatedIn");
	}

	public void initializeObjectProperties(){

		hasSibling		 = ontModel.createObjectProperty(ns + "hasSibling");
		hasSister 		 = ontModel.createObjectProperty(ns + "hasSister");
		hasBrother 	     = ontModel.createObjectProperty(ns + "hasBrother");
		hasParent 		 = ontModel.createObjectProperty(ns + "hasParent");
		hasFather 		 = ontModel.createObjectProperty(ns + "hasFather");
		hasMother 		 = ontModel.createObjectProperty(ns + "hasMother");
		hasFriend 		 = ontModel.createObjectProperty(ns + "hasFriend");
		hasMedia 		 = ontModel.createObjectProperty(ns + "hasMedia");
		inMedia 		 = ontModel.createObjectProperty(ns + "inMedia");
		hasVideo		 = ontModel.createObjectProperty(ns + "hasVideo");
		inVideo			 = ontModel.createObjectProperty(ns + "inVideo");
		hasPicture		 = ontModel.createObjectProperty(ns + "hasPicture");
		inPicture 		 = ontModel.createObjectProperty(ns + "inPicture");
		hasDepartment    = ontModel.createObjectProperty(ns + "hasDepartment");
		hasGraduated     = ontModel.createObjectProperty(ns + "hasGraduated");
		bornIn  		 = ontModel.createObjectProperty(ns + "bornIn");
		livesIn 		 = ontModel.createObjectProperty(ns + "livesIn");
		hasPet 			 = ontModel.createObjectProperty(ns + "hasPet");
		liked 			 = ontModel.createObjectProperty(ns + "liked");
		hasDog 			 = ontModel.createObjectProperty(ns + "hasDog");
	}

	public void setSubclasses(){

		person.addSubClass(malePerson);
		person.addSubClass(femalePerson);
		school.addSubClass(elementarySchool);
		school.addSubClass(highSchool);
		school.addSubClass(university);
		media.addSubClass(video);
		media.addSubClass(picture);
		animal.addSubClass(dog);

		hasParent.addSubProperty(hasFather);
		hasParent.addSubProperty(hasMother);
		hasSibling.addSubProperty(hasSister);
		hasSibling.addSubProperty(hasBrother);
		hasMedia.addSubProperty(hasVideo);
		inMedia.addSubProperty(inVideo);
		hasMedia.addSubProperty(hasPicture);
		inMedia.addSubProperty(inPicture);
		hasPet.addSubProperty(hasDog);
	}

	public void setDisjoints(){

		malePerson.addDisjointWith(femalePerson);
		femalePerson.addDisjointWith(malePerson);

		elementarySchool.addDisjointWith(highSchool);
		elementarySchool.addDisjointWith(university);
		highSchool.addDisjointWith(elementarySchool);
		highSchool.addDisjointWith(university);
		university.addDisjointWith(elementarySchool);
		university.addDisjointWith(highSchool);

		video.addDisjointWith(picture);
		picture.addDisjointWith(video);
	}

	public void setDomainAndRange(){

		liked.setDomain(person);
		liked.setDomain(animal);
		liked.setRange(media);

		hasGraduated.setDomain(person);
		hasGraduated.setRange(department);

		hasAge.setDomain(person);
		hasAge.setDomain(school);
		hasAge.setRange(XSD.integer);

		graduatedIn.setDomain(person);
		graduatedIn.setRange(XSD.date);

		hasBirthday.setDomain(person);
		hasBirthday.setRange(XSD.date);

		hasSibling.setDomain(person);
		hasSibling.setRange(person);

		hasParent.setDomain(person);
		hasParent.setRange(person);

		hasFriend.setDomain(person);
		hasFriend.setDomain(person);
		hasDepartment.setDomain(university);
		hasDepartment.setRange(department);

		bornIn.setDomain(person);
		bornIn.setRange(city);

		livesIn.setDomain(person);
		livesIn.setRange(city);

		hasMedia.setDomain(animal);
		hasMedia.setDomain(person);
		hasMedia.setRange(media);

		inMedia.setDomain(animal);
		inMedia.setDomain(person);
		inMedia.setRange(media);

		hasPet.setDomain(person);
		hasPet.setRange(animal);
	}

	public void setCardinalityRestrictions(){

		hasAgeMinCard = ontModel.createMinCardinalityRestriction(null, hasAge, 1);
		hasDepartmentMinCard = ontModel.createMinCardinalityRestriction(null, hasDepartment, 1);
	}
}
