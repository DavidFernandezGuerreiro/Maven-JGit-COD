
package com.mycompany.cod.maven;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 * En esta clase están situados todos los métodos de la aplicación.
 * Métodos: createRepository, clonar, commit y push.
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
     * Clona el proyecto de un repositorio creado en GitHub.
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
     * Este método hace un nuevo commit al proyecto que quieras.
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
            AddCommand add=git.add();
            add.addFilepattern(rutaCommit).call();
            ///home/local/DANIELCASTELAO/dfernandezguerreiro/NetBeansProjects/JavaApplication135/.git
            
            git=new Git(repo);
            CommitCommand commit=git.commit();
            commit.setMessage(JOptionPane.showInputDialog("Introducir el mensaje del commit: ")).call();
            
        } catch (IOException ex) {
            System.out.println("ERROR: "+ex);
        } catch (GitAPIException ex) {
            System.out.println("ERROR 2: "+ex);
        }
    }
    
    /**
     * Este método hace un push del proyecto que precises.
     * Añadiendo los siguientes parámetros.
     * 
     * @param ruta Pide por teclado la ruta de proyecto con .git al final.
     * @param rutaURI Pide por teclado la ruta del repositorio de GitHub.
     * @param nomUsu Pide por teclado el nombre de usuario.
     * @param pass Pide por teclado la contraseña.
     */
    public void push(){
        String ruta=JOptionPane.showInputDialog("Introducir la 'ruta del proyecto .git': ");
        String rutaURI=JOptionPane.showInputDialog("Introducir la URL.git del repositorio: ");
        
        FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
        try {
            org.eclipse.jgit.lib.Repository repo=repositoryBuilder.setGitDir(new File(ruta))
                    ///home/local/DANIELCASTELAO/dfernandezguerreiro/NetBeansProjects/JavaApplication135/.git
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
            
            Git git=new Git(repo);
            RemoteAddCommand remote=git.remoteAdd();
            remote.setName("origin");
            remote.setUri(new URIish(rutaURI));
            remote.call();
            
            String nomUsu=JOptionPane.showInputDialog("Introducir nombre de usuario: ");
            String pass=JOptionPane.showInputDialog("Introducir contraseña: ");
            PushCommand push=git.push();
            push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(nomUsu,pass));
            push.call();
            
        } catch (IOException ex) {
            System.out.println("ERROR: "+ex);
        } catch (URISyntaxException ex) {
            System.out.println("ERROR: "+ex);
        } catch (GitAPIException ex) {
            System.out.println("ERROR: "+ex);
        }
    }
    
    
}
