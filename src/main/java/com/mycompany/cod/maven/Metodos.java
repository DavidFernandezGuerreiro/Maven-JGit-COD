
package com.mycompany.cod.maven;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHEventPayload.Repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;



/**
 *
 * @author dfernandezguerreiro
 */
public class Metodos {
    /**
     * Crea un nuevo repositorio en GitHub con el nombre de usuario y contraseña creados en la carpeta .github.
     * Pide por teclado el nombre que le quieres asignar al repositorio.
     * 
     * @throws IOException Señala que se ha producido un error de E/S.
     */
    public void createRepository() throws IOException{
        GitHub obxGitHub=GitHub.connect();
        GHCreateRepositoryBuilder obxBuilder=obxGitHub.createRepository(JOptionPane.showInputDialog("Introducir nombre del repositorio: "));
        obxBuilder.create();
    }
    
    public void clonar(){
        try {
            Git.cloneRepository()
                    .setURI(JOptionPane.showInputDialog("Introducir la URL.git del repositorio: "))
                    .setDirectory(new File(JOptionPane.showInputDialog("Introducir la ruta de la carpeta donde se guardará el proyecto clonado:")))
                    .call();
            
        } catch (GitAPIException ex) {
            System.out.println("ERROR: "+ex);
        }
    }
    
    
    
    
    
    
}
