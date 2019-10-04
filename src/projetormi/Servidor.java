/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetormi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUCAS MATHEUS
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(9999);
            JogoVelhaRemote objeto = (JogoVelhaRemote) new JogoVelha();
            registro.rebind("JogoVelha", objeto);
            System.out.println("Objeto Remoto Criado com sucesso.");
                    
        } catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
