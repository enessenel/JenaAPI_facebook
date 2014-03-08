import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;


public class hw1 {
public static facebookRelations fb;
	public static void main(String[] args) {
		fb = new facebookRelations();
		
		createJohn();
		try {

			  File file= new File("data.rdf");
			 fb.ontModel.write(new FileOutputStream(file));

			} catch (IOException e) {
			  e.printStackTrace();
			}
	}

	
	public static void createJohn(){
		Individual computerScience = fb.department.createIndividual(fb.ns + "ComputerScience");
		Individual stanford = fb.university.createIndividual(fb.ns + "StanfordUniversity");
		Statement department = fb.ontModel.createStatement(stanford, fb.hasDepartment,computerScience );
		fb.ontModel.add(department);
		
		Individual john = fb.malePerson.createIndividual(fb.ns + "John");
		Literal birthday = fb.ontModel.createTypedLiteral("31.07.1980", XSDDatatype.XSDdate);
		Statement birthdayOfJohn = fb.ontModel.createStatement(john, fb.hasBirthday, birthday);
		fb.ontModel.add(birthdayOfJohn);
			
		Statement graduatedFrom = fb.ontModel.createStatement(john, fb.hasGraduated, computerScience);
		fb.ontModel.add(graduatedFrom);
		Literal graduation = fb.ontModel.createTypedLiteral("2004", XSDDatatype.XSDdate);
		Statement graduatedIn = fb.ontModel.createStatement(john, fb.graduatedIn, graduation);
		fb.ontModel.add(graduatedIn);
		
	}
}
