/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetormi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author LUCAS MATHEUS
 */
public interface JogoVelhaRemote extends Remote{
    
    // Funções Jogo SinglePlayer
    public String jogaCPU() throws RemoteException;
    public int jogada(int jogada) throws RemoteException;
    
    // Funções Jogo MultPlayer
    public int getStatus()throws RemoteException;
    public void setStatus(int status)throws RemoteException;
    public int jogadaMult(int jogada,String simbolo);
    
    // Funções Gerais
    public String printJogo()throws RemoteException;
    public String Teste2()throws RemoteException;
    public void initVet() throws RemoteException;
    public void cleanVet() throws RemoteException;
    public String instrucao() throws RemoteException;
    public int first() throws RemoteException;
    public int endGame() throws RemoteException;
}
