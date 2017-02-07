package com.iup.tp.twitup.ihm;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import com.iup.tp.twitup.common.Constants;

public class Fichier1 implements Serializable{
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JTextArea textArea_1;
	FileFilter filtre_lsp;
	JFileChooser chooser = new JFileChooser();
	String path_file, line;
	private String nomFic;
	
	public Fichier1(String path_file){
		this.path_file = path_file;
	}
	
	public void saveConfig(String data) throws IOException
	{
		 File f = new File(Constants.CONFIGURATION_FILE);
         BufferedWriter bw = new BufferedWriter(new FileWriter(f));
         bw.write(data);
         bw.close();
	}
	public String readFile(String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String ligne;
			StringBuffer fichier = new StringBuffer();
 
			while((ligne = reader.readLine()) != null){
				fichier.append(ligne);
				fichier.append("\n");			
			}
			reader.close();
 
			return fichier.toString();		
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	
	public void Chargement_fic1(){
		JFileChooser chooser = new JFileChooser();

		int returnVal = chooser.showOpenDialog(frame);
		
		// Si on sélectionne un fichier à charger
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File selection = chooser.getSelectedFile();
			// Lecture du fichier
			line = readFile(chooser.getSelectedFile().getPath());;
			// Recuperer le chemin du fichier
			path_file = (selection.getPath());
			// Recuperer le nom du fichier
			nomFic = (selection.getName() != null) ? selection.getName().substring(0,selection.getName().indexOf('.')) : "";
			System.out.println("Fichier " + nomFic + " sélectionné !");
		}
		// Sinon(si sélection du fichier annulée)
		else{
			// Affichage d'un message de notification
			JOptionPane.showMessageDialog(null, "Non chargé", "Information", JOptionPane.PLAIN_MESSAGE);
		}		
	}
	
	public String getPath_file() {
        return this.path_file;
    }
	
	public String getLine() {
        return this.line;
    }
	
	public String toString() {
        return nomFic;
    }
}