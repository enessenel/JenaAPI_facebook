/*
 * 	Author: Enes Þenel - S003272
 * 
 * Includes main method for saving rdf/xml format and also create Individuals to
 * create sample data. 
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;

public class hw1 {

	public static facebookRelations fb;

	public static void main(String[] args) {

		fb = new facebookRelations();

		Individual computerScience = fb.department.createIndividual(fb.ns
				+ "ComputerScience");
		Individual stanford = fb.university.createIndividual(fb.ns
				+ "StanfordUniversity");
		createDepartment(stanford, computerScience);

		// male with birthdate 31.07.1980
		Individual john = fb.malePerson.createIndividual(fb.ns + "John");
		Literal birthdayOfJohn = fb.ontModel.createTypedLiteral("31.07.1980",
				XSDDatatype.XSDdate);
		createBirthdayRelation(john, birthdayOfJohn);

		// graduated from CS at Stanford in 2004
		Literal graduation = fb.ontModel.createTypedLiteral("2004",
				XSDDatatype.XSDdate);
		setGraduation(john, computerScience, graduation);

		// Person called jane with 16.06.1970 birthdate
		Individual jane = fb.femalePerson.createIndividual(fb.ns + "Jane");
		Literal birthdayOfJane = fb.ontModel.createTypedLiteral("31.07.1980",
				XSDDatatype.XSDdate);
		createBirthdayRelation(jane, birthdayOfJane);

		// she is from NYC but lives in istanbul
		Individual nyc = fb.city.createIndividual(fb.ns + "NYC");
		setBornedIn(jane, nyc);
		Individual istanbul = fb.city.createIndividual(fb.ns + "Istanbul");
		livesIn(jane, istanbul);

		// john has a pic with jane in his profile
		Individual picture1 = fb.picture.createIndividual(fb.ns + "Picture1");
		hasPicture(john, picture1);
		inPicture(jane, picture1);
		inPicture(john, picture1);

		// wendy is a dog with fb account has a video in jane's profile
		Individual wendy = fb.dog.createIndividual(fb.ns + "Wendy");
		Individual dogVideo = fb.video.createIndividual(fb.ns + "dogVid");
		inVideo(wendy, dogVideo);
		inVideo(jane, dogVideo);
		hasVideo(jane, dogVideo);

		// john and jane likes wendy's pic
		Individual profPic = fb.picture.createIndividual(fb.ns + "profPic");
		hasPicture(wendy, profPic);
		likesMedia(john, profPic);
		likesMedia(jane, profPic);

		// jane and john are friends
		setFriends(jane, john);
		setFriends(john, jane);

		try {

			File file = new File("data.rdf");
			fb.ontModel.write(new FileOutputStream(file));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setFriends(Individual i1, Individual i2) {

		Statement friends = fb.ontModel.createStatement(i1, fb.hasFriend, i2);
		fb.ontModel.add(friends);
	}

	public static void likesMedia(Individual i1, Individual i2) {

		Statement likes = fb.ontModel.createStatement(i1, fb.liked, i2);
		fb.ontModel.add(likes);
	}

	public static void inPicture(Individual i1, Individual i2) {

		Statement picture = fb.ontModel.createStatement(i1, fb.inPicture, i2);
		fb.ontModel.add(picture);
	}

	public static void hasPicture(Individual i1, Individual i2) {

		Statement picture = fb.ontModel.createStatement(i1, fb.hasPicture, i2);
		fb.ontModel.add(picture);
	}

	public static void inVideo(Individual i1, Individual i2) {

		Statement video = fb.ontModel.createStatement(i1, fb.inVideo, i2);
		fb.ontModel.add(video);
	}

	public static void hasVideo(Individual i1, Individual i2) {

		Statement video = fb.ontModel.createStatement(i1, fb.hasVideo, i2);
		fb.ontModel.add(video);
	}

	public static void createDepartment(Individual i1, Individual i2) {

		Statement department = fb.ontModel.createStatement(i1,
				fb.hasDepartment, i2);
		fb.ontModel.add(department);
	}

	public static void createBirthdayRelation(Individual i1, Literal l1) {

		Statement birthday = fb.ontModel
				.createStatement(i1, fb.hasBirthday, l1);
		fb.ontModel.add(birthday);
	}

	public static void setGraduation(Individual i1, Individual i2, Literal g1) {

		Statement graduatedFrom = fb.ontModel.createStatement(i1,
				fb.hasGraduated, i2);
		fb.ontModel.add(graduatedFrom);
		Statement graduatedIn = fb.ontModel.createStatement(i1, fb.graduatedIn,
				g1);
		fb.ontModel.add(graduatedIn);
	}

	public static void setBornedIn(Individual i1, Individual i2) {

		Statement bornedIn = fb.ontModel.createStatement(i1, fb.bornIn, i2);
		fb.ontModel.add(bornedIn);
	}

	public static void livesIn(Individual i1, Individual i2) {

		Statement livesIn = fb.ontModel.createStatement(i1, fb.livesIn, i2);
		fb.ontModel.add(livesIn);
	}

}
