import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
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

	public DatatypeProperty hasAge;
	public DatatypeProperty hasBirthday;

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

	public OntModel ontModel;
	public String ns;
	public String baseURI;

	public facebookRelations(){

		ontModel = ModelFactory.createOntologyModel();
		ns = new String("http://www.facebook.com/onto1#");
		baseURI = new String("http://www.facebook.com/onto1");
		Ontology onto = ontModel.createOntology(baseURI);		
	}

	public void initializeClasses(){

		person 			 = ontModel.createClass(ns + "Person");
		malePerson	     = ontModel.createClass(ns + "MalePerson");
		femalePerson 	 = ontModel.createClass(ns + "FemalePerson");
		school 			 = ontModel.createClass(ns + "School");
		elementarySchool = ontModel.createClass(ns + "ElementarySchool");
		highSchool	     = ontModel.createClass(ns + "HighSchool");
		university 		 = ontModel.createClass(ns + "University");
		media 			 = ontModel.createClass(ns + "Media");
		video 			 = ontModel.createClass(ns + "Video");
		picture			 = ontModel.createClass(ns + "Picture");
	}

	public void intializeDatatypeProperties(){

		hasAge			 = ontModel.createDatatypeProperty(ns + "hasAge");
		hasBirthday		 = ontModel.createDatatypeProperty(ns + "hasBirthday");
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
	}

	public void setSubclasses(){

		person.addSubClass(malePerson);
		person.addSubClass(femalePerson);
		school.addSubClass(elementarySchool);
		school.addSubClass(highSchool);
		school.addSubClass(university);
		media.addSubClass(video);
		media.addSubClass(picture);

		hasParent.addSubProperty(hasFather);
		hasParent.addSubProperty(hasMother);
		hasSibling.addSubProperty(hasSister);
		hasSibling.addSubProperty(hasBrother);
		hasMedia.addSubProperty(hasVideo);
		hasMedia.addSubProperty(inVideo);
		hasMedia.addSubProperty(hasPicture);
		hasMedia.addSubProperty(inPicture);
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

		hasAge.setDomain(person);
		hasAge.setDomain(school);
		hasAge.setRange(XSD.integer);

		hasBirthday.setDomain(person);
		hasBirthday.setRange(XSD.date);

		hasSibling.setDomain(person);
		hasSibling.setRange(person);

		hasParent.setDomain(person);
		hasParent.setRange(person);

	}
}
