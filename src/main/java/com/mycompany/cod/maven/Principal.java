
package com.mycompany.cod.maven;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 *
 * @author dfernandezguerreiro
 */
public class Principal {

    /**
     * Clase Principal donde hay un menú hecho con un switch.
     * Donde recibe los Métodos situados en la Clase Metodos.
     * 
     * @throws IOException Señala que se ha producido un error de E/S.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        /**
         * Aquí tenemos un menú con todos los métodos de la Clase Metodos.
         * 
         * @param opc La función de este parámetro es entrar y salir del bucle while.
         * @param op Con este parámetro ejecutamos el método que queramos.
         */
        
        Metodos obx=new Metodos();

        boolean opc=true;
        while(opc==true){
            int op=Integer.parseInt(JOptionPane.showInputDialog("***** MENÚ *****"
                    + "\n0.-Salir de la aplicación."
                    + "\n1.-Crear un nuevo Repositorio."
                    + "\n2.-Clonar un repositorio."
                    + "\n3.-Hacer un commit."
                    + "\n4.-Hacer push a un repositorio."));
            switch(op){
                case 0:
                    opc=false;
                    break;
                case 1:
                    obx.createRepository();
                    break;
                case 2:
                    obx.clonar();
                    break;
                case 3:
                    obx.commit();
                    break;
                case 4:
                    obx.push();
                    break;
                default:
                    System.out.println("*** OPCIÓN INCORRECTA ***");
            }
        }
        
    }
    
}
