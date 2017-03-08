package cn.lcy.ontologyconstruction.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import cn.lcy.ontologyconstruction.config.Config;

public class ConstructionBaseDAOImpl implements ConstructionBaseDAOI {
	
	public static final OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );

	public static final Model loadModel = FileManager.get().readModel(model, Config.ontologyPath);
	
	@Override
	public OntClass createOntClass(String className) {
		return model.createClass(Config.pizzaNs + className);
	}
	
	@Override
	public OntClass getOntClass(String ontClassName) {
		return model.getOntClass(Config.pizzaNs + ontClassName);
	}
	
	
	@Override
	public void addSameAs(Individual individual, Individual aliasIndividual) {
		individual.addSameAs(aliasIndividual);
	}
	
	@Override
	public void addSubClass(OntClass ontClass, OntClass subClass) {
		
		ontClass.addSubClass(subClass);
	}
	
	@Override
	public void addSubClass(Individual individual, OntClass ontClass) {
		individual.addOntClass(ontClass);
	}
	
	@Override
	public Individual createIndividual(String individualId, OntClass genusClass) {
		return model.createIndividual(Config.pizzaNs + individualId, genusClass);
	}
	
	@Override
	public Individual getIndividual(String individualId) {
		return model.getIndividual(Config.pizzaNs + individualId);
	}
	
	@Override
	public boolean addComment(Individual individual, String comment) {
		Literal ccommentLiteral = model.createLiteral(comment);
		individual.addComment(ccommentLiteral);
		File file = new File(Config.ontologyPath);
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			model.write(fw, "RDF/XML-ABBREV");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean addDataProperty(Individual individual, String propertyName, String propertyValue) {
		if(propertyName == null || propertyValue == null) {
			return false;
		}
		DatatypeProperty property = model.createDatatypeProperty(Config.pizzaNs + "有" + propertyName);
		individual.addProperty(property, propertyValue);
		File file = new File(Config.ontologyPath);

		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			model.write(fw, "RDF/XML-ABBREV");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean addDataProperties(Individual individual , List<String> propertyNames, List<String> propertyValues) {
		if(propertyNames == null || propertyValues == null) {
			return false;
		}
		for(int i = 0; i < propertyNames.size(); i++) {
			DatatypeProperty property = model.createDatatypeProperty(Config.pizzaNs + "有" + propertyNames.get(i));
			individual.addProperty(property, propertyValues.get(i));
		}
		
		File file = new File(Config.ontologyPath);
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			model.write(fw, "RDF/XML-ABBREV");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}

	@Override
	public Resource addObjectProperty(Individual individualStart, String objectPropertyName, Individual individualEnd) {
		if(individualStart == null || objectPropertyName == null || individualEnd == null) {
			return null;
		}
		ObjectProperty property = model.createObjectProperty(Config.pizzaNs + objectPropertyName);
		Resource resource = individualStart.addProperty(property, individualEnd);
		
		File file = new File(Config.ontologyPath);
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			model.write(fw, "RDF/XML-ABBREV");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resource;
	}

	@Override
	public List<Resource> addObjectProperties(Individual individualStart, List<String> objectPropertyNames, List<Individual> individualEnds) {
		if(individualStart == null || objectPropertyNames == null || individualEnds == null) {
			return null;
		}
		int index = 0;
		List<Resource> resources = new ArrayList<Resource>();
		for(Individual individualEnd : individualEnds) {
			ObjectProperty property = model.createObjectProperty(Config.pizzaNs + objectPropertyNames.get(index));
			Resource resource = individualStart.addProperty(property, individualEnd);
			resources.add(resource);
			++index;
		}
		return resources;
	}
}