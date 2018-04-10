
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
    
    /**
     * Clona el proyecto de repositorio creado en GitHub.
     * Pide por teclado la URL.git del repositorio y la ruta donde quieres guardar el proyecto.
     */
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
    
    /**
     * Hace un nuevo commit al proyecto que quieras.
     * Pide por teclado el mensaje del commit.
     * 
     * @param rutaCommit Pide por teclado introducir la ruta del proyecto .git.
     */
    public void commit(){
        
        String rutaCommit=JOptionPane.showInputDialog("Introducir la 'ruta del proyecto .git': ");
        
        FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
        try {
            org.eclipse.jgit.lib.Repository repo=repositoryBuilder.setGitDir(new File(rutaCommit))
                    ///home/local/DANIELCASTELAO/dfernandezguerreiro/NetBeansProjects/JavaApplication135/.git
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
            
            Git git=new Git(repo);
            AddCommand add = git.add();
            add.addFilepattern(rutaCommit).call();
            ///home/local/DANIELCASTELAO/dfernandezguerreiro/NetBeansProjects/JavaApplication135/.git
            
            git=new Git(repo);
            CommitCommand commit = git.commit();
            commit.setMessage(JOptionPane.showInputDialog("Introducir el mensaje del commit: ")).call();
            
        } catch (IOException ex) {
            System.out.println("ERROR: "+ex);
        } catch (GitAPIException ex) {
            System.out.println("ERROR 2: "+ex);
        }
        
    }
    
    
}
